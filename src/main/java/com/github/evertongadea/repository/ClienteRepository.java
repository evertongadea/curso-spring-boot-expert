package com.github.evertongadea.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.evertongadea.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	

	
	/**
	 * query methods
	 */
	
	List<Cliente> findByNomeLike (String nome);
	
	List<Cliente> findByNomeOrIdCliente (String nome, Integer idCliente);
	
	@Query(value = "SELECT c FROM Cliente c WHERE c.nome LIKE :nome")
	List<Cliente> encontrarPorNome (@Param("nome") String nome);
	
//	@Query(value = "SELECT c FROM Cliente c LEFT JOIN FETCH c.pedidos WHERE c.idCliente = :idCliente")
	@Query(value = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente")
	Cliente findClienteComPedidos(@Param("idCliente") Integer idCliente);

	Optional<Cliente> findByIdCliente(Integer idCliente);
	
	
	
	
	
	
	
	/**
	 * Exemplo utilizando JDBC
	 */
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//    public void persistir(Cliente cliente) {
//    	String sqlInsert = "INSERT INTO CLIENTE (NOME) VALUES (?)";
//    	
//    	jdbcTemplate.update(sqlInsert, new Object[] {cliente.getNome()});
//    }
//    
//    public List<Cliente> consultarTodos() {
//    	String sqlSelect = "SELECT * FROM CLIENTE";
//    	
//    	return jdbcTemplate.query(sqlSelect, new RowMapper<Cliente>() {
//    		@Override
//    		public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
//    			return new Cliente(resultSet.getInt("id_Cliente"), resultSet.getString("nome"));
//    		}
//    	});
//    }
}
