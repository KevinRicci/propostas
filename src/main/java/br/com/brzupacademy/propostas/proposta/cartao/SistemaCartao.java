package br.com.brzupacademy.propostas.proposta.cartao;

import br.com.brzupacademy.propostas.exception.ApiException;
import br.com.brzupacademy.propostas.proposta.Proposta;
import br.com.brzupacademy.propostas.proposta.avisoViagem.AvisoViagem;
import br.com.brzupacademy.propostas.proposta.carteira.CarteiraDigital;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    /**
     * @param proposta cartão já tem que estar presente
     * @return Retorna chave "resultado"
     */
    public Map<String, Object> bloqueiaCartaoDaProposta(@NotNull Proposta proposta, @NotBlank String sistemaResponsavel){
        Assert.isTrue(proposta != null && proposta.cartaoIsPresent() != false && sistemaResponsavel != null, "Proposta, cartão e sistema responsável não podem ser nulos");
        Map<String, String> sistema = new HashMap<>();
        sistema.put("sistemaResponsavel", sistemaResponsavel);
        try{
            Map<String, Object> retorno = clienteCartao.bloqueiaCartao(proposta.getIdCartao(), sistema);
            return retorno;
        }
        catch(FeignException exception){
           processaFeignException(exception, "bloquear cartão");
        }
        return null;
    }

    public Map<String, Object> avisoViagem(@NotNull AvisoViagem avisoViagem, @NotNull Proposta proposta){
        Assert.isTrue(avisoViagem != null && proposta != null && proposta.cartaoIsPresent() != false, "Aviso viagem, proposta e cartão não podem ser nulos");

        Map<String, Object> detalhesViagem = new HashMap<>();
        detalhesViagem.put("destino", avisoViagem.getDestino());
        detalhesViagem.put("validoAte", avisoViagem.getTerminoViagem());

        try{
            Map<String, Object> retorno = clienteCartao.notificaAvisoViagem(proposta.getIdCartao(), detalhesViagem);
            return retorno;
        }
        catch(FeignException exception){
            processaFeignException(exception, "notificar aviso viagem");
        }
        return null;
    }

    public Map<String, Object> cadastraCarteiraDigital(@NotNull CarteiraDigital carteiraDigital, @NotNull Proposta proposta){
        Assert.isTrue(carteiraDigital != null && proposta != null && proposta.cartaoIsPresent() != false, "Carteira digital, proposta e cartão não podem ser nulos");

        Map<String, Object> detalhesCarteira = new HashMap<>();
        detalhesCarteira.put("email", proposta.getEmail());
        detalhesCarteira.put("carteira", carteiraDigital.getCarteira().toString());

        try{
            Map<String, Object> retorno = clienteCartao.cadastraCarteiraDigital(proposta.getIdCartao(), detalhesCarteira);
            return retorno;
        }
        catch(FeignException exception){
            processaFeignException(exception, "Cadastrar carteira digital");
        }
        return null;
    }

    private void processaFeignException(FeignException exception, String operacao){
        if(exception.status() >= 400 && exception.status() <= 499){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Erro ao "+operacao);
        }
        else throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno ao "+operacao);
    }
}
