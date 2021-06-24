package com.github.evertongadea.dto;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErros {

	@Getter
	private List<String> erros;

	public ApiErros(String erros) {
		this.erros = Arrays.asList(erros);
	}
	
	
}
