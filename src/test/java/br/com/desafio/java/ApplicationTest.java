package br.com.desafio.java;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.desafio.java.controllers.EnderecoController;
import br.com.desafio.java.controllers.PessoaController;
import br.com.desafio.java.model.Endereco;
import br.com.desafio.java.model.Pessoa;
import br.com.desafio.java.repository.EnderecoRepository;
import br.com.desafio.java.repository.PessoaRepository;

@SpringBootTest
public class ApplicationTest {
  
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	

	private EnderecoController enderecoController;
	
	private PessoaController pessoaController;
	
	private Pessoa pessoa;
	
	private Endereco endereco;
	
	
	//Testando a classe principal da aplicação
	@Test
	public void main() {
		Application.main(new String[] {});
	}

    @Test
    public void criaPessoa() throws Exception {

    }


    @Test
    public void editarPessoa() throws Exception {
   

    }


    @Test
    public void consultarPessoa() throws Exception {

    }
    
    @Test
    public void listarPessoa() throws Exception {

    }
    
    @Test
    public void criarEndereco() throws Exception {

    }
    
    @Test
    public void listaEnderecoPorPessoa() throws Exception {

    }
    
    @Test
    public void informaEnderecoPrincipalPorPessoa() throws Exception {

    }
}
