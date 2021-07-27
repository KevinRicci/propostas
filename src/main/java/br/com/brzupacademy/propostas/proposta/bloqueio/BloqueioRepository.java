package br.com.brzupacademy.propostas.proposta.bloqueio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloqueioRepository extends JpaRepository<Bloqueio, Long> {

    Optional<Bloqueio> findByProposta_Id(long id);
}
