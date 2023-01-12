package br.com.desafio.java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.java.model.Pessoa;
import br.com.desafio.java.repository.PessoaRepository;

@RestController
@RequestMapping(value="pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	//Lista todas as pessoas cadastradas na aplicação
	@GetMapping(value = "listarPessoa")
	@ResponseBody
	public ResponseEntity<List<Pessoa>> listaPessoa() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);
	}
	
	//Salva uma nova pessoa que contenha endereço ou não contenha tanto faz
	@PostMapping(value = "salvarPessoa")
	@ResponseBody
	public ResponseEntity<Pessoa> salvaPessoa(@RequestBody Pessoa pessoa){
		
		for(int i = 0; i < pessoa.getEnderecos().size(); i++) {
			pessoa.getEnderecos().get(i).setPessoa(pessoa);
		}
		
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		return new ResponseEntity<Pessoa>(pessoaSalva, HttpStatus.OK);
	}
	
	//Atualiza uma determinada pessoa 
	@PutMapping(value = "atualizarPessoa")
	@ResponseBody
	public ResponseEntity<?> atualizarPessoa(@RequestBody Pessoa pessoa){
		if(pessoa.getId() == null) {
			return new ResponseEntity<String>("Nenhuma Identificação de pessoa informada!", HttpStatus.OK);
		}
		
		for(int i =0; i < pessoa.getEnderecos().size(); i++) {
			pessoa.getEnderecos().get(i).setPessoa(pessoa);
		}
		
		Pessoa pessoaAtualizada = pessoaRepository.save(pessoa);
		return new ResponseEntity<Pessoa>(pessoaAtualizada, HttpStatus.OK);
	}
	
	//Deleta uma determinada Pessoa
	@DeleteMapping(value = "deletarPessoa")
	@ResponseBody
	public ResponseEntity<String> deletarPessoa(@RequestParam(name = "idPessoa") Long idPessoa){
		pessoaRepository.deleteById(idPessoa);
		return new ResponseEntity<String>("Pessoa deletada com sucesso!", HttpStatus.OK);
	}
	
	//Busca uma determinada Pessoa por ID
	@GetMapping(value = "buscarPessoa")
	@ResponseBody
	public ResponseEntity<Pessoa> buscarPessoaPorId(@RequestParam(name = "idPessoa") Long idPessoa){
		Pessoa pessoa = pessoaRepository.findById(idPessoa).get();
		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
	}
	
}
