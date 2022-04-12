package com.api.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.cadastro.model.cadastroModel;

public interface cadastroRepository extends JpaRepository<cadastroModel, Long> {

}
