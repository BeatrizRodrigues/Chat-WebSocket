package br.com.chat.bigChatBrasil.api.controller;

import br.com.chat.bigChatBrasil.api.app.cliente.dto.UpdateCreditoDTO;
import br.com.chat.bigChatBrasil.api.app.cliente.dto.UpdateLimiteDTO;
import br.com.chat.bigChatBrasil.api.app.cliente.dto.UpdatePlanoDTO;
import br.com.chat.bigChatBrasil.domain.Plano;
import br.com.chat.bigChatBrasil.domain.cliente.Cliente;
import br.com.chat.bigChatBrasil.domain.cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin({"*"})
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody final Cliente dto) {
        log.info("c=ClienteController, m=salvar, dto={}", dto);

        final var resultado = service.salvarCliente(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> buscarPorID(@PathVariable Long id) {
        log.info("c=ClienteController, m=buscarPorID, id={}", id);

        final var resultado = service.buscarClientePorId(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodos() {
        log.info("c=ClienteController, m=buscarTodos");

        final var resultado = service.buscarTodosClientes();

        return new ResponseEntity<>(resultado, OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id,  @RequestBody final Cliente cliente) {
        log.info("c=ClienteController, m=atualizar, id={}, cliente={}", id, cliente);

        final var resultado = service.atualizarCliente(id, cliente);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cliente> deletarPorID(@PathVariable Long id) {
        log.info("c=ClienteController, m=deletarPorID, id={}", id);

        final var resultado = service.deletarCliente(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @PutMapping(value = "/incluirCreditos/id/{id}")
    public ResponseEntity<Cliente> incluirCreditos(@PathVariable Long id, @RequestBody final UpdateCreditoDTO dto) {
        log.info("c=ClienteController, m=incluirCreditos, id={}, credito={}", id, dto);

        final var resultado = service.incluirCreditos(id, dto.getCredito());

        return new ResponseEntity<>(resultado, OK);
    }

    @PutMapping(value = "/alterarLimite/id/{id}")
    public ResponseEntity<Cliente> alterarLimite(@PathVariable Long id, @PathVariable final UpdateLimiteDTO dto) {
        log.info("c=ClienteController, m=alterarLimite, id={}, limite={}", id, dto);

        final var resultado = service.alterarLimite(id, dto.getLimite());

        return new ResponseEntity<>(resultado, OK);
    }

    @PutMapping(value = "/alterarPlano/id/{id}")
    public ResponseEntity<Cliente> alterarPlano(@PathVariable Long id,  @RequestBody final UpdatePlanoDTO dto) {
        log.info("c=ClienteController, m=alterarPlano, id={}, limite={}", id, dto);

        final var resultado = service.alterarPlano(id, dto.getPlano());

        return new ResponseEntity<>(resultado, OK);
    }
}
