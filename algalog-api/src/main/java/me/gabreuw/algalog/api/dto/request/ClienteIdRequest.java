package me.gabreuw.algalog.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class ClienteIdRequest implements Serializable {

    @NotNull
    private Long id;

}
