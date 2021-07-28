package br.com.brzupacademy.propostas.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "cartao", url = "${cartao.url}")
public interface ClienteCartao {

    @GetMapping("api/cartoes")
    public BuscaCartaoResponse buscaCartao(@RequestParam("idProposta")String idProposta);

    /**
     *
     * @param sistemaResponsavel Chave contendo String sistemaResponsavel e valor
     * @return Retorna chave resultado com valor BLOQUEADO para sucesso
     */
    @PostMapping("api/cartoes/{id}/bloqueios")
    public Map<String, Object> bloqueiaCartao(@PathVariable(value = "id") String idCartao,
                                              Map<String, String> sistemaResponsavel);
}
