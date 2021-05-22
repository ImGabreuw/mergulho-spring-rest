package me.gabreuw.algalog.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.OffsetDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String descricao;
    private OffsetDateTime dataRegistro;

    @ManyToOne
    private Entrega entrega;


}
