package me.gabreuw.algalog.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.gabreuw.algalog.domain.model.validation.ValidationGroups;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

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

    @NotNull
    private BigDecimal taxa;

    @JsonProperty(access = READ_ONLY)
    private OffsetDateTime dataPedido;

    @JsonProperty(access = READ_ONLY)
    private OffsetDateTime dataFinalizacao;

    @Valid
    @ConvertGroup(to = ValidationGroups.ClienteId.class)
    @NotNull
    @ManyToOne
    private Cliente cliente;

    @Valid
    @NotNull
    @Embedded
    private Destinatario destinatario;

    @JsonProperty(access = READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

}
