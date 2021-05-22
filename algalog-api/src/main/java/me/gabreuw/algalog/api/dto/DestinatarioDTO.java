package me.gabreuw.algalog.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DestinatarioDTO implements Serializable {

    private String nome;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;

}
