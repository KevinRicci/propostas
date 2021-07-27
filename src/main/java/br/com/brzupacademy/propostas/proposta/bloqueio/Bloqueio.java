package br.com.brzupacademy.propostas.proposta.bloqueio;

import br.com.brzupacademy.propostas.proposta.Proposta;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String ipOrigem;
    @NotBlank
    private String userAgentOrigem;
    private LocalDateTime instanteBloqueio = LocalDateTime.now();
    @OneToOne
    @JoinColumn(unique = true)
    private Proposta proposta;

    public Bloqueio(String ipOrigem, String userAgentOrigem, Proposta proposta) {
        this.ipOrigem = ipOrigem;
        this.userAgentOrigem = userAgentOrigem;
        this.proposta = proposta;
    }

    @Deprecated
    public Bloqueio(){}

    public long getId() {
        return id;
    }

    public String getIpOrigem() {
        return ipOrigem;
    }

    public String getUserAgentOrigem() {
        return userAgentOrigem;
    }

    public LocalDateTime getInstanteBloqueio() {
        return instanteBloqueio;
    }

    public long getPropostaId() {
        return proposta.getId();
    }
}
