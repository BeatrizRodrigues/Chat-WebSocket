package br.com.chat.bigChatBrasil.util.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage implements Serializable {

    private String erro;
    private String descricao;
    private List<ErrorMessage> detalhes;

    public ErrorMessage(String titulo) {
        this.erro = titulo;
    }

    public ErrorMessage(String titulo, List<ErrorMessage> detalhes) {
        this.erro = titulo;
        this.detalhes = detalhes;
    }

    public ErrorMessage(String titulo, String descricao) {
        this.erro = titulo;
        this.descricao = descricao;
    }

    public ErrorMessage() {
    }

    public void addError(final ErrorMessage error) {
        if (isNull(this.detalhes)) {
            this.detalhes = new ArrayList<>();
        }

        this.detalhes.add(error);
    }

}
