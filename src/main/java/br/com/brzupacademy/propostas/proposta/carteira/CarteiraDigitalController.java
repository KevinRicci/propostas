package br.com.brzupacademy.propostas.proposta.carteira;

import br.com.brzupacademy.propostas.exception.ApiException;
import br.com.brzupacademy.propostas.proposta.Proposta;
import br.com.brzupacademy.propostas.proposta.PropostaRepository;
import br.com.brzupacademy.propostas.proposta.cartao.SistemaCartao;
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
public class CarteiraDigitalController {

    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private CarteiraDigitalRepository carteiraDigitalRepository;
    @Autowired
    private SistemaCartao sistemaCartao;

    @PostMapping("/{id}/cartoes/carteiras")
    @Transactional
    public ResponseEntity<?> cadastraCarteiraDigital(@PathVariable long id,
                                                     @RequestBody @Valid CarteiraDigitalRequest carteiraDigitalRequest,
                                                     UriComponentsBuilder uriComponentsBuilder,
                                                     JwtAuthenticationToken token){
        Optional<Proposta> possivelProposta = propostaRepository.findById(id);
        if(possivelProposta.isPresent() && possivelProposta.get().cartaoIsPresent()){
            Proposta proposta = possivelProposta.get();
            proposta.pertenceAoUsuario(token);
            if(carteiraDigitalRepository.findByCarteiraAndProposta_Id(carteiraDigitalRequest.getCarteira(), id).isEmpty()) {

                CarteiraDigital carteiraDigital = carteiraDigitalRequest.toModel(proposta);
                sistemaCartao.cadastraCarteiraDigital(carteiraDigital, proposta);
                carteiraDigitalRepository.save(carteiraDigital);

                URI uri = uriComponentsBuilder.path("/propostas/{id}/cartoes/carteiras/{idCarteira}").buildAndExpand(
                        proposta.getId(), carteiraDigital.getId()).toUri();

                return ResponseEntity.created(uri).build();
            }
            else throw new ApiException(HttpStatus.UNPROCESSABLE_ENTITY, "O cartão já possui uma mesma carteira digital associada");
        }
        else throw new ApiException(HttpStatus.NOT_FOUND, "Id da proposta do cartão não encontrado ou cartão ainda não foi gerado");
    }
}
