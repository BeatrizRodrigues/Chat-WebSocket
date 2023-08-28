package br.com.chat.bigChatBrasil.domain.cliente;

import br.com.chat.bigChatBrasil.domain.Plano;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_CLIENTE")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private Double creditos;
    private Long limite;
    private String cpf;
    private String cnpj;
    private String nomeEmpresa;
    private Plano plano;

}
