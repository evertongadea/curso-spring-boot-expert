package com.github.evertongadea;

import com.github.evertongadea.service.DevProfile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//@Configuration
//@Profile("dev")
@DevProfile
public class AplicacaoConfig {

    @Bean(name = "nomeAplicacao")
    public String NomeAplicacao() {
        return "Sistema de Vendas - Ambiente de desenvolvimento";
    }
}
