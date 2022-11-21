package com.web.springbootauth;

import com.web.springbootauth.helper.JwtUtil;
import com.web.springbootauth.service.CustomUserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        String jwtToken = null;
        String userName = null;
        if(header !=null && header.startsWith("Bearer ")){
            jwtToken = header.substring(7);
            try {

                userName=jwtUtil.extractUsername(jwtToken);

            }catch (IllegalArgumentException e){
                System.out.println("Unable to get Token");
            }catch (ExpiredJwtException e){
                System.out.println("Token has expired");
            }
        }else
            System.out.println("Token is not start with Bearer");

        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()== null){
            UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);
            if(jwtUtil.validateToken(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                        new UsernamePasswordAuthenticationToken(userDetails,"richa123",userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request,response);
    }
}
