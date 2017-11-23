package com.gestaosimples.servico.services.mail;

import org.springframework.mail.SimpleMailMessage;
import com.gestaosimples.servico.domain.Pedido;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage msg);
}
