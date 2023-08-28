package br.com.chat.bigChatBrasil.api.controller;

import br.com.chat.bigChatBrasil.domain.mensagem.Mensagem;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class MensagemController {

    @MessageMapping("/send")
    @SendTo("/topic/public")
    public Mensagem sendMessage(@Payload Mensagem mensagem)
            throws Exception {

        final var dataAtual = LocalDateTime.now();
        var chat = mensagem.toBuilder()
                .date(dataAtual)
                .build();

        return chat;
    }


}
