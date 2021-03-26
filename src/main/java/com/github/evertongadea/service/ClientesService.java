package com.github.evertongadea.service;

import com.github.evertongadea.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.evertongadea.model.Cliente;

@Service
public class ClientesService {

    @Autowired
    private ClienteRepository repository;

    public  void savorCliente(Cliente cliente) {
        if (validarCliente(cliente))
            this.repository.persistir(cliente);
    }

    public boolean validarCliente (Cliente cliente) {
        //TODO validações
        return true;
    }
}
