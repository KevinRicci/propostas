package br.com.brzupacademy.propostas.proposta.carteira;

import br.com.brzupacademy.propostas.proposta.Proposta;

import javax.validation.constraints.NotNull;

public class CarteiraDigitalRequest {

    @NotNull
    private Carteira carteira;

    public CarteiraDigitalRequest(Carteira carteira) {
        this.carteira = carteira;
    }

    @Deprecated
    public CarteiraDigitalRequest(){}

    public CarteiraDigital toModel(Proposta proposta){
        return new CarteiraDigital(carteira, proposta);
    }

    public Carteira getCarteira() {
        return carteira;
    }
}
