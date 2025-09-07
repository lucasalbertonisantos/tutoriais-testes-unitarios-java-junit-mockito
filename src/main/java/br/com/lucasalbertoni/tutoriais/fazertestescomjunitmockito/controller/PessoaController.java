package br.com.lucasalbertoni.tutoriais.fazertestescomjunitmockito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucasalbertoni.tutoriais.fazertestescomjunitmockito.entity.Pessoa;
import br.com.lucasalbertoni.tutoriais.fazertestescomjunitmockito.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@PostMapping
	public Pessoa cadastrar(@RequestBody Pessoa pessoa) {
		return pessoaService.cadastrar(pessoa);
	}
	
	@GetMapping
	public List<Pessoa> consultar() {
		return pessoaService.listarTodos();
	}
	
	@PutMapping
	public Pessoa alterar(@RequestBody Pessoa pessoa) {
		return pessoaService.alterar(pessoa);
	}
	
	@DeleteMapping
	public Pessoa excluir(@RequestParam Long id) {
		return pessoaService.excluir(id);
	}

}
