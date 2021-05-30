package com.github.evertongadea.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.github.evertongadea.model.Cliente;

@Repository
public class ClienteRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

    public void persistir(Cliente cliente) {
    	String sqlInsert = "INSERT INTO CLIENTE (NOME) VALUES (?)";
    	
    	jdbcTemplate.update(sqlInsert, new Object[] {cliente.getNome()});
    }
    
    public List<Cliente> consultarTodos() {
    	String sqlSelect = "SELECT * FROM CLIENTE";
    	
    	return jdbcTemplate.query(sqlSelect, new RowMapper<Cliente>() {
    		@Override
    		public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
    			return new Cliente(resultSet.getInt("dddd"), resultSet.getString("nome"));
    		}
    	});
    }
}
