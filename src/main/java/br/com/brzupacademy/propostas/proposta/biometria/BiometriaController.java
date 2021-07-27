package br.com.brzupacademy.propostas.proposta.biometria;

import br.com.brzupacademy.propostas.exception.ApiException;
import br.com.brzupacademy.propostas.proposta.Proposta;
import br.com.brzupacademy.propostas.proposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class BiometriaController {

    @Autowired
    private BiometriaRepository biometriaRepository;
    @Autowired
    private PropostaRepository propostaRepository;

    @Transactional
    @PostMapping("/{idPropostaCartao}/cartao/biometria")
    public ResponseEntity<?> cadastra(JwtAuthenticationToken jwtAuthenticationToken, @PathVariable long idPropostaCartao, @RequestBody @Valid BiometriaRequest biometriaRequest, UriComponentsBuilder uriComponentsBuilder){
        Optional<Proposta> possivelPropostaComCartao = propostaRepository.findById(idPropostaCartao);
        if(possivelPropostaComCartao.isPresent() && possivelPropostaComCartao.get().cartaoIsPresent()) {
            possivelPropostaComCartao.get().pertenceAoUsuario(jwtAuthenticationToken);
            Biometria biometria = biometriaRequest.toModel(possivelPropostaComCartao.get());
            biometriaRepository.save(biometria);

            URI uri = uriComponentsBuilder.path("/propostas/{idPropostaCartao}/cartao/biometria/{id}")
                    .buildAndExpand(idPropostaCartao, biometria.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        else throw new ApiException(HttpStatus.NOT_FOUND, "Id da proposta do cartão não encontrado ou cartão ainda não foi gerado");
    }
}
