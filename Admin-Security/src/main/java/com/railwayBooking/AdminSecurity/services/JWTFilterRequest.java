package com.railwayBooking.AdminSecurity.services;

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

import com.railwayBooking.AdminSecurity.utils.JWTUtils;

@Component 
public class JWTFilterRequest extends OncePerRequestFilter {
	
	@Autowired
	private JWTUtils jwtUtils;
	
	@Autowired
	private AdminService userService;
	
	//unimplemented method
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader=request.getHeader("Authorization");
		String username=null;
		//String pwd=null;
		String jwtToken=null;
		//retrive jwt token from authorization header
		if (authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")) //will retrive the header from this token
		{
			jwtToken = authorizationHeader.substring(7); //bcz BEARER_ is 7character 
			username=jwtUtils.extractUsername(jwtToken);        //extract username from jwt
		}
		
		if (username !=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails currentUserDetails=userService.loadUserByUsername(username);
			Boolean tokenValidated=jwtUtils.validateToken(jwtToken, currentUserDetails);
			
			if (tokenValidated)
			{
				//new UsernamePasswordAuthenticationToken(currentUserDetails, tokenValidated, null)
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(currentUserDetails,null,currentUserDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails( new WebAuthenticationDetailsSource().buildDetails(request) );
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		//continue the chain of the filter
		filterChain.doFilter(request, response);
		
	}

}