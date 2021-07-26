package br.com.brzupacademy.propostas.biometria;

import br.com.brzupacademy.propostas.proposta.Proposta;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class BiometriaRequest {

    @NotBlank
    private String biometria;

    public BiometriaRequest(String biometria) {
        this.biometria = biometria;
    }

    public BiometriaRequest(){
    }

    public String getBiometria() {
        return biometria;
    }

    public Biometria toModel(Proposta proposta){
        byte[] biometriaDecodificada = Base64.getDecoder().decode(this.biometria);
        return new Biometria(new String(biometriaDecodificada), proposta);
    }
}
