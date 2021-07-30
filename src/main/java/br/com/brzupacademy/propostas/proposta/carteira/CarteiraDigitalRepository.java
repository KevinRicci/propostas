package br.com.brzupacademy.propostas.proposta.carteira;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraDigitalRepository extends JpaRepository<CarteiraDigital, Long> {

    Optional<CarteiraDigital> findByCarteiraAndProposta_Id(Carteira carteira, long idProposta);
}
