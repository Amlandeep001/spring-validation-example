package com.validation.api.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.ContentCachingRequestWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@Log4j2
@WebFilter(filterName = "RequestCachingFilter", urlPatterns = "/*")
public class RequestCachingFilter extends OncePerRequestFilter
{
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException
	{
		HttpMethod httpMethod = HttpMethod.valueOf(request.getMethod());

		// Wrap request to cache the body
		ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);

		// Proceed with the filter chain
		filterChain.doFilter(wrappedRequest, response);

		if(httpMethod == HttpMethod.GET)
		{
			// Log query parameters for GET requests
			final Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			log.info("Input {} REQUEST Path Variables: {}", httpMethod, pathVariables != null ? pathVariables : "No path variables");
		}
		else
		{
			// Log request body for other methods (e.g., POST, PUT)
			final String requestBody = new String(wrappedRequest.getContentAsByteArray(), StandardCharsets.UTF_8);
			log.info("Input REQUEST json: {}", requestBody);
		}
	}
}