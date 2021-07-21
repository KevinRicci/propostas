package br.com.brzupacademy.propostas.proposta;

import br.com.brzupacademy.propostas.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping
    public ResponseEntity<PropostaResponse> criar(@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriComponentsBuilder){
        if(propostaRepository.findByDocumento(propostaRequest.getDocumento()).isEmpty()) {
            Proposta proposta = propostaRequest.toModel();
            propostaRepository.save(proposta);

            URI uri = uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
            return ResponseEntity.created(uri).body(new PropostaResponse(proposta));
        }
        else throw new ApiException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe uma proposta para esse documento");
    }
}
