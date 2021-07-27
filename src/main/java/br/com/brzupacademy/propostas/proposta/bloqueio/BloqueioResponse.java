package br.com.brzupacademy.propostas.proposta.bloqueio;

public class BloqueioResponse {

    private String ipOrigem;
    private String userAgentOrigem;
    private long propostaCartaoId;

    public BloqueioResponse(Bloqueio bloqueio) {
        this.ipOrigem = bloqueio.getIpOrigem();
        this.userAgentOrigem = bloqueio.getUserAgentOrigem();
        this.propostaCartaoId = bloqueio.getPropostaId();
    }

    public String getIpOrigem() {
        return ipOrigem;
    }

    public String getUserAgentOrigem() {
        return userAgentOrigem;
    }

    public long getPropostaCartaoId() {
        return propostaCartaoId;
    }
}
