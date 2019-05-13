package br.com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.web.model.Empregado;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long>{

}
