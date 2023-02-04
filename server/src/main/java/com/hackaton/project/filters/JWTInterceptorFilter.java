package com.hackaton.project.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackaton.project.dtos.ErrorDTO;
import com.hackaton.project.dtos.UserAuthDTO;
import com.hackaton.project.exceptions.JWTExpiredException;
import com.hackaton.project.utils.JwtUtilImpl;
import jakarta.annotation.Priority;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JWTInterceptorFilter extends OncePerRequestFilter {
    private final JwtUtilImpl jwtUtil;
    public JWTInterceptorFilter(JwtUtilImpl jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("filterings");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, Authorization");

        String token = request.getHeader("Authorization");

        if (Objects.nonNull(token)) {
                token = token.trim();
                UserAuthDTO decodedUser = jwtUtil.decode(token);

                if (Objects.isNull(decodedUser)) {
                    ErrorDTO errorDTO = ErrorDTO.builder()
                            .withTitle("Expired Token")
                            .withDetails("Expired JWT")
                            .withStatus(HttpStatus.GONE.value())
                            .withErrorType(JWTExpiredException.class.getSimpleName())
                            .withErrorCode("JHEE")
                            .build();

                    response.setStatus(HttpStatus.GONE.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.getWriter().write(convertObjectToJson(errorDTO));
                    return;
                }

                request.setAttribute("user", decodedUser);
                request.setAttribute("isAuthenticated", decodedUser);
            }

        filterChain.doFilter(request, response);
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
