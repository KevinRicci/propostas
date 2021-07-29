package br.com.brzupacademy.propostas.proposta.avisoViagem;

import br.com.brzupacademy.propostas.exception.ApiException;
import br.com.brzupacademy.propostas.proposta.Proposta;
import br.com.brzupacademy.propostas.proposta.PropostaRepository;
import br.com.brzupacademy.propostas.proposta.cartao.SistemaCartao;
import br.com.brzupacademy.propostas.utils.ClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class AvisoViagemController {

    @Autowired
    private AvisoViagemRepository avisoViagemRepository;
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private SistemaCartao sistemaCartao;

    @PostMapping("/{id}/cartoes/avisos-viagens")
    @Transactional
    public ResponseEntity<?> criaAvisoViagem(@PathVariable long id,
                                             @RequestBody @Valid AvisoViagemRequest avisoViagemRequest,
                                             HttpServletRequest httpServletRequest,
                                             JwtAuthenticationToken token){
        Optional<Proposta> possivelProposta = propostaRepository.findById(id);
        if(possivelProposta.isPresent() && possivelProposta.get().cartaoIsPresent()){
            Proposta propostaComCartao = possivelProposta.get();
            propostaComCartao.pertenceAoUsuario(token);
            AvisoViagem avisoViagem = avisoViagemRequest.toModel(propostaComCartao, httpServletRequest);

            sistemaCartao.avisoViagem(avisoViagem, propostaComCartao);
            avisoViagemRepository.save(avisoViagem);

            return ResponseEntity.ok().build();
        }
        else throw new ApiException(HttpStatus.NOT_FOUND, "Id da proposta do cartão não encontrado ou cartão ainda não gerado");
    }
}
