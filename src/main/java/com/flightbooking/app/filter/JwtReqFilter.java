package com.flightbooking.app.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.flightbooking.app.Util.JwtUtil;
import com.flightbooking.app.service.impl.MyUserDetailsService;



@Component
public class JwtReqFilter extends OncePerRequestFilter{


	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//examine the jwt header and check if the token is valid
		//if valid get the userdetails and save
		String authHeader=request.getHeader("Authorization");
		String username=null;
		String jwtToken=null;

		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			jwtToken=authHeader.substring(7);
			username=jwtUtil.extractUsername(jwtToken);
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
		
			if(jwtUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken
						(userDetails, null, userDetails.getAuthorities());
				//if jwt is valid set it into context 
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}

		}
		filterChain.doFilter(request, response);
	}

}
