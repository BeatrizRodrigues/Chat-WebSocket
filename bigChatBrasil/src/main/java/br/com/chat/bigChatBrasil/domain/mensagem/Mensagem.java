package br.com.chat.bigChatBrasil.domain.mensagem;

import br.com.chat.bigChatBrasil.domain.TipoMensagem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Mensagem {

    private TipoMensagem tipoMensagem;
    private String mensagem;
    private String remetente;
    private LocalDateTime date;

}
