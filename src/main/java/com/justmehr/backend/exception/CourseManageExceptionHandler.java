package com.justmehr.backend.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CourseManageExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST, "Validation error", details);
		return buildResponseEntity(error);
	}

	@ExceptionHandler({ CourseNotFoundException.class, UserNotFoundException.class })
	public final ResponseEntity<Object> handleNotFoundExceptions(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ApiError error = new ApiError(HttpStatus.NOT_FOUND, "Resource error", details);
		return buildResponseEntity(error);
	}

	@ExceptionHandler({CourseCreationException.class, Exception.class})
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occured");
		return buildResponseEntity(error);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError error) {
		return new ResponseEntity(error, new HttpHeaders(), error.getStatus());
	}
}
