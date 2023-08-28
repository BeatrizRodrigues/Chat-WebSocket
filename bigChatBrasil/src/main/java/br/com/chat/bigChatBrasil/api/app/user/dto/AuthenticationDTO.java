package br.com.chat.bigChatBrasil.api.app.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationDTO {
    private String telefone;
    private String password;
}
