package br.com.brzupacademy.propostas.proposta.cartao;

import br.com.brzupacademy.propostas.proposta.EstadoAnaliseFinanceira;
import br.com.brzupacademy.propostas.proposta.Proposta;
import br.com.brzupacademy.propostas.proposta.PropostaRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableScheduling
public class ScheduledCartao {

    private final Logger logger = LoggerFactory.getLogger(ScheduledCartao.class);
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private ClienteCartao clienteCartao;

    /**
     * Busca as proposta com estado ELEGIVEL no sistema e no sistema de cartões
     * faz a busca pelo cartão gerado, se já estiver, associa-o à proposta.
     */

    @Transactional
    @Scheduled(fixedRate = 60000)
    public void associaPropostaCartao(){
        Optional<List<Proposta>> propostasSemCartao =
                propostaRepository.findByEstadoAnaliseFinanceiraAndIdCartaoNull(EstadoAnaliseFinanceira.ELEGIVEL);

        if(propostasSemCartao.isPresent()){
            propostasSemCartao.get().forEach(proposta ->{
                try {
                    CartaoResponse cartao = clienteCartao.buscaCartao(String.valueOf(proposta.getId()));
                    cartao.associaPropostaCartao(proposta);
                }catch(FeignException exception){
                    logger.info("Aguardando próxima interação");
                }
            });
        }
    }
}
