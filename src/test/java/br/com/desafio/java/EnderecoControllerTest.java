package br.com.desafio.java;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.desafio.java.controllers.EnderecoController;
import br.com.desafio.java.model.Endereco;
import br.com.desafio.java.model.Pessoa;
import br.com.desafio.java.repository.EnderecoRepository;
import br.com.desafio.java.repository.PessoaRepository;

@SpringBootTest
class EnderecoControllerTest {
	
	@InjectMocks
	private EnderecoController enderecoController;
	
	@Mock
	private PessoaRepository pessoaRepository;
	
	@Mock
	private EnderecoRepository enderecoRepository;
	
	private Pessoa pessoa;
	
	private Endereco endereco;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
	}
	
	private void startPessoa() {
		pessoa = new Pessoa();
	}
	
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
