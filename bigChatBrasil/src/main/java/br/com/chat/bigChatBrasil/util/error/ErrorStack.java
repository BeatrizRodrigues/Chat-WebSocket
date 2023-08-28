package br.com.chat.bigChatBrasil.util.error;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Getter
@Component
@RequestScope
public class ErrorStack {
    private final ErrorMessage falha;

    public ErrorStack() {
        this.falha = new ErrorMessage();
    }

    public void addError(final ErrorMessage error) {
        falha.addError(error);
    }
}
