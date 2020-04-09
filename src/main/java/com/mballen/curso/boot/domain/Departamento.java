package com.mballen.curso.boot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento extends AbstractEntity<Long> {
	
	//nao deixa inserir campos vazios
	@NotBlank(message="Informe seu nome")
	//verifica o tamanho da spring
	@Size(min= 3, max=60, message="O nome do Departamento deve ter entre {min} e {max} caracteres.")
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	@OneToMany(mappedBy = "departamento") // chave estrangeira 1 para muitos cargos referenciando a variavel que esta na
											// outra entity
	private List<Cargo> cargos;

	public String getNome() {
		return nome;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
