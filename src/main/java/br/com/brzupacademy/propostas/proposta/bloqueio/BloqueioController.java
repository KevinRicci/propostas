package br.com.brzupacademy.propostas.proposta.bloqueio;

import br.com.brzupacademy.propostas.exception.ApiException;
import br.com.brzupacademy.propostas.proposta.Proposta;
import br.com.brzupacademy.propostas.proposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class BloqueioController {

    @Autowired
    private BloqueioRepository bloqueioRepository;
    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping("/{idPropostaCartao}/cartao/bloqueio")
    @Transactional
    public ResponseEntity<BloqueioResponse> bloqueiaCartao(@PathVariable long idPropostaCartao,
                                                           HttpServletRequest httpServletRequest,
                                                           UriComponentsBuilder uriComponentsBuilder,
                                                           JwtAuthenticationToken jwtAuthenticationToken){

        Optional<Proposta> possivelPropostaComCartao = propostaRepository.findById(idPropostaCartao);
        if(possivelPropostaComCartao.isPresent() && possivelPropostaComCartao.get().cartaoIsPresent()){
            possivelPropostaComCartao.get().pertenceAoUsuario(jwtAuthenticationToken);
            if(bloqueioRepository.findByProposta_Id(idPropostaCartao).isEmpty()) {

                Bloqueio bloqueio = new Bloqueio(
                        getClientIpAdress(httpServletRequest),
                        httpServletRequest.getHeader("user-agent"),
                        possivelPropostaComCartao.get());

                bloqueioRepository.save(bloqueio);

                URI uri = uriComponentsBuilder.path("/propostas/{idPropostaCartao}/cartao/bloqueio/{id}")
                        .buildAndExpand(possivelPropostaComCartao.get().getId(), bloqueio.getId()).toUri();

                return ResponseEntity.created(uri).body(new BloqueioResponse(bloqueio));
            }
            else throw new ApiException(HttpStatus.UNPROCESSABLE_ENTITY, "O cartão já está bloqueado");
        }
        else throw new ApiException(HttpStatus.NOT_FOUND, "Id da proposta do cartão não encontrado ou cartão ainda não foi gerado");
    }

    private String getClientIpAdress(HttpServletRequest httpServletRequest){
        String ip = httpServletRequest.getHeader("X-FORWARDED-FOR");
        if (ip != null) {
            String ipClient = ip.contains(",") ? ip.split(",")[0] : ip;
            return ipClient;
        }
        else{
            return httpServletRequest.getRemoteAddr();
        }
    }
}
