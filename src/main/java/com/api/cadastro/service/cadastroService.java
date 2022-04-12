package com.api.cadastro.service;

import com.api.cadastro.model.cadastroModel;
import com.api.cadastro.repository.cadastroRepository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class cadastroService {
	
	final cadastroRepository cadastroRepository;
	
	public cadastroService (cadastroRepository cadastroRepository) {
		this.cadastroRepository = cadastroRepository;
		
		}
	
	@Transactional
	public cadastroModel save(cadastroModel cadastroModel) {
		return cadastroRepository.save(cadastroModel);
	}
	
	public List<cadastroModel> findAll(){
		return cadastroRepository.findAll();
	}
	
	public Optional<cadastroModel> findById(Long id){
		return cadastroRepository.findById(id);
	}
	
	@Transactional
	public void delete(cadastroModel cadastroModel) {
		cadastroRepository.delete(cadastroModel);
	}
	


}
