package br.com.brzupacademy.propostas.proposta;

import br.com.brzupacademy.propostas.exception.ApiException;
import br.com.brzupacademy.propostas.proposta.analiseFinanceira.AnaliseFinanceira;
import br.com.brzupacademy.propostas.proposta.analiseFinanceira.AnaliseFinanceiraResponse;
import br.com.brzupacademy.propostas.proposta.analiseFinanceira.ClienteAnaliseFinanceira;
import br.com.brzupacademy.propostas.proposta.analiseFinanceira.EnviaProposta;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private AnaliseFinanceira analiseFinanceira;

    @PostMapping
    @Transactional
    public ResponseEntity<PropostaResponse> criar(@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriComponentsBuilder){
        if(propostaRepository.findByDocumento(propostaRequest.getDocumento()).isEmpty()) {
            Proposta proposta = propostaRequest.toModel();
            propostaRepository.saveAndFlush(proposta);

            AnaliseFinanceiraResponse analise = analiseFinanceira.enviaParaAnalise(proposta);

            proposta.setEstadoAnaliseFinanceira(analise.getResultadoSolicitacao().normaliza());

            URI uri = uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
            return ResponseEntity.created(uri).body(new PropostaResponse(proposta));
        }
        else throw new ApiException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe uma proposta para esse documento");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropostaResponse> buscaPorId(@PathVariable Long id){
        Optional<Proposta> possivelProposta = propostaRepository.findById(id);
        if(possivelProposta.isPresent()){
            return ResponseEntity.ok().body(new PropostaResponse(possivelProposta.get()));
        }
        else throw new ApiException(HttpStatus.NOT_FOUND, "Proposta não encontrada");
    }
}
