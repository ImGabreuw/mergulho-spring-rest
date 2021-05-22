package me.gabreuw.algalog.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClienteResumoDTO implements Serializable {

    private Long id;
    private String nome;

}
