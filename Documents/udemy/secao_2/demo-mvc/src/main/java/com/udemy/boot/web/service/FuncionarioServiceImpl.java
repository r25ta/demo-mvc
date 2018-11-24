package com.udemy.boot.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.boot.web.dao.FuncionarioDao;
import com.udemy.boot.web.domain.Funcionario;

@Service

/*CONTROLE DE TRANSAÇÃO DESATIVADO (TRUE) NA ASSINATURA DA CLASSE*/
@Transactional(readOnly = true)
public class FuncionarioServiceImpl implements FuncionarioService {
	
	@Autowired
	private FuncionarioDao funcionarioDao;
	
	@Transactional(readOnly = false)
	/*ANOTAÇÃO SOBRESCRITA PARA ATIVAR O CONTROLE DE TRANSAÇÃO*/
	public void salvar(Funcionario funcionario) {
		funcionarioDao.save(funcionario);
	}

	/*ANOTAÇÃO SOBRESCRITA PARA ATIVAR O CONTROLE DE TRANSAÇÃO*/
	@Transactional(readOnly = false)
	public void editar(Funcionario funcionario) {
		funcionarioDao.update(funcionario);
	}

	/*ANOTAÇÃO SOBRESCRITA PARA ATIVAR O CONTROLE DE TRANSAÇÃO*/
	@Transactional(readOnly = false)
	public void excluir(Long id) {
		funcionarioDao.delete(id);

	}

	
	public Funcionario buscarPorId(Long id) {
		
		return funcionarioDao.findById(id);
	}

	
	public List<Funcionario> buscarTodos() {
		
		return funcionarioDao.findAll();
	}

}
