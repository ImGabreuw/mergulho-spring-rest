package me.gabreuw.algalog.api.exception_handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Problema {

    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;

    private List<Campo> campos;

    @AllArgsConstructor
    @Getter
    public static class Campo {

        private final String nome;
        private final String mensagem;

    }
}
