package spring.boot.desafio.nubankPratica.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.OneToMany;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Clientes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @jakarta.persistence.OneToMany(mappedBy = "clients", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Object> contatos = new ArrayList<Object>();
}
