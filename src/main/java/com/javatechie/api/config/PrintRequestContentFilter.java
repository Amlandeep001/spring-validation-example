/*package com.javatechie.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;

import lombok.extern.log4j.Log4j2;

@Order(Ordered.LOWEST_PRECEDENCE)
@Log4j2
@Component
@WebFilter(filterName = "printRequestContentFilter", urlPatterns = "/*")
public class PrintRequestContentFilter extends OncePerRequestFilter
{

	@Override
	public void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
			throws IOException, ServletException
	{
		InputStream inputStream = request.getInputStream();
		byte[] body = StreamUtils.copyToByteArray(inputStream);
		log.info("PrintRequestContentFilter - doFilterInternal() : User Request body is : {}", new String(body));
		ContentCachingResponseWrapper resp = new ContentCachingResponseWrapper(response);
		filterChain.doFilter(request, resp);
		byte[] responseBody = resp.getContentAsByteArray();
		log.info("PrintRequestContentFilter - doFilterInternal() : User Response body is : {}", new String(responseBody, StandardCharsets.UTF_8));
		if(response.getStatus() == 400)
		{
			if(StringUtils.hasText(new String(responseBody, StandardCharsets.UTF_8)))
			{
				logErrorInDB(new String(body), new String(responseBody, StandardCharsets.UTF_8), response.getStatus());
			}
			else
			{
				logErrorInDB(new String(body), null, response.getStatus());
			}
		}

		resp.copyBodyToResponse();
	}

	*//**
		* @param String requestJson,String responseJson, int responseCode
		* @throws JsonProcessingException
		*//*
			private void logErrorInDB(String requestJson, String responseJson, int responseCode) throws IOException
			{
			log.info("UserRequestContentFilter -> Validation Error -> User Request for the Date: " + LocalDate.now() + ", id : " + JsonPath.read(requestJson, "$.id")
				+
				", name : " + JsonPath.read(requestJson, "$.name") +
				", email : " + JsonPath.read(requestJson, "$.email") +
				", mobile : " + JsonPath.read(requestJson, "$.mobile") +
				", gender : " + JsonPath.read(requestJson, "$.gender") +
				", age : " + JsonPath.read(requestJson, "$.age") +
				", nationality : " + JsonPath.read(requestJson, "$.nationality"));
			log.info("UserRequestContentFilter -> Validation Error -> Billing Response body is : " + responseJson);
			}
			}
			*/