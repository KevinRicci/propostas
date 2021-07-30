package br.com.brzupacademy.propostas.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
                                              @RequestBody Map<String, String> sistemaResponsavel);

    /**
     *
     * @param detalhesViagem espera chaves: "destino" e "validoAte"
     * @return chave "resultado"
     */
    @PostMapping("api/cartoes/{id}/avisos")
    public Map<String, Object> notificaAvisoViagem(@PathVariable(value = "id") String idCartao,
                                                   @RequestBody Map<String, Object> detalhesViagem);

    /**
     *
     * @param detalhesCarteira espera chaves: "email" e "carteira"
     * @return chaves "resultado" e "id"
     */
    @PostMapping("api/cartoes/{id}/carteiras")
    public Map<String, Object> cadastraCarteiraDigital(@PathVariable(value = "id") String idCartao,
                                                       @RequestBody Map<String, Object> detalhesCarteira);
}
