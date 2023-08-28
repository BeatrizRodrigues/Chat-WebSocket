package br.com.chat.bigChatBrasil.infra.user;

import br.com.chat.bigChatBrasil.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserJPARepository extends JpaRepository<User, Long> {

    User findByTelefone(final String telefone);

}
