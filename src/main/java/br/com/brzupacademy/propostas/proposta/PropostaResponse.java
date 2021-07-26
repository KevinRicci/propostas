package br.com.brzupacademy.propostas.proposta;

import java.util.UUID;

public class PropostaResponse {

    private String id;
    private String nome;
    private EstadoAnaliseFinanceira estadoAnaliseFinanceira;

    public PropostaResponse(Proposta proposta) {
        this.id = proposta.getId().toString();
        this.nome = proposta.getNome();
        this.estadoAnaliseFinanceira = proposta.getEstadoAnaliseFinanceira();
    }

    @Deprecated
    public PropostaResponse(){}

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public EstadoAnaliseFinanceira getEstadoAnaliseFinanceira() {
        return estadoAnaliseFinanceira;
    }
}
