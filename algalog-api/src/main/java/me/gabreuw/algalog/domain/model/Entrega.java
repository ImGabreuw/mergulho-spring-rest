package me.gabreuw.algalog.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
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

    @JsonProperty(access = READ_ONLY)
    private LocalDateTime dataPedido;

    @JsonProperty(access = READ_ONLY)
    private LocalDateTime dataFinalizacao;

    @ManyToOne
    private Cliente cliente;

    @Embedded
    private Destinatario destinatario;

    @JsonProperty(access = READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

}
