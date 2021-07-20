package br.com.brzupacademy.propostas.proposta;

public class PropostaResponse {

    private long id;
    private String nome;

    public PropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.nome = proposta.getNome();
    }

    @Deprecated
    public PropostaResponse(){}

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
