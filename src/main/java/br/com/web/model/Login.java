package br.com.web.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class Login {

	@Id
	private String login;
	
	private String senha;
	
	@OneToOne
	@JoinColumn(name="pk_id_empregado")
	private Empregado empregado;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="login_permissao", joinColumns = @JoinColumn(name="fk_Login")
	, inverseJoinColumns = @JoinColumn(name="fk_id_permissao"))
	private List<Permissao> permissoes;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
}
