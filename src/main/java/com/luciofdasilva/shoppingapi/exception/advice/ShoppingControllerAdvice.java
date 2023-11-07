package com.luciofdasilva.shoppingapi.exception.advice;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.luciofdasilva.shoppingclient.dto.ErrorDTO;
import com.luciofdasilva.shoppingclient.exception.ProductNotFoundException;
import com.luciofdasilva.shoppingclient.exception.UserNotFoundException;

@ControllerAdvice(basePackages = "com.luciofdasilva.shoppingapi.controllers")
public class ShoppingControllerAdvice {
    @ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDTO handleUserNotFound(ProductNotFoundException userNotFoundException) {
    	ErrorDTO errorDTO = new ErrorDTO();
    	errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
    	errorDTO.setMessage("Produto não encontrado.");
    	errorDTO.setTimestamp(new Date());
        return errorDTO;
    }

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO handleUserNotFound(UserNotFoundException userNotFoundException) {
    	ErrorDTO errorDTO = new ErrorDTO();
    	errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
    	errorDTO.setMessage("Usuário não encontrado.");
    	errorDTO.setTimestamp(new Date());
        return errorDTO;
    }
}
