package br.com.ufms.eprontuarioapi.application.config.handler;

import br.com.ufms.eprontuarioapi.application.config.errordto.ErrorApiDTO;
import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import br.com.ufms.eprontuarioapi.domain.utils.validador.exception.ValidadorEProntuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestControllerAdvice
public class RestResponseExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({ValidadorEProntuarioException.class, GenericRuntimeException.class})
    public ResponseEntity<ErrorApiDTO> handleApiException(Exception exception) {

        List<String> erros = new ArrayList<>();

        for (Throwable throwable : exception.getSuppressed()) {
            erros.add(throwable.getMessage());
        }

        Logger.getGlobal().log(Level.SEVERE, erros.toString());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorApiDTO.builder()
                        .code(500)
                        .messages(erros)
                        .exception(exception.getClass().getSimpleName())
                        .build());

    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorApiDTO>> handleApiException(MethodArgumentNotValidException exception) {

        List<ErrorApiDTO> dtos = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<String> erros = new ArrayList<>();

        fieldErrors.forEach(fieldError -> {
            erros.add(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
            dtos.add(ErrorApiDTO.builder()
                    .code(400)
                    .field(fieldError.getField())
                    .message(erros.toString())
                    .messages(erros)
                    .build());
        });

        Logger.getGlobal().log(Level.SEVERE, erros.toString());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(dtos);

    }
}
