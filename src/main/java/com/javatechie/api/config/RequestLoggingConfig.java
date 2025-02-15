/*package com.javatechie.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
@ConditionalOnProperty(value = "request.logging.shouldLog")
public class RequestLoggingConfig
{
	private String excludedChars;
	private boolean shouldLogRequest;
	private int maxPayloadLength;

	public RequestLoggingConfig(@Value("${request.logging.excludedCharsRegex}") String excludedChars, @Value("${request.logging.shouldLog}") boolean shouldLogRequest,
			@Value("${max.length.payload}") int maxPayloadLength)
	{
		this.excludedChars = excludedChars;
		this.shouldLogRequest = shouldLogRequest;
		this.maxPayloadLength = maxPayloadLength;
	}

	@Bean
	CommonsRequestLoggingFilter requestLoggingFilter()
	{
		CommonsRequestLoggingFilter loggingFilter = new CustomPayloadCapture(excludedChars, shouldLogRequest);
		loggingFilter.setIncludeClientInfo(true);
		loggingFilter.setIncludeQueryString(true);
		loggingFilter.setIncludePayload(true);
		loggingFilter.setMaxPayloadLength(maxPayloadLength);
		return loggingFilter;
	}

}
*/