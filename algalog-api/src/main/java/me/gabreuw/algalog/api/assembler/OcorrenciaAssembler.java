package me.gabreuw.algalog.api.assembler;

import lombok.RequiredArgsConstructor;
import me.gabreuw.algalog.api.dto.OcorrenciaDTO;
import me.gabreuw.algalog.domain.model.Ocorrencia;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OcorrenciaAssembler {

    private final ModelMapper modelMapper;

    public OcorrenciaDTO toDTO(Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaDTO.class);
    }

    public List<OcorrenciaDTO> toCollectionDTO(List<Ocorrencia> ocorrencias) {
        return ocorrencias.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
