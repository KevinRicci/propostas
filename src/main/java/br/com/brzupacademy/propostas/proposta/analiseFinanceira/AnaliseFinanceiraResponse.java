package br.com.brzupacademy.propostas.proposta.analiseFinanceira;

public class AnaliseFinanceiraResponse {

    private String documento;
    private String nome;
    private String idProposta;
    private StatusAnalise resultadoSolicitacao;

    public AnaliseFinanceiraResponse(String documento, String nome, String idProposta, StatusAnalise resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    @Deprecated
    public AnaliseFinanceiraResponse(){}

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public StatusAnalise getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
