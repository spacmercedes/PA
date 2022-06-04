package com.example.productmanagement.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    public static final String SECRET = "SECRET_KEY"; //secretul cu care este semnat token-ul
    public static final long EXPIRATION_TIME = 2 * 60 * 60 * 1000; // dura de valabilitate a token-ului - 2h

    @Autowired
    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Performs an authentication
     *
     * @param request  - the input {@link HttpServletRequest}
     * @param response - the input {@link HttpServletResponse}
     * @return an Authentication
     */
    @Override //se ocupa clasele din spring
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = this.obtainUsername(request);
        String password = this.obtainPassword(request);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken); //returneaza un token
    }

    /**
     * Set HTTP response headers
     *
     * @param request  {@link HttpServletRequest} representing HTTP request
     * @param response {@link HttpServletResponse} representing HTTP response
     * @param chain    {@link FilterChain} representing chain of security filters
     * @param auth     {@link Authentication}
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) {
        String token = JWT.create() //daca autentificarea se face cu succes, se creaaza un token
                .withSubject(((User) auth.getPrincipal()).getUsername()) //cu subiectul user
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // timpul de exprare ( data curenta + timpul de expirare )
                .sign(Algorithm.HMAC512(SECRET.getBytes())); //semneaza cu algoritmul HMA512(SHA512) si cu secretul pe care l am definit
        token = "Bearer " + token; //avem un token de tip Bearer, de aceea avem nevoie de acest prefix
          //request-urile si response-urile HTTP au mai multe headere, predefinite
        response.addHeader("Authorization", token);
        response.setHeader("Authorization", token);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
    }
}
