package com.udemy.boot.web.dao;

import org.springframework.stereotype.Repository;

import com.udemy.boot.web.domain.Cargo;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao{

}
