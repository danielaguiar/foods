package com.gestaosimples.servico.config;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.gestaosimples.servico.services.DBService;
import com.gestaosimples.servico.services.mail.EmailService;
import com.gestaosimples.servico.services.mail.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instanciateDataBase() throws ParseException {
        dbService.instanciateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() throws ParseException {
        return new MockEmailService();
    }

}
