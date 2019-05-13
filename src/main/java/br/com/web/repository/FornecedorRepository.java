package br.com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.web.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{

}
