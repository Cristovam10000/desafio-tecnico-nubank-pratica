package spring.boot.desafio.nubankPratica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.desafio.nubankPratica.model.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {

    
} 