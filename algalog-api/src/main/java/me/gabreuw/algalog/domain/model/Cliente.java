package me.gabreuw.algalog.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;
    private String email;
    @Column(name = "fone")
    private String telefone;
}
