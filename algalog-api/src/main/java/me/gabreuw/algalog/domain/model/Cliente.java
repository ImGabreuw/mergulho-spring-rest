package me.gabreuw.algalog.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cliente {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
}
