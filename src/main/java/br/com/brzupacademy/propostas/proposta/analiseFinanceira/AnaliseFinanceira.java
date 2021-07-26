package br.com.brzupacademy.propostas.proposta.analiseFinanceira;

import br.com.brzupacademy.propostas.exception.ApiException;
import br.com.brzupacademy.propostas.proposta.Proposta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AnaliseFinanceira {

    @Autowired
    private ClienteAnaliseFinanceira clienteAnaliseFinanceira;

    public AnaliseFinanceiraResponse enviaParaAnalise(Proposta proposta) {
        try {
            AnaliseFinanceiraResponse analise = clienteAnaliseFinanceira.enviaProposta(new EnviaProposta(proposta));
            return analise;
        }catch (FeignException.UnprocessableEntity exception){
            String content = exception.contentUTF8();
            try {
                AnaliseFinanceiraResponse response = new ObjectMapper().readValue(content, AnaliseFinanceiraResponse.class);
                return response;
            } catch (JsonProcessingException e) {
                throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao processar JSON de retorno");
            }
        }
    }
}
