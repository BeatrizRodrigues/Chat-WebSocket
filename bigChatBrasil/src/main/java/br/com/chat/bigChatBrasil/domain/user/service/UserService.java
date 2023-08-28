package br.com.chat.bigChatBrasil.domain.user.service;

import br.com.chat.bigChatBrasil.domain.user.User;
import br.com.chat.bigChatBrasil.domain.user.infra.IUserRepository;
import br.com.chat.bigChatBrasil.util.error.ErrorMessage;
import br.com.chat.bigChatBrasil.util.error.ErrorStack;
import br.com.chat.bigChatBrasil.util.exception.NexusException;
import br.com.chat.bigChatBrasil.util.exception.NexusNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final IUserRepository repository;
    private final ErrorStack errorStack;

    @Override
    public UserDetails loadUserByUsername(String telefone) throws UsernameNotFoundException {
        log.debug("c=UserService, m=loadUserByUsername, telefone={}", telefone);

        final var buscaUser = repository.buscarPorTelefone(telefone);

        if (isNull(buscaUser)) {
            NexusNotFoundException.of("Usuario não encontrado.");
        }

        return buscaUser;
    }

    public User buscarUserPorTelefone(final String telefone) {
        log.debug("c=UserService, m=buscarUserPorTelefone, telefone={}", telefone);

        final var buscaUser = repository.buscarPorTelefone(telefone);

        return buscaUser;

    }

    public User salvarUser(final User user) {
        log.debug("c=UserService, m=salvarUser, user={}", user);

        if (!this.validar(user)) {
            log.error("Falha ao salvar Usuario {}", user);
            NexusException.of("Falha ao salvar Usuario", errorStack.getFalha());
        }

        final var usuarioSalvo = repository.salvar(user);
        log.debug("Usuario {} salvo com sucesso", usuarioSalvo);

        return usuarioSalvo;
    }

    private boolean validar(User user) {
        log.debug("c=UserService, m=validar, user={}", user);

        var validado = TRUE;

        if (isNull(user.getTelefone())) {
            log.error("Telefone é Obrigatório [{}]", user);
            errorStack.addError(new ErrorMessage("Telefone é obrigatório"));

            validado = FALSE;
        }

        if (isNull(user.getPassword())) {
            log.error("Password é Obrigatório [{}]", user);
            errorStack.addError(new ErrorMessage("Password é obrigatório"));

            validado = FALSE;
        }

        if (isNull(user.getTipoUser())) {
            log.error("Tipo de usuario é Obrigatório [{}]", user);
            errorStack.addError(new ErrorMessage("Tipo de usuario é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }
}
