package com.woodpecker.webframework.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.woodpecker.webframework.exception.BusinessException;
import com.woodpecker.webframework.exception.SystemException;
import com.woodpecker.webframework.json.JsonResponse;
import com.woodpecker.webframework.json.ValidateError;
import com.woodpecker.webframework.util.WoodpeckerContextUtil;

@ControllerAdvice
@ResponseBody
public class WoodpeckerAdviceController {

	private static final Logger logger = LoggerFactory.getLogger(WoodpeckerAdviceController.class);
	
	
    /**
     * 200 - accepted
     * When hibernate-validate exception occurs, return to valid exception message
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResponse handleException(MethodArgumentNotValidException e) {
    	String message = WoodpeckerContextUtil.getMessage("msg.common.10007");
    	logger.info(message, e);
        List<ValidateError> validateErrorList = new ArrayList<>();
        if (e.getBindingResult().hasFieldErrors()) {
            for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            	ValidateError validateError = new ValidateError();
            	validateError.setField(fieldError.getField());
            	validateError.setObjectName(fieldError.getObjectName());
            	validateError.setValue(String.valueOf(fieldError.getRejectedValue()));
            	validateError.setValidateMessage(fieldError.getDefaultMessage());
            	validateErrorList.add(validateError);
			}
        }
        
        return new JsonResponse().failure(message,validateErrorList);
    }
	
    /**
     * 200 - accepted
     * When business exception occurs, return to business exception message
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(BusinessException.class)
    public JsonResponse handleException(BusinessException e) {
    	logger.info(WoodpeckerContextUtil.getMessage("msg.common.90002"),e);
        return new JsonResponse().failure(e.getMessage());
    }
    
    /**
     * 200 - accepted
     * When system exception occurs, return to system exception message
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(SystemException.class)
    public JsonResponse handleException(SystemException e) {
    	String message = WoodpeckerContextUtil.getMessage("msg.common.10002");
        logger.error(message,e);
        return new JsonResponse().failure(message);
    }
    
	/**
     * 400 - Bad Request
     * parameter resolution failure
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JsonResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    	String message = WoodpeckerContextUtil.getMessage("msg.common.10006");
    	logger.info(message,e);
        return new JsonResponse().failure(message);
    }
    
    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    	String message = WoodpeckerContextUtil.getMessage("msg.common.10005");
    	logger.info(message,e);
        return new JsonResponse().failure(message);
    }
    
    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public JsonResponse handleHttpMediaTypeNotSupportedException(Exception e) {
    	String message = WoodpeckerContextUtil.getMessage("msg.common.10004");
    	logger.info(message,e);
        return new JsonResponse().failure(message);
    }
    
    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public JsonResponse handleException(Throwable e) {
    	String message = WoodpeckerContextUtil.getMessage("msg.common.10003");
        logger.error(message,e);
        return new JsonResponse().failure(message);
    }
}
