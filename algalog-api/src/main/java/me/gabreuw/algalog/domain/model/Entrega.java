package me.gabreuw.algalog.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Entrega {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private BigDecimal taxa;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;

    @ManyToOne
    private Cliente cliente;

    @Embedded
    private Destinatario destinatario;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

}
