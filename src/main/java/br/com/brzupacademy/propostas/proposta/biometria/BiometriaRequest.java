package br.com.brzupacademy.propostas.proposta.biometria;

import br.com.brzupacademy.propostas.exception.ApiException;
import br.com.brzupacademy.propostas.proposta.Proposta;
import org.springframework.http.HttpStatus;

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
        try {
            byte[] biometriaDecodificada = Base64.getDecoder().decode(this.biometria);
            return new Biometria(new String(biometriaDecodificada), proposta);
        }
        catch(IllegalArgumentException exception){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Biometria não está encodada em Base64");
        }
    }
}
