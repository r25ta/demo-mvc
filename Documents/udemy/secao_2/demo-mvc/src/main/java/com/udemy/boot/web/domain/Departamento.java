package com.udemy.boot.web.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * NESTA CLASSE NÃO FOI DECLARADO O ATRIBUTO ID PORQUE SERÁ HERANÇA DA CLASSE AbstractEntity
 * Campos da tabela DEPARTAMENTOS
 * ID
 * NOME
 * */
@SuppressWarnings("serial")
@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento extends AbstractEntity<Long> {
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	/*
	 * Atributo mappedby utilizado porque o relacionamento entre as tabelas é bidirecional
	 * qdo temos um relacionamento bidirecional é obrigatório definir qual é o lado forte e fraco
	 * do relacionamento.
	 * Lado forte é quem possue a chave estrangeira (nesse caso tabela CARGO)
	 * */
	@OneToMany(mappedBy = "departamento")
	private List<Cargo> cargos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
	
}
