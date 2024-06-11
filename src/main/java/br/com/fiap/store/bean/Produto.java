package br.com.fiap.store.bean;

import java.time.LocalDate;

public class Produto {

	private Integer codigo;
	
	private String nome;
	
	private Double valor;
	
	private LocalDate dataDeFabricacao;
	
	private Integer quantidade;
	
	public Produto() {
		
	}

	public Produto(Integer codigo, String nome, Double valor, LocalDate dataDeFabricacao, Integer quantidade) {
		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
		this.dataDeFabricacao = dataDeFabricacao;
		this.quantidade = quantidade;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getDataDeFabricacao() {
		return dataDeFabricacao;
	}

	public void setDataDeFabricacao(LocalDate dataDeFabricacao) {
		this.dataDeFabricacao = dataDeFabricacao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "Produto: " + nome
				+ "\nValor: R$ " + valor 
				+ "\nData de Fabricação: " 
					+ dataDeFabricacao.getDayOfMonth() + "/" 
					+ dataDeFabricacao.getMonthValue() + "/" 
					+ dataDeFabricacao.getYear()
				+ "\nQuantidade: " + quantidade;
	}
	
	
}