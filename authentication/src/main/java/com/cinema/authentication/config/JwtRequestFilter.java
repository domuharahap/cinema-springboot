package com.cinema.authentication.config;

import com.cinema.authentication.service.AuthenticationService;
import com.cinema.authentication.utils.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider provider;

    @Autowired
    private AuthenticationService service;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            final String tokenHeader = request.getHeader("Authorization");

            if(tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                String token = tokenHeader.substring(7);

                String username = provider.getUsernameFromToken(token);

                if(username != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                    UserDetails details = service.findByUsername(username);

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } else
                logger.warn("JWT token does not begin with Bearer string");

        }catch (Exception e){
            logger.error("Unable to get JWT token", e);
        }

        filterChain.doFilter(request, response);

    }
}
