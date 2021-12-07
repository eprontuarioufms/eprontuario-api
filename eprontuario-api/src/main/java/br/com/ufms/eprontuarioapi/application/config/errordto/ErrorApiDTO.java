package br.com.ufms.eprontuarioapi.application.config.errordto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorApiDTO {

    private Integer code;
    private String field;
    private String message;
    private List<String> messages;
    private String exception;
}
