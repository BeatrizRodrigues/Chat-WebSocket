package br.com.chat.bigChatBrasil.api.app.user.dto;

import br.com.chat.bigChatBrasil.domain.TipoUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {

    private String telefone;
    private String password;
    private TipoUser tipoUser;

}
