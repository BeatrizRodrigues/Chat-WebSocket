package br.com.chat.bigChatBrasil.infra.cliente;

import br.com.chat.bigChatBrasil.domain.cliente.Cliente;
import br.com.chat.bigChatBrasil.domain.cliente.infra.IClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.Objects.nonNull;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ClienteRepository implements IClienteRepository {

    private final IClienteJPARepository repository;

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        log.debug("c=ClienteRepository, m=salvarCliente, cliente={}", cliente);
        return repository.save(cliente);
    }

    @Override
    public Cliente atualizarCliente(Cliente cliente) {
        log.debug("c=ClienteRepository, m=atualizarCliente, cliente={}", cliente);
        return repository.save(cliente);
    }

    @Override
    public Cliente deletarCliente(Cliente cliente) {
        log.debug("c=ClienteRepository, m=deletarCliente, cliente={}", cliente);
        repository.delete(cliente);
        return cliente;
    }

    @Override
    public Cliente incluirCreditos(Cliente cliente) {
        log.debug("c=ClienteRepository, m=incluirCreditos, cliente={}", cliente);
        return repository.save(cliente);
    }

    @Override
    public Cliente consultarCreditos(Long id) {
        log.debug("c=ClienteRepository, m=consultarCreditos, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Cliente alterarLimite(Cliente cliente) {
        log.debug("c=ClienteRepository, m=alterarLimite, cliente={}", cliente);
        return repository.save(cliente);
    }

    @Override
    public Cliente alterarPlano(Cliente cliente) {
        log.debug("c=ClienteRepository, m=alterarPlano, cliente={}", cliente);
        return repository.save(cliente);
    }

    @Override
    public Cliente buscarClientePorId(Long id) {
        log.debug("c=ClienteRepository, m=buscarClientePorId, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Cliente> buscarTodosClientes() {
        log.debug("c=ClienteRepository, m=buscarTodosClientes");
        return repository.findAll();
    }
}
