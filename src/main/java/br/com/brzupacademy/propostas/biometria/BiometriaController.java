package br.com.brzupacademy.propostas.biometria;

import br.com.brzupacademy.propostas.exception.ApiException;
import br.com.brzupacademy.propostas.proposta.Proposta;
import br.com.brzupacademy.propostas.proposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/cartao/{idCartao}/biometria")
    public ResponseEntity<?> cadastra(@PathVariable String idCartao, @RequestBody @Valid BiometriaRequest biometriaRequest, UriComponentsBuilder uriComponentsBuilder){
        Optional<Proposta> possivelProposta = propostaRepository.findByIdCartao(idCartao);
        if(possivelProposta.isPresent()) {
            Biometria biometria = biometriaRequest.toModel(possivelProposta.get());
            biometriaRepository.save(biometria);

            URI uri = uriComponentsBuilder.path("/propostas/cartao/{idCartao}/biometria/{id}").buildAndExpand(idCartao, biometria.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        else throw new ApiException(HttpStatus.NOT_FOUND, "Id do cartão não encontrado");
    }
}
