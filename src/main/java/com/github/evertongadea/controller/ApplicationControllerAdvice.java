package com.github.evertongadea.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.evertongadea.dto.ApiErros;
import com.github.evertongadea.exception.RegraNegocioException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErros handlerRegraNegocioException(RegraNegocioException ex) {
		String msgErro = ex.getMessage();
		return new ApiErros(msgErro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ApiErros handlerMathodsNotValidException( MethodArgumentNotValidException ex ) {
		List<String> erros = ex.getBindingResult().getAllErrors()
						.stream()
						.map(erro -> erro.getDefaultMessage())
						.collect(Collectors.toList());
		return new ApiErros(erros);
	}

}
