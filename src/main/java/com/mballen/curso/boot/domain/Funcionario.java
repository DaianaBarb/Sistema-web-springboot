package com.mballen.curso.boot.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "FUNCIONARIO")
//nulable false  significa que e obrigatorio no banco de dados e n pode ser nulo
public class Funcionario extends AbstractEntity<Long> {
	@NotBlank
	@Size(max=255, min=3)
	@Column(nullable = false, unique = true)
	private String nome;
    @NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00") // sete digitos e duas casas depois da
	private BigDecimal salario;
    //valida a data PastOrPresent
     @NotNull(message="{PastOrPresent.funcionario.dataEntrada}")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_entrada", nullable = false, columnDefinition = "DATE")
	private LocalDate dataEntrada;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_saida", columnDefinition = "DATE")
	private LocalDate dataSaida;
	
											// OnetoOne
//Sua função é mapear o relacionamento entre duas tabelas no banco de dados que tenha relação do tipo 1x1.
	//valid significa que deve ser validado conforme as regras da classe endereço
	@Valid
	@JoinColumn(name = "endereco_id_fk")
	//cascade na hora que apagar um funcionario ele apaga um endereçoS 
	@OneToOne(cascade = CascadeType.ALL) // um endereço para um funcionario fazer sempre a chave estrangeira onde e
	private Endereco endereco;
	@NotNull(message="{NotNull.funcionario.cargo}")
	@ManyToOne
	@JoinColumn(name = "cargo_id_fk")
	private Cargo cargo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDatadataSaida() {
		return dataSaida;
	}

	public void setDatadataSaida(LocalDate datadataSaida) {
		this.dataSaida = datadataSaida;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
}
