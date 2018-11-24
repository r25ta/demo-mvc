package com.udemy.boot.web.dao;

import java.util.List;

import com.udemy.boot.web.domain.Funcionario;

public interface FuncionarioDao {
	void save(Funcionario funcionario);
	
	void update(Funcionario funcionario);
	
	void delete(Long id);
	
	Funcionario findById(Long id);
	
	List<Funcionario> findAll();

}
