package com.github.evertongadea;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.evertongadea.model.Cliente;
import com.github.evertongadea.model.Produto;
import com.github.evertongadea.repository.ClienteRepository;
import com.github.evertongadea.repository.PedidoRepository;
import com.github.evertongadea.repository.ProdutoRepository;

@SpringBootApplication
@RestController
public class VendasApplication {
	
	@Autowired
	@Qualifier("nomeAplicacao")
	private String nomeAplicacao2;

    @Value("${application.name}")
    private String nomeAplicacao;

    @GetMapping("/hello")
    public String helloWorld() {
        return nomeAplicacao;
    }
    
    public static void main(String[] args) {
    	SpringApplication.run(VendasApplication.class, args);
    }
    
    
    
    
    @Bean
    public CommandLineRunner init(
    		@Autowired ClienteRepository clienteRepository,
    		@Autowired PedidoRepository pedidoRepository,
    		@Autowired ProdutoRepository produtoRepository) {
    	return args -> {
    		inserirClientes(clienteRepository);
    		inserirProdutos(produtoRepository);
//    		listarClientes(clienteRepository);
//    		inserirClienteComPedido(clienteRepository, pedidoRepository);
//    		consultarClientePorNome(clienteRepository);
//    		consultarClienteComPedido(clienteRepository, pedidoRepository);
    	};
    }
//
//	private void listarClientes(ClienteRepository clienteRepository) {
//		List<Cliente> clientes = clienteRepository.findAll();
//		clientes.forEach(System.out::println);
//	}
//
//	private void consultarClientePorNome(ClienteRepository clienteRepository) {
//		List<Cliente> listaCli1 = clienteRepository.findByNomeLike("Everton");
//		listaCli1.forEach(c -> System.out.println(c.getNome() + " encontrado!"));
//		
//		List<Cliente> listaCli2 = clienteRepository.encontrarPorNome("Paulo");
//		listaCli2.forEach(c -> System.out.println(c.getNome() + " encontrado!"));
//	}
//	
//	private void consultarClienteComPedido(ClienteRepository clienteRepository, PedidoRepository pedidoRepository) {
//		Cliente cliente = clienteRepository.findClienteComPedidos(4);
//		System.out.println("Cliente " + cliente.getNome());
//				
//		List<Pedido> pedidos= pedidoRepository.findByCliente(cliente);
//		pedidos.forEach(System.out::println);
//	}
//	
	private void inserirClientes(ClienteRepository clienteRepository) {
		clienteRepository.save(new Cliente("Paulo", "1232344456"));
		clienteRepository.save(new Cliente("João"));
		clienteRepository.save(new Cliente("Pedro", "11222222343"));
	}
	
	private void inserirProdutos(ProdutoRepository produtoRepository) {
		produtoRepository.save(new Produto("Chapéu", new BigDecimal(10)));
		produtoRepository.save(new Produto("Camisate", new BigDecimal(50)));
	}
//	
//	private void inserirClienteComPedido(ClienteRepository clienteRepository, PedidoRepository pedidoRepository) {
//		Cliente cliente = new Cliente("Everton");
//		clienteRepository.save(cliente);
//		
//		Pedido pedido = new Pedido();
//		pedido.setCliente(cliente);
//		pedido.setDataPedido(LocalDate.now());
//		pedido.setTotal(BigDecimal.valueOf(100));
//		pedidoRepository.save(pedido);
//	}
	

   
}