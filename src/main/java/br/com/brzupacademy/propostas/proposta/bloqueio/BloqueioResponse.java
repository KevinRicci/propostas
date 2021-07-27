package br.com.brzupacademy.propostas.proposta.bloqueio;

import java.time.LocalDateTime;

public class BloqueioResponse {

    private String ipOrigem;
    private String userAgentOrigem;
    private LocalDateTime instanteBloqueio;
    private long propostaCartaoId;

    public BloqueioResponse(Bloqueio bloqueio) {
        this.ipOrigem = bloqueio.getIpOrigem();
        this.userAgentOrigem = bloqueio.getUserAgentOrigem();
        this.instanteBloqueio = bloqueio.getInstanteBloqueio();
        this.propostaCartaoId = bloqueio.getPropostaId();
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

    public long getPropostaCartaoId() {
        return propostaCartaoId;
    }
}
