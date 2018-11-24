package com.udemy.boot.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.boot.web.dao.CargoDao;
import com.udemy.boot.web.domain.Cargo;

@Service

/*CONTROLE DE TRANSAÇÃO ATIVADO (FALSE) PARA METODOS DE ESCRITA E LEITURA*/
@Transactional(readOnly = false)
public class CargoServiceImpl implements CargoService {

	@Autowired
	private CargoDao cargoDao;
	
	public void salvar(Cargo cargo) {
		cargoDao.save(cargo);

	}


	public void editar(Cargo cargo) {
		cargoDao.update(cargo);
		
	}

	
	public void excluir(Long id) {
		cargoDao.delete(id);

	}

	
	public Cargo buscarPorId(Long id) {
		
		return cargoDao.findById(id);
	}

	
	public List<Cargo> buscarTodos() {
	
		return cargoDao.findAll();
	}


	@Override
	public boolean cargoTemFuncionarios(Long id) {
		if(buscarPorId(id).getFuncionarios().isEmpty()) {
			return false;
		}
		return true;
	}

}
