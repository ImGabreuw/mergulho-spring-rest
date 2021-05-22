package me.gabreuw.algalog.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class OcorrenciaRequest implements Serializable {

    @NotBlank
    private String descricao;

}
