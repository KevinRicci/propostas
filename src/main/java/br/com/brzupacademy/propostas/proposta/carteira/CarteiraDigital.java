package br.com.brzupacademy.propostas.proposta.carteira;

import br.com.brzupacademy.propostas.proposta.Proposta;

import javax.persistence.*;

@Entity
public class CarteiraDigital {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(value = EnumType.STRING)
    private Carteira carteira;
    @ManyToOne
    private Proposta proposta;

    public CarteiraDigital(Carteira carteira, Proposta proposta) {
        this.carteira = carteira;
        this.proposta = proposta;
    }

    @Deprecated
    public CarteiraDigital(){}

    public long getId() {
        return id;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public Proposta getProposta() {
        return proposta;
    }
}
