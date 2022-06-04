package com.example.productmanagement.filters;

import com.example.productmanagement.util.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
public class AuthEntryPoint implements AuthenticationEntryPoint {
    /**
     * Commences an authentication and returns an error message if the exception is invoked
     *
     * @param request       - the input {@link HttpServletRequest}
     * @param response      - the input {@link HttpServletResponse}
     * @param authException - the input {@link AuthenticationException}
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error("Unauthorized error: {}", authException.getMessage()); //daca ceva nu merge cu procesul de autorizare
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//se pune status de UNAUTHORIZED
        response.setContentType(APPLICATION_JSON_VALUE);
        //se pune mesajul de eroare si continutul de tip JSON
        new ObjectMapper().writeValue(response.getOutputStream(), JSONUtil.objectToJsonString("Unauthorized error: Bad credentials"));
    }
}