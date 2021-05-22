package me.gabreuw.algalog.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
public class OcorrenciaDTO implements Serializable {

    private Long id;

    private String descricao;
    private OffsetDateTime dataRegistro;

}
