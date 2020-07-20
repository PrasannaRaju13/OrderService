package com.order.item.exceptions;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.order.item.constants.ExceptionConstants;



@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler 
{

	@Autowired
	MessageSourceUtil messageSourceUtil;
	
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<Object> handleApplicationException(WebRequest request, Exception ex){
		BaseException e = (BaseException)ex;
		return getCustomExceptionResponse(request,e);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(WebRequest request, Exception ex){
		BaseException e = (BaseException)ex;
		return getCustomExceptionResponse(request,e);
	}
	private ResponseEntity<Object> getCustomExceptionResponse(WebRequest request, BaseException ex) {
		String errorCode = ex.getErrorCode();
		String exceptionMessage = ex.getExceptionMessage();
		String timeStamp = ex.getTimeStamp();
		String errorModule = ex.getErrorModule();
		String errorMessage = "";
		Integer id=RandomUtils.nextInt(10000, 50000);
		try{
			errorMessage = messageSourceUtil.getLocalisedText(errorCode, errorModule);
		}catch(Exception e) {
			errorMessage = messageSourceUtil.getLocalisedText(ExceptionConstants.GENERAL_ERROR_CODE, ExceptionConstants.GENERAL_MODULE);
			exceptionMessage = "The message for errorCode:"+errorCode+" module:"+errorModule+" is not found in prop file";
		}
		HttpStatus status = ex.getHttpStatus();
		
		return new ResponseEntity<>(new ApiError(id, errorMessage,errorCode, timeStamp,exceptionMessage), status);
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
		body.put("errors", errors);
		return new ResponseEntity<>(body, headers, status);
	}

}