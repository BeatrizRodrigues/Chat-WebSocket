package br.com.chat.bigChatBrasil.infra.cliente;

import br.com.chat.bigChatBrasil.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteJPARepository extends JpaRepository<Cliente, Long> {

}
