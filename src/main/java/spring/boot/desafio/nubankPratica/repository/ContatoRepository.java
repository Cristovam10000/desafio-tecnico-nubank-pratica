package spring.boot.desafio.nubankPratica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.desafio.nubankPratica.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    
}
