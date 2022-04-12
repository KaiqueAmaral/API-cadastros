package com.api.cadastro.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class cadastroDto {
	

	@NotBlank
	private String nome;
	@NotBlank
	private Integer idade;
	@NotBlank
	private String email;
	@NotBlank
	private Long celular;
	

}
