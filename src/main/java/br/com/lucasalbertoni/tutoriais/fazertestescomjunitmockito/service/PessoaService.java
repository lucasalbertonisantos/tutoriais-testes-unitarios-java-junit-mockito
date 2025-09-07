package br.com.lucasalbertoni.tutoriais.fazertestescomjunitmockito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucasalbertoni.tutoriais.fazertestescomjunitmockito.entity.Pessoa;
import br.com.lucasalbertoni.tutoriais.fazertestescomjunitmockito.exception.PessoaException;
import br.com.lucasalbertoni.tutoriais.fazertestescomjunitmockito.producer.PessoaProducer;
import br.com.lucasalbertoni.tutoriais.fazertestescomjunitmockito.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;


	@Autowired
	private PessoaProducer pessoaProducer;
	
	public Pessoa cadastrar(Pessoa pessoa){
		if(pessoa == null || pessoa.getId() != null 
				|| pessoa.getNome() == null || pessoa.getNome().isBlank()) {
			throw new PessoaException("A pessoa não pode ser nula, o id não pode estar preenchido e o "
					+ "nome deve ser preenchido e não pode ser vazio");
		}
		Pessoa pessoaCadastrada = pessoaRepository.save(pessoa);
		pessoaProducer.enviarCriadaAlterada(pessoaCadastrada);
		return pessoaCadastrada;
	}
	
	public List<Pessoa> listarTodos(){
		return pessoaRepository.findAll();
	}
	
	public Pessoa alterar(Pessoa pessoa){
		if(pessoa == null || pessoa.getId() == null 
				|| pessoa.getNome() == null || pessoa.getNome().isBlank()
				|| !pessoaRepository.existsById(pessoa.getId())) {
			throw new PessoaException("A pessoa não pode ser nula, o id deve estar preenchido, o nome "
					+ "deve ser preenchido e não pode ser vazio e a pessoa deve existir no banco de dados");
		}
		Pessoa pessoaCadastrada = pessoaRepository.save(pessoa);
		pessoaProducer.enviarCriadaAlterada(pessoaCadastrada);
		return pessoaCadastrada;
	}
	
	public Pessoa excluir(Long id){
		Optional<Pessoa> pessoaCadastrada = pessoaRepository.findById(id);
		if(pessoaCadastrada.isPresent()) {
			pessoaRepository.deleteById(id);
			pessoaProducer.enviarExcluida(pessoaCadastrada.get());
		}
		throw new PessoaException("A pessoa não existe para o id informado");
	}

}
