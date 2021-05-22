package me.gabreuw.algalog.api.assembler;

import lombok.RequiredArgsConstructor;
import me.gabreuw.algalog.api.dto.EntregaDTO;
import me.gabreuw.algalog.api.dto.request.EntregaRequest;
import me.gabreuw.algalog.domain.model.Entrega;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class EntregaAssembler {

    private final ModelMapper modelMapper;

    public EntregaDTO toDTO(Entrega entrega) {
        return modelMapper.map(entrega, EntregaDTO.class);
    }

    public List<EntregaDTO> toCollectionDTO(List<Entrega> entregas) {
        return entregas.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaRequest entregaRequest) {
        return modelMapper.map(entregaRequest, Entrega.class);
    }

}
