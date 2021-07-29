package br.com.brzupacademy.propostas.proposta.avisoViagem;

import br.com.brzupacademy.propostas.proposta.Proposta;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String destino;
    @NotNull @Future
    private LocalDate terminoViagem;
    private LocalDateTime instanteDoAviso = LocalDateTime.now();
    @NotBlank
    private String ipOrigem;
    @NotBlank
    private String userAgentOrigem;
    @ManyToOne
    private Proposta proposta;

    public AvisoViagem(String destino, LocalDate terminoViagem, String ipOrigem, String userAgentOrigem, Proposta proposta) {
        this.destino = destino;
        this.terminoViagem = terminoViagem;
        this.ipOrigem = ipOrigem;
        this.userAgentOrigem = userAgentOrigem;
        this.proposta = proposta;
    }

    @Deprecated
    public AvisoViagem(){}

    public long getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }

    public LocalDateTime getInstanteDoAviso() {
        return instanteDoAviso;
    }

    public String getIpOrigem() {
        return ipOrigem;
    }

    public String getUserAgentOrigem() {
        return userAgentOrigem;
    }

    public Proposta getProposta() {
        return proposta;
    }
}
