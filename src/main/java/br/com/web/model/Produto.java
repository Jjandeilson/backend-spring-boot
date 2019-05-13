package br.com.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_produto")
	private Long idProduto;
	
	@Column(name="nome_produto")
	@NotNull
	@Size(min = 6, max = 20)
	private String nomeProduto;
	
	@Column(name="preco_fabrica")
	@NotNull
	private double precoFabrica;
	
	@Column(name="preco_loja")
	@NotNull
	private double precoLoja;
	
	@NotNull
	@Size(max=20)
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name="pk_id_fornecedor")
	@NotNull
	private Fornecedor fornecedor;
	
	@ManyToOne
	@JoinColumn(name="pk_id_loja")
	@NotNull
	private Loja loja;

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getPrecoFabrica() {
		return precoFabrica;
	}

	public void setPrecoFabrica(double precoFabrica) {
		this.precoFabrica = precoFabrica;
	}

	public double getPrecoLoja() {
		return precoLoja;
	}

	public void setPrecoLoja(double precoLoja) {
		this.precoLoja = precoLoja;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (idProduto == null) {
			if (other.idProduto != null)
				return false;
		} else if (!idProduto.equals(other.idProduto))
			return false;
		return true;
	}
	
}
