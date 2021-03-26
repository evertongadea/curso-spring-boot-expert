package com.github.evertongadea;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AplicacaoConfig {

    @Bean(name = "nomeAplicacao")
    public String NomeAplicacao() {
        return "Sistema de Vendas";
    }
}
