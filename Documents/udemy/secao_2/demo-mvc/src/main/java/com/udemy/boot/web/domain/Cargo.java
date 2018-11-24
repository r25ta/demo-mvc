package com.udemy.boot.web.domain;

import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long> {

	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	/*DEFINIÇÃO DO TIPO DE RELACIONAMENTO É FEITA SEMPRE DA DIREITA PARA ESQUERDA
	 * NESTE CASO MUITOS CARGOS PARA UM DEPARTAMENTO
	 * */
	@ManyToOne
	@JoinColumn(name = "id_departamento_fk")
	private Departamento departamento;
	
	/*
	 * NESTE RELACIONAMENTO UM CARGO PODE RETORNAR UMA LISTA DE FUNCIONARIOS
	 * A PARTE FORTE DO RELACIONAMENTO ESTA EM FUNCIONARIOS, PQ CONTEM A FK
	 * */
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionarios;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	
}
