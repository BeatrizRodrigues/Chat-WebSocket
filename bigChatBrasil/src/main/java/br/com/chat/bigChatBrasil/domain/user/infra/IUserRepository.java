package br.com.chat.bigChatBrasil.domain.user.infra;

import br.com.chat.bigChatBrasil.domain.user.User;

public interface IUserRepository {

    User buscarPorTelefone(final String telefone);

    User salvar(final User user);

}
