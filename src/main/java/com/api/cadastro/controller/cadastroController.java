package com.api.cadastro.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.cadastro.dto.cadastroDto;
import com.api.cadastro.model.cadastroModel;
import com.api.cadastro.service.cadastroService;

@RestController
@RequestMapping("/")
public class cadastroController {
	
	final cadastroService cadastroService;
	
	public cadastroController(cadastroService  cadastroService) {
		this.cadastroService = cadastroService;
		}
	
	@PostMapping("/add")
	public ResponseEntity<Object> saveCadastro (@RequestBody @Valid cadastroDto cadastroDto) {
		var cadastroModel = new cadastroModel();
		BeanUtils.copyProperties(cadastroDto, cadastroModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(cadastroService.save(cadastroModel));
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<cadastroModel>> getAllCadastros(){
		return ResponseEntity.status(HttpStatus.OK).body(cadastroService.findAll());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneCadastro(@PathVariable("id") Long id) {
		Optional<cadastroModel> cadastroModelOptional = cadastroService.findById(id);
		
		if(cadastroModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(cadastroModelOptional.get());
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro não encontrado");
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> cadastroDelete(@PathVariable("id") Long id) {
		Optional <cadastroModel> cadastroModelOptional = cadastroService.findById(id);
		
		if(cadastroModelOptional.isPresent()) {
			cadastroService.delete(cadastroModelOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body("Cadastro deletado com sucesso");
			
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro não encontrado");
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateCastro(@PathVariable("id") Long id, 
			@RequestBody @Valid cadastroDto cadastroDto){
		
		Optional <cadastroModel> cadastroModelOptional = cadastroService.findById(id);
		if(cadastroModelOptional.isPresent()) {
			var cadastroModel = new cadastroModel();
			BeanUtils.copyProperties(cadastroDto, cadastroModel);
			cadastroModel.setId(cadastroModelOptional.get().getId());
			
			return ResponseEntity.status(HttpStatus.OK).body(cadastroService.save(cadastroModel));
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro não encontrado");
	}
	
	
	
}