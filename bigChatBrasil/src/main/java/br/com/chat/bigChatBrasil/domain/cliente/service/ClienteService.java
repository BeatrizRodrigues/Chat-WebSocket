package br.com.chat.bigChatBrasil.domain.cliente.service;

import br.com.chat.bigChatBrasil.domain.Plano;
import br.com.chat.bigChatBrasil.domain.cliente.Cliente;
import br.com.chat.bigChatBrasil.domain.cliente.infra.IClienteRepository;
import br.com.chat.bigChatBrasil.util.error.ErrorMessage;
import br.com.chat.bigChatBrasil.util.error.ErrorStack;
import br.com.chat.bigChatBrasil.util.exception.NexusException;
import br.com.chat.bigChatBrasil.util.exception.NexusNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClienteService {

    private final IClienteRepository repository;
    private final ErrorStack errorStack;

    public Cliente salvarCliente(final Cliente cliente) {
        log.debug("c=ClienteService, m=salvarCliente, cliente={}", cliente);

        if (!this.validar(cliente)) {
            log.error("Falha ao salvar Cliente {}", cliente);
            NexusException.of("Falha ao salvar Cliente", errorStack.getFalha());
        }

        final var clienteSalvo = repository.salvarCliente(cliente);
        log.debug("Cliente {} salvo com sucesso", clienteSalvo);

        return clienteSalvo;
    }

    public Cliente buscarClientePorId(final Long id) {
        log.debug("c=ClienteService, m=buscarClientePorId, id={}", id);

        final var buscaCliente = repository.buscarClientePorId(id);

        if (isNull(buscaCliente)) {
            NexusNotFoundException.of("Cliente não encontrado.");
        }

        return buscaCliente;

    }

    public List<Cliente> buscarTodosClientes() {
        log.debug("c=AlcadaAprovacaoService, m=buscarTodosClientes");

        return repository.buscarTodosClientes();
    }

    public Cliente atualizarCliente(final Long id, final Cliente cliente) {
        log.debug("c=ClienteService, m=atualizarCliente, id={}, cliente={}", id, cliente);
        final var buscaCliente = this.buscarClientePorId(id);

        if(isNull(buscaCliente)) {
            NexusNotFoundException.of("Cliente não encontrado.");
        }

        if (!this.validar(cliente)) {
            log.error("Falha ao salvar Cliente {}", cliente);
            NexusException.of("Falha ao salvar Cliente", errorStack.getFalha());
        }

        final var clienteSalvo = repository.atualizarCliente(cliente);
        log.debug("Cliente {} atualizado com sucesso", clienteSalvo);

        return clienteSalvo;
    }

    public Cliente deletarCliente(final Long id) {
        log.debug("c=ClienteService, m=deletarCliente, id={}", id);
        final var resultado = repository.deletarCliente(this.buscarClientePorId(id));

        log.debug("Cliente {} excluído com sucesso", resultado);

        return resultado;
    }

    public Cliente incluirCreditos(final Long id, final Double credito) {
        log.debug("c=ClienteService, m=incluirCreditos, id={}", id);
        final var resultado = this.buscarClientePorId(id);

        if(isNull(resultado)) {
            NexusNotFoundException.of("Cliente não encontrado.");
        }

        if (resultado.getPlano().equals(Plano.POSPAGO)){
            NexusNotFoundException.of("Plano não compativel.");
        }

        resultado.setCreditos(resultado.getCreditos() + credito);
        final var clienteSalvo = repository.incluirCreditos(resultado);
        log.debug("Cliente {} salvo com sucesso", clienteSalvo);

        return clienteSalvo;
    }

    public Cliente consultarCreditos(final Long id) {
        log.debug("c=ClienteService, m=consultarCreditos, id={}", id);
        final var buscaCliente = this.buscarClientePorId(id);

        if (isNull(buscaCliente)) {
            NexusNotFoundException.of("Cliente não encontrado.");
        }

        return buscaCliente;
    }

    public Cliente alterarLimite(final Long id, final Long limite) {
        log.debug("c=ClienteService, m=alterarLimite, id={}, limite={}", id, limite);
        final var buscaCliente = this.buscarClientePorId(id);

        if(isNull(buscaCliente)) {
            NexusNotFoundException.of("Cliente não encontrado.");
        }

        if (buscaCliente.getPlano().equals(Plano.PREPAGO)){
            NexusNotFoundException.of("Plano não compativel.");
        }


        buscaCliente.setLimite(limite);
        final var clienteSalvo = repository.atualizarCliente(buscaCliente);
        log.debug("Limite {} atualizado com sucesso", clienteSalvo);

        return clienteSalvo;
    }

    public Cliente alterarPlano(final Long id, final Plano plano) {
        log.debug("c=ClienteService, m=alterarPlano, id={}, plano={}", id, plano);
        final var buscaCliente = this.buscarClientePorId(id);

        if (isNull(buscaCliente)) {
            NexusNotFoundException.of("Cliente não encontrado.");
        }

        buscaCliente.setPlano(plano);
        final var clienteSalvo = repository.alterarPlano(buscaCliente);
        log.debug("Plano {} atualizado com sucesso", clienteSalvo);

        return clienteSalvo;
    }

    private boolean validar(Cliente cliente) {
        log.debug("c=ClienteService, m=validar, cliente={}", cliente);

        var validado = TRUE;

        if (isNull(cliente.getNome())) {
            log.error("Nome é Obrigatório [{}]", cliente);
            errorStack.addError(new ErrorMessage("Nome é obrigatório"));

            validado = FALSE;
        }

        if (isNull(cliente.getEmail())) {
            log.error("E-mail é Obrigatório [{}]", cliente);
            errorStack.addError(new ErrorMessage("E-mail é obrigatório"));

            validado = FALSE;
        }

        if (isNull(cliente.getTelefone())) {
            log.error("Telefone é Obrigatório [{}]", cliente);
            errorStack.addError(new ErrorMessage("Telefone é obrigatório"));

            validado = FALSE;
        }

        if (isNull(cliente.getCreditos())) {
            log.error("Creditos é Obrigatório [{}]", cliente);
            errorStack.addError(new ErrorMessage("Creditos é obrigatório"));

            validado = FALSE;
        }

        if (isNull(cliente.getLimite())) {
            log.error("Limite é Obrigatório [{}]", cliente);
            errorStack.addError(new ErrorMessage("Limite é obrigatório"));

            validado = FALSE;
        }

        if (isNull(cliente.getCpf())) {
            log.error("CPF é Obrigatório [{}]", cliente);
            errorStack.addError(new ErrorMessage("CPF é obrigatório"));

            validado = FALSE;
        }

        if (isNull(cliente.getCnpj())) {
            log.error("CNPJ é Obrigatório [{}]", cliente);
            errorStack.addError(new ErrorMessage("CNPJ é obrigatório"));

            validado = FALSE;
        }

        if (isNull(cliente.getNomeEmpresa())) {
            log.error("Nome da empresa é Obrigatório [{}]", cliente);
            errorStack.addError(new ErrorMessage("Nome da empresa é obrigatório"));

            validado = FALSE;
        }

        if (isNull(cliente.getPlano())) {
            log.error("Plano é Obrigatório [{}]", cliente);
            errorStack.addError(new ErrorMessage("Plano é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }

}
