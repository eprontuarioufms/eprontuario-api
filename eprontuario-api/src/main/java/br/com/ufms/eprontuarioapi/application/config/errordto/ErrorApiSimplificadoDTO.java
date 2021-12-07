package br.com.ufms.eprontuarioapi.application.config.errordto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorApiSimplificadoDTO {
    private String field;
    private String message;
}
