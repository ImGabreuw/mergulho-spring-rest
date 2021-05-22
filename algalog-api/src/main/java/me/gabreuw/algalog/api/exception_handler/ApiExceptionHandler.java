package me.gabreuw.algalog.api.exception_handler;

import lombok.RequiredArgsConstructor;
import me.gabreuw.algalog.domain.exception.EntidadeNaoEncontradaException;
import me.gabreuw.algalog.domain.exception.NegocioException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        var problema = new Problema();

        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
        problema.setCampos(mapBindExceptionToCampos(exception));

        return handleExceptionInternal(exception, problema, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(
            NegocioException exception,
            WebRequest request
    ) {
        var status = HttpStatus.BAD_REQUEST;
        var problema = new Problema();

        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setTitulo(exception.getMessage());

        return handleExceptionInternal(exception, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontradaException(
            EntidadeNaoEncontradaException exception,
            WebRequest request
    ) {
        var status = HttpStatus.NOT_FOUND;
        var problema = new Problema();

        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setTitulo(exception.getMessage());

        return handleExceptionInternal(exception, problema, new HttpHeaders(), status, request);
    }

    private List<Problema.Campo> mapBindExceptionToCampos(BindException exception) {
        Function<ObjectError, Problema.Campo> mapper = error -> new Problema.Campo(
                ((FieldError) error).getField(),
                messageSource.getMessage(error, LocaleContextHolder.getLocale())
        );

        return exception.getBindingResult().getAllErrors()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
