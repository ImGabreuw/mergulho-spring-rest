package me.gabreuw.algalog.api.controller;

import lombok.RequiredArgsConstructor;
import me.gabreuw.algalog.api.dto.DestinatarioDTO;
import me.gabreuw.algalog.api.dto.EntregaDTO;
import me.gabreuw.algalog.domain.model.Destinatario;
import me.gabreuw.algalog.domain.model.Entrega;
import me.gabreuw.algalog.domain.repository.EntregaRepository;
import me.gabreuw.algalog.domain.service.SolicitacaoEntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/entregas")
public class EntregaController {

    private final EntregaRepository repository;
    private final SolicitacaoEntregaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
        return service.solicitar(entrega);
    }

    @GetMapping
    public List<Entrega> listar() {
        return repository.findAll();
    }

    @GetMapping(path = "/{entregaId}")
    public ResponseEntity<EntregaDTO> buscar(@PathVariable Long entregaId) {
        return repository.findById(entregaId)
                .map(this::entregaMapper)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private EntregaDTO entregaMapper(Entrega entrega) {
        var dto = new EntregaDTO();

        dto.setId(entrega.getId());
        dto.setNomeCliente(entrega.getCliente().getNome());
        dto.setDestinatarioDTO(destinatarioMapper(entrega.getDestinatario()));
        dto.setTaxa(entrega.getTaxa());
        dto.setStatus(entrega.getStatus());
        dto.setDataPedido(entrega.getDataPedido());
        dto.setDataFinalizacao(entrega.getDataFinalizacao());

        return dto;
    }

    private DestinatarioDTO destinatarioMapper(Destinatario destinatario) {
        var dto = new DestinatarioDTO();

        dto.setNome(destinatario.getNome());
        dto.setLogradouro(destinatario.getLogradouro());
        dto.setNumero(destinatario.getNumero());
        dto.setComplemento(destinatario.getComplemento());
        dto.setBairro(destinatario.getBairro());

        return dto;
    }

}
