package me.gabreuw.algalog.api.dto;

import lombok.Getter;
import lombok.Setter;
import me.gabreuw.algalog.domain.model.StatusEntrega;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaDTO implements Serializable {

    private Long id;

    private BigDecimal taxa;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;

    private StatusEntrega status;

    private DestinatarioDTO destinatario;
    private ClienteResumoDTO cliente;

}
