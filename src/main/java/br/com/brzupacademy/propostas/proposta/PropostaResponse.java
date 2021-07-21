package br.com.brzupacademy.propostas.proposta;

public class PropostaResponse {

    private long id;
    private String nome;
    private EstadoAnaliseFinanceira estadoAnaliseFinanceira;

    public PropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.nome = proposta.getNome();
        this.estadoAnaliseFinanceira = proposta.getEstadoAnaliseFinanceira();
    }

    @Deprecated
    public PropostaResponse(){}

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public EstadoAnaliseFinanceira getEstadoAnaliseFinanceira() {
        return estadoAnaliseFinanceira;
    }
}
