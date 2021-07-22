package br.com.brzupacademy.propostas.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;

@FeignClient(name = "cartao", url = "${cartao.url}")
public interface ClienteCartao {

    @GetMapping("api/cartoes")
    public CartaoResponse buscaCartao(@RequestParam("idProposta")String idProposta);
}
