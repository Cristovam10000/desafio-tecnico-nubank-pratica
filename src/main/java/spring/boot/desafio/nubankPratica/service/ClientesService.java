package spring.boot.desafio.nubankPratica.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.desafio.nubankPratica.dto.ClientesDTO;
import spring.boot.desafio.nubankPratica.dto.ClientesResponseDTO;
import spring.boot.desafio.nubankPratica.dto.ContatoDTO;
import spring.boot.desafio.nubankPratica.dto.ContatoResponseDTO;
import spring.boot.desafio.nubankPratica.model.Clientes;
import spring.boot.desafio.nubankPratica.model.Contato;
import spring.boot.desafio.nubankPratica.repository.ClientesRepository;
import spring.boot.desafio.nubankPratica.repository.ContatoRepository;

@Service
public class ClientesService {
    
    @Autowired
    private ClientesRepository clientesRepository;

    public Clientes salvarClientes(ClientesDTO dto) {
        Clientes clientes = new Clientes();
        clientes.setNome(dto.getNome());

        if (dto.getContatos() != null && dto.getContatos().size() > 0) {
            List<Contato> contatos = dto.getContatos().stream().map(c -> {
                Contato contato = new Contato();

                contato.setTelefone(c.getTelefone());
                contato.setEmail(c.getEmail());
                contato.setClientes(clientes);
                
                return contato;


            }).collect(Collectors.toList());
            clientes.setContatos(contatos);
        }

        return clientesRepository.save(clientes);
    }

    @Transactional(readOnly = true)
    public List<ClientesResponseDTO> ListarTodos() {
        return clientesRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<ContatoResponseDTO> ListContatosPorCliente(Long clienteId) {
        Clientes cliente = clientesRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontratado"));
        return cliente.getContatos().stream().map(c -> {
            ContatoResponseDTO contatoDTO = new ContatoResponseDTO();

            contatoDTO.setId(c.getId());
            contatoDTO.setTelefone(c.getTelefone());
            contatoDTO.setEmail(c.getEmail());
            return contatoDTO;

        }).collect(Collectors.toList());
    }

    private ClientesResponseDTO toDTO(Clientes cliente) {
        ClientesResponseDTO dto = new  ClientesResponseDTO();

        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());

        List<ContatoResponseDTO> contatos = cliente.getContatos().stream().map(c -> {
            ContatoResponseDTO contatoDTO = new ContatoResponseDTO();


            contatoDTO.setId(c.getId());
            contatoDTO.setTelefone(c.getTelefone());
            contatoDTO.setEmail(c.getEmail());
            return contatoDTO;
            
        }).collect(Collectors.toList());
        dto.setContatos(contatos);

        return dto;


    }

    

    
}
