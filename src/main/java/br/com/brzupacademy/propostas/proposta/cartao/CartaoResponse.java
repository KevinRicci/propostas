package br.com.brzupacademy.propostas.proposta.cartao;

import br.com.brzupacademy.propostas.proposta.Proposta;

import javax.validation.constraints.NotNull;

public class CartaoResponse {

    private String id;

    public CartaoResponse(){}

    public void associaPropostaCartao(@NotNull Proposta proposta){
        if(proposta != null && id != null){
            proposta.setIdCartao(id);
        }
    }

    public String getId() {
        return id;
    }
}
