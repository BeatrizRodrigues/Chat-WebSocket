package br.com.chat.bigChatBrasil.infra.user;

import br.com.chat.bigChatBrasil.domain.user.User;
import br.com.chat.bigChatBrasil.domain.user.infra.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

    private final IUserJPARepository repository;

    @Override
    public User buscarPorTelefone(String telefone) {
        log.debug("c=UserRepository, m=buscarPorTelefone, telefone={}", telefone);
        return repository.findByTelefone(telefone);
    }

    @Override
    public User salvar(User user) {
        log.debug("c=UserRepository, m=salvar, user={}", user);
        return repository.save(user);
    }
}
