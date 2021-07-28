package br.com.brzupacademy.propostas.proposta.cartao;

import br.com.brzupacademy.propostas.exception.ApiException;
import br.com.brzupacademy.propostas.proposta.Proposta;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SistemaCartao {

    private final Logger logger = LoggerFactory.getLogger(SistemaCartao.class);
    @Autowired
    private ClienteCartao clienteCartao;

    public void associaCartaoA(Proposta proposta){
        try {
            BuscaCartaoResponse cartao = clienteCartao.buscaCartao(String.valueOf(proposta.getId()));
            cartao.associaPropostaCartao(proposta);
        }catch(FeignException exception){
            logger.info("Cartão para proposta: "+ proposta.getId()+ ", ainda não gerado");
        }
    }

    public Map<String, Object> bloqueiaCartaoDaProposta(Proposta proposta, String sistemaResponsavel){
        Map<String, String> sistema = new HashMap<>();
        sistema.put("sistemaResponsavel", sistemaResponsavel);
        try{
            Map<String, Object> retorno = clienteCartao.bloqueiaCartao(proposta.getIdCartao(), sistema);
            return retorno;
        }
        catch(FeignException exception){
            if(exception.status() >= 400 && exception.status() <= 499){
                throw new ApiException(HttpStatus.BAD_REQUEST, "Erro ao bloquear cartão");
            }
            else throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno ao bloquear cartão");
        }
    }
}