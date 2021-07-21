package br.com.brzupacademy.propostas.proposta.analiseFinanceira;

import br.com.brzupacademy.propostas.proposta.PropostaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "analise-financeira", url = "${analise.financeira.url}")
public interface ClienteAnaliseFinanceira {

    @PostMapping("api/solicitacao")
    AnaliseFinanceiraResponse enviaProposta(EnviaProposta enviaProposta);
}
