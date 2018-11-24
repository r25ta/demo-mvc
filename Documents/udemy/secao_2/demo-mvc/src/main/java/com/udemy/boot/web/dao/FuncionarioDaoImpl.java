package com.udemy.boot.web.dao;

import org.springframework.stereotype.Repository;

import com.udemy.boot.web.domain.Funcionario;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

}
