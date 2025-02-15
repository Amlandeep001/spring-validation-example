/*package com.javatechie.api.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.CommonsRequestLoggingFilter;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomPayloadCapture extends CommonsRequestLoggingFilter
{
	private String excludedChars;
	private boolean shouldLogRequest;

	public CustomPayloadCapture(String excludedChars, boolean shouldLogRequest)
	{
		super();
		this.excludedChars = excludedChars;
		this.shouldLogRequest = shouldLogRequest;
	}

	@Override
	protected boolean shouldLog(HttpServletRequest request)
	{
		return shouldLogRequest;
	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message)
	{
		if(log.isInfoEnabled() && !request.getRequestURI().contains("health"))
		{
			log.info(message.replaceAll(excludedChars, ""));
		}

		// String payloadMsg = message.substring(message.indexOf("payload=") + 8, message.length() - 1);
		// log.error(payloadMsg);

		// log.error("id : " + JsonPath.read(payloadMsg, "$.id"));
	}

	@Override
	protected void beforeRequest(HttpServletRequest request, String message)
	{
		if(log.isDebugEnabled() && !request.getRequestURI().contains("health"))
		{
			log.debug(message.replaceAll(excludedChars, ""));
		}
	}

}
*/