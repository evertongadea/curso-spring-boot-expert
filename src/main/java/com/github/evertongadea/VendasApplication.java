package com.github.evertongadea;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.evertongadea.model.Cliente;
import com.github.evertongadea.repository.ClienteRepository;

@SpringBootApplication
@RestController
public class VendasApplication {
	
//	@Autowired
//	@Qualifier("nomeAplicacao")
//	private String nomeAplicacao;

    @Value("${application.name}")
    private String nomeAplicacao;

    @GetMapping("/hello")
    public String helloWorld() {
        return nomeAplicacao;
    }
    
    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {
    	return args -> {
    		clienteRepository.persistir(new Cliente("Everton"));
    		clienteRepository.persistir(new Cliente("Paulo"));
    		clienteRepository.persistir(new Cliente("João"));
    		
    		List<Cliente> clientes = clienteRepository.consultarTodos();
    		clientes.forEach(System.out::println);
    	};
    }


    public static void main(String[] args) {
    	SpringApplication.run(VendasApplication.class, args);
    }
}