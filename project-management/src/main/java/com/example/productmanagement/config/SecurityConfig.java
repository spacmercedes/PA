package com.example.productmanagement.config;

import com.example.productmanagement.filters.AuthEntryPoint;
import com.example.productmanagement.filters.AuthenticationFilter;
import com.example.productmanagement.filters.CustomAuthorizationFilter;
import com.example.productmanagement.services.UserDetailsServiceImpl;
import com.example.productmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//securitatea aplicatiei consta in faptul ca un request trece prin mai multe filtre, ca sa fie autorizat
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final CustomAuthorizationFilter authorizationFilter;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, CustomAuthorizationFilter authorizationFilter) {
        this.userDetailsService = userDetailsService;
        this.authorizationFilter = authorizationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(new AuthEntryPoint()); //cors si csrf sunt layere de securitate prin care nu se permite aplicatiiilor sa faca requesturi doar de la backend //cors - nu poti da requestrui din pagina de HTML. Faci un request catre serverul de front-end si dupa frontul face request la backend
        //le dau disable acestor layere, deoarece nu permit transferul de resurse foarte usor
        http.authorizeRequests().antMatchers("/users").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/swagger-ui/*", "/swagger-ui.html", "/webjars/**", "/v2/**", "/swagger-resources/**").permitAll();

        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager()))  //se verifica autenticitatea token-ului
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class) //primul filtru prin care trece, se face login-ul cu  UsernamePasswordAuthenticationFilter.class (clasa din spring)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //se asigura ca un client, indifereent de cate servere sunt, primeste acelasi raspuns pentru acelasi request
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { //implementarea PasswordEncoder care foloseste functia hash BCrypt
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER) //id default pentru autetificare
    public AuthenticationManager authenticationManagerBean() throws Exception { //proceseaza un request de Autentificare
        return super.authenticationManagerBean();
    }


}
