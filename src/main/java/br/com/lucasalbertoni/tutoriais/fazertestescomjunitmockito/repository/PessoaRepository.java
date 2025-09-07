package br.com.lucasalbertoni.tutoriais.fazertestescomjunitmockito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lucasalbertoni.tutoriais.fazertestescomjunitmockito.entity.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
}
