package br.com.brzupacademy.propostas.exception;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ErroPadronizado> handle(ApiException apiException){
        ErroPadronizado erroPadronizado = new ErroPadronizado(Arrays.asList(apiException.getReason()));
        return ResponseEntity.status(apiException.getHttpStatus()).body(erroPadronizado);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadronizado> handle(MethodArgumentNotValidException exception){
        List<String> mensagens = new ArrayList<>();
        exception.getFieldErrors().forEach(e ->{
            String mensagem = String.format("Campo %s %s", e.getField(), e.getDefaultMessage());
            mensagens.add(mensagem);
        });

        return ResponseEntity.badRequest().body(new ErroPadronizado(mensagens));
    }
}
