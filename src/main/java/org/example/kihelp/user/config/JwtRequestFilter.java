package org.example.kihelp.user.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.kihelp.user.util.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.kihelp.user.util.MessageError.JWT_TOKEN_EXPIRED;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        try {
            if(authHeader != null && authHeader.startsWith("Bearer ")){
                jwt = authHeader.substring(7);
                username = jwtUtils.getUsername(jwt);
            }

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        jwtUtils.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                );
                SecurityContextHolder.getContext().setAuthentication(token);
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            Map<String, String> error = new HashMap<>();
            error.put("error", JWT_TOKEN_EXPIRED);
            response.getWriter().write(new ObjectMapper().writeValueAsString(error));
        }
    }

}
