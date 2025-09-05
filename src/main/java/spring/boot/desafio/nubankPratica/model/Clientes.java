package spring.boot.desafio.nubankPratica.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @jakarta.persistence.OneToMany(mappedBy = "clientes", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Contato> contatos = new ArrayList<Contato>();
}
