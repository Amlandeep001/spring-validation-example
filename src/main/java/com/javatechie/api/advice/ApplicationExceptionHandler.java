package com.javatechie.api.advice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;

import jakarta.servlet.ServletException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
@Log4j2
public class ApplicationExceptionHandler
{
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex, WebRequest request)
			throws IOException, ServletException
	{
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->
		{
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		// log.error("Failures with errors: {}", errorMap);

		// Extract validation error messages
		/*String errorMessage = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage())
				.collect(Collectors.joining(", "));*/

		// Extract request URI
		String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();

		// Extract full request JSON body
		ContentCachingRequestWrapper cachedRequest = (ContentCachingRequestWrapper) ((ServletWebRequest) request).getRequest();
		String requestBody = new String(cachedRequest.getContentAsByteArray(), StandardCharsets.UTF_8);

		// Log the full JSON request along with error details
		log.error("400 Bad Request for URI: {} | Errors: {} | Request JSON: {}", requestUri, errorMap, requestBody);

		return errorMap;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) throws IOException
	{
		String errorMessage = ex.getConstraintViolations().stream()
				.map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
				.collect(Collectors.joining(", "));

		String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();

		ContentCachingRequestWrapper cachedRequest = (ContentCachingRequestWrapper) ((ServletWebRequest) request).getRequest();
		String requestBody = new String(cachedRequest.getContentAsByteArray(), StandardCharsets.UTF_8);

		log.error("400 Bad Request for URI: {} | Errors: {} | Request JSON: {}", requestUri, errorMessage, requestBody);

		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
}
