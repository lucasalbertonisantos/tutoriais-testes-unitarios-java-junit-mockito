package br.com.lucasalbertoni.tutoriais.fazertestescomjunitmockito.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lucasalbertoni.tutoriais.fazertestescomjunitmockito.entity.Pessoa;
import br.com.lucasalbertoni.tutoriais.fazertestescomjunitmockito.exception.PessoaException;

@Component
public class PessoaProducer {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void enviarCriadaAlterada(Pessoa pessoa) {
		try {
			String evento = objectMapper.writeValueAsString(pessoa);
			kafkaTemplate.send("pessoa-criada-alterada", evento);
		} catch (JsonProcessingException e) {
			throw new PessoaException("Não foi pessível converter a pessoa em um objeto json no evento de criacao alteracao!");
		}
	}
	
	public void enviarExcluida(Pessoa pessoa) {
		try {
			String evento = objectMapper.writeValueAsString(pessoa);
			kafkaTemplate.send("pessoa-excluida", evento);
		} catch (JsonProcessingException e) {
			throw new PessoaException("Não foi pessível converter a pessoa em um objeto json no evento de exclusao!");
		}
	}

}
