package com.example.productmanagement.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.productmanagement.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//AICI SE FAC FILTRELE
@Slf4j
@Service
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    UserService userService;

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String AUTHORIZATION = "Authorization";
    private static final String SECRET = "SECRET_KEY";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException, ServletException {
        if(request.getServletPath().equals("/login")){ //daca path-il la care s-a facut request ste /login
            filterChain.doFilter(request, response); //atunci se face urmatorul filtru
        } else { //daca e altfel de request, se cere autorizare
            String authorizationHeader = request.getHeader(AUTHORIZATION);//din request ia header-ul de Authorization
            if(authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) { //daca nu e null si incepe cu Bearer , atunci
                try {
                    String token = authorizationHeader.substring(TOKEN_PREFIX.length()); //il tai in secvente si verific autenticitatea TOKEN-ului
                    Algorithm algorithm = Algorithm.HMAC512(SECRET.getBytes()); //
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token); //decodeaza apoi verifica token-ul
                    String username = decodedJWT.getSubject();
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, null); //ii pune autetificarea
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    log.error("Error loggin in {}", e.getMessage());
                    response.setHeader("error", e.getMessage());
                    response.sendError(401);// error -> se arunca exceptie si 401
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
