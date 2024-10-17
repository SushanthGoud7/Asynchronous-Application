package com.courierdetails.app.exceptionhandler;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CourierExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidInputException.class)
	public Map<String, String> handleConstraintViolationException(ConstraintViolationException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getConstraintViolations().forEach(error -> {
			errorMap.put(error.getPropertyPath().toString(), error.getMessageTemplate());
		});
		return errorMap;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ServiceException.class)
	public Map<String, String> handleServiceException(ServiceException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("Internal server error", ex.getMessage());
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(JmsPublishingException.class)
	public Map<String, String> handleJmsPublishingException(ServiceException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("Internal server error", ex.getMessage());
		return errorMap;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CourierNotFoundException.class)
	public Map<String, String> handleCourierNotFoundException(Exception ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("courier not found", ex.getMessage());
		return errorMap;
	}

}
