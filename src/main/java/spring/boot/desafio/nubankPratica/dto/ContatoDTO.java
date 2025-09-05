package spring.boot.desafio.nubankPratica.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContatoDTO {
    
    private String telefone;
    private String email;
    private Long clienteId;

}
 