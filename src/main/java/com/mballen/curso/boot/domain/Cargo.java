package com.mballen.curso.boot.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@SuppressWarnings("serial")
@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long> {
	
	@NotBlank(message="O campo Cargo e obrigatorio")
	@Size(max=60, message="o nome do campo deve conter no maximo 60 caracteres")
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	@NotNull(message = " selecione o departemento relativo ao cargo")
	@ManyToOne // muitos cargos para um departamento
	@JoinColumn(name = "id_departamento_fk")
//chave estrangeira departamento varios cargos
//cargo um departamento
//um para n coloca ao contrario na tabela que vai ter a chave estrangeira
	private Departamento departamento;

	@OneToMany(mappedBy = "cargo") // chave estrangeira 1 para muitos cargos muitos funcionarios para um cargo
	private List<Funcionario> funcionarios;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
