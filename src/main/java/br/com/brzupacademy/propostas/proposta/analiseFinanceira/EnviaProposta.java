package br.com.brzupacademy.propostas.proposta.analiseFinanceira;

import br.com.brzupacademy.propostas.proposta.EstadoAnaliseFinanceira;
import br.com.brzupacademy.propostas.proposta.Proposta;

public class EnviaProposta {

    private String idProposta;
    private String nome;
    private String documento;

    public EnviaProposta(Proposta proposta) {
        this.idProposta = proposta.getId().toString();
        this.nome = proposta.getNome();
        this.documento = proposta.getDocumento();
    }

    public String getIdProposta() {
        return idProposta;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }
}
