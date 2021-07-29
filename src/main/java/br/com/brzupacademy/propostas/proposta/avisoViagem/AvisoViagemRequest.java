package br.com.brzupacademy.propostas.proposta.avisoViagem;

import br.com.brzupacademy.propostas.proposta.Proposta;
import br.com.brzupacademy.propostas.utils.ClientRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @NotBlank
    private String destino;
    @NotNull @Future @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate terminoViagem;

    public AvisoViagemRequest(String destino, LocalDate terminoViagem) {
        this.destino = destino;
        this.terminoViagem = terminoViagem;
    }

    @Deprecated
    public AvisoViagemRequest(){}

    public AvisoViagem toModel(Proposta propostaComCartao, HttpServletRequest httpServletRequest){
        return new AvisoViagem(this.destino,
                this.terminoViagem,
                ClientRequest.ipAdress(httpServletRequest),
                httpServletRequest.getHeader("user-agent"),
                propostaComCartao);
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }
}
