package br.com.chat.bigChatBrasil.domain.cliente.infra;

import br.com.chat.bigChatBrasil.domain.cliente.Cliente;

import java.util.List;

public interface IClienteRepository {

    Cliente salvarCliente(final Cliente cliente);

    Cliente atualizarCliente(final Cliente cliente);

    Cliente deletarCliente(final Cliente cliente);

    Cliente incluirCreditos(final Cliente cliente);

    Cliente consultarCreditos(final Long id);

    Cliente alterarLimite(final Cliente cliente);

    Cliente alterarPlano(final Cliente cliente);

    Cliente buscarClientePorId(final Long id);

    List<Cliente> buscarTodosClientes();

}
