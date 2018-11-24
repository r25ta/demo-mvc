package com.udemy.boot.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.boot.web.dao.DepartamentoDao;
import com.udemy.boot.web.domain.Departamento;

@Service

public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private DepartamentoDao departamentoDao;

	/*CONTROLE DE TRANSAÇÃO ATIVO (FALSE) PARA METODOS DE ESCRITA*/
	@Transactional(readOnly = false)
	public void salvar(Departamento departamento) {
		departamentoDao.save(departamento);
	}

	
	/*CONTROLE DE TRANSAÇÃO ATIVO (FALSE) PARA METODOS DE ESCRITA*/
	@Transactional(readOnly = false)
	public void editar(Departamento departamento) {
		departamentoDao.update(departamento);

	}

	/*CONTROLE DE TRANSAÇÃO ATIVO (FALSE) PARA METODOS DE ESCRITA*/
	@Transactional(readOnly = false)
	public void excluir(Long id) {
		departamentoDao.delete(id);

	}

	/*CONTROLE DE TRANSAÇÃO DESATIVADO (TRUE) PARA METODOS DE LEITURA*/
	@Transactional(readOnly = true)
	public Departamento buscarPorId(Long id) {
	
		return departamentoDao.findById(id);
	}

	/*CONTROLE DE TRANSAÇÃO DESATIVADO (TRUE) PARA METODOS DE LEITURA*/
	@Transactional(readOnly = true)
	public List<Departamento> buscarTodos() {
	
		return departamentoDao.findAll();
	}


	@Override
	public boolean departamentoTemCargos(Long id) {
		/*
		 * Teste para veririfcar se existe cargo cadastrados no departamento
		 * */
		if(buscarPorId(id).getCargos().isEmpty()) {
			return false;
		}
		return true;
	}

}