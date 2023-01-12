package br.com.desafio.java.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.java.model.Endereco;
import br.com.desafio.java.model.Pessoa;
import br.com.desafio.java.repository.EnderecoRepository;
import br.com.desafio.java.repository.PessoaRepository;

@RestController
@RequestMapping(value="endereco")
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
		
	//Lista todos endereços contidos na aplicação
	 @GetMapping(value = "listarTodos")
	 @ResponseBody
	 public ResponseEntity<List<Endereco>> listaEndereco() {
		 List<Endereco> enderecos = enderecoRepository.findAll();
		 return new ResponseEntity<List<Endereco>>(enderecos, HttpStatus.OK);
	 }
	 
	 //Lista todos endereços de uma única pessoa
	 @GetMapping(value = "listarPorPessoa")
	 @ResponseBody
	 public ResponseEntity<List<Endereco>> listaEnderecoPorPessoa(@RequestParam(name = "idPessoa") Long idPessoa) {
		 List<Endereco> enderecos = new ArrayList<Endereco>();
		 Pessoa pessoa = pessoaRepository.findById(idPessoa).get();
		 enderecos = pessoa.getEnderecos();
		 return new ResponseEntity<List<Endereco>>(enderecos, HttpStatus.OK);
	 }
	 
	 //Salva o endereço em uma pessoa
	 @PostMapping(value = "salvar/{idPessoa}")
	 @ResponseBody
	 public ResponseEntity<Endereco> salvaEndereco(@PathVariable("idPessoa") Long idPessoa,  @RequestBody Endereco endereco){
		 
		 Pessoa pessoa = pessoaRepository.findById(idPessoa).get();
		 endereco.setPessoa(pessoa);		 
		 Endereco enderecoSalvo = enderecoRepository.save(endereco);
		 
		 return new ResponseEntity<Endereco>(enderecoSalvo, HttpStatus.OK);
	 }
	 
	 //Atualiza o Endereço especifico pelo json enviado
	 @PutMapping(value = "atualizar")
	 @ResponseBody
	 public ResponseEntity<?> atualizarEndereco(@RequestBody Endereco endereco){
		 		 
		 if(endereco.getId() == null) {
			 return new ResponseEntity<String>("Nenhuma Identificação de endereço informada!", HttpStatus.OK);
		 } 	
		 
		 Endereco enderecoAtual = enderecoRepository.findById(endereco.getId()).get();		 
		 
		 Pessoa pessoa = pessoaRepository.findById(enderecoAtual.getPessoa().getId()).get();
		 endereco.setPessoa(pessoa);
		 
		 Endereco enderecoAtualizado = enderecoRepository.saveAndFlush(endereco);
		 return new ResponseEntity<Endereco>(enderecoAtualizado, HttpStatus.OK);
	 }
	 
	 //Deleta Endereço especifico pelo ID
	 @DeleteMapping(value = "deletar")
	 @ResponseBody
	 public ResponseEntity<String> deletarEndereco(@RequestParam(name = "idEndereco") Long idEndereco){
		 
		 if(idEndereco == null) {
			 return new ResponseEntity<String>("Nenhuma Identificação de endereço informada!", HttpStatus.OK);
		 } 	
		 
		 enderecoRepository.deleteById(idEndereco);
		 return new ResponseEntity<String>("Endereco deletado com sucesso!", HttpStatus.OK);
	 }
	 
	 //Busca endereço especifico pelo ID
	 @GetMapping(value = "buscar")
	 @ResponseBody
	 public ResponseEntity<Endereco> buscarEnderecoPorId(@RequestParam(name = "idEndereco") Long idEndereco){
		 
		 Endereco endereco = enderecoRepository.findById(idEndereco).get();
		 return new ResponseEntity<Endereco>(endereco, HttpStatus.OK);
	 }
	 
	 //Busca Endereço principal de determinada Pessoa
	 @GetMapping(value = "buscarPrincipal")
	 @ResponseBody
	 public ResponseEntity<?> buscarEnderecoPrincipalPorPessoa(@RequestParam(name = "idPessoa") Long idPessoa){
		 
		 Pessoa pessoa = pessoaRepository.findById(idPessoa).get();
		 Endereco enderecoPrincipal = new Endereco();
		 
		 for(int i = 0; i < pessoa.getEnderecos().size(); i++) {
			 Endereco endereco  = pessoa.getEnderecos().get(i);
			 if(endereco.isPrincipal() == true) {
				 enderecoPrincipal = endereco;
			 } 
		 }
		 
		 if(pessoa.getEnderecos().isEmpty()) {
			 return new ResponseEntity<String>("Esta Pessoa Não tem Nenhum endereço cadastrado!", HttpStatus.OK);
		 }
		 
		 if(enderecoPrincipal.getId() == null) {
			 return new ResponseEntity<String>("Esta Pessoa Não tem Nenhum endereço como Principal!", HttpStatus.OK);
		 }
		 
		 return new ResponseEntity<Endereco>(enderecoPrincipal, HttpStatus.OK);
	 }
	 
	 
}
