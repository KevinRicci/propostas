package br.com.brzupacademy.propostas.proposta.biometria;

import br.com.brzupacademy.propostas.proposta.Proposta;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String biometria;
    private LocalDateTime instante = LocalDateTime.now();
    @ManyToOne
    private Proposta proposta;

    public Biometria(String biometria, Proposta proposta) {
        this.biometria = biometria;
        this.proposta = proposta;
    }

    public long getId() {
        return id;
    }

    public String getBiometria() {
        return biometria;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public Proposta getProposta() {
        return proposta;
    }
}
