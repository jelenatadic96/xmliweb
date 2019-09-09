package com.megatravel.korisniciservice.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class JwtTokenFilter extends GenericFilterBean {

	private JwtTokenUtils jtwTokenUtils;

	public JwtTokenFilter(JwtTokenUtils jtwTokenUtils) {
		this.jtwTokenUtils = jtwTokenUtils;
	}

	@Override
	public void doFilter(ServletRequest request, 
			ServletResponse response, 
			FilterChain filterChain)
			throws IOException, ServletException {
		String token = jtwTokenUtils.resolveToken((HttpServletRequest) request);
		if (token != null && jtwTokenUtils.validateToken(token)) {
			Authentication authentication = token != null ? jtwTokenUtils.getAuthentication(token) : null;
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

}