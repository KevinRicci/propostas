package br.com.brzupacademy.propostas.proposta.analiseFinanceira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "analise-financeira", url = "${analise.financeira.url}")
public interface ClienteAnaliseFinanceira {

    @PostMapping("api/solicitacao")
    EnviaPropostaResponse enviaProposta(EnviaProposta enviaProposta);
}
