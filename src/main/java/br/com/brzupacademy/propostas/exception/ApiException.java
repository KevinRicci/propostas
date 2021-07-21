package br.com.brzupacademy.propostas.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{

    private HttpStatus httpStatus;
    private String reason;

    public ApiException(HttpStatus httpStatus, String reason){
        this.httpStatus = httpStatus;
        this.reason = reason;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getReason() {
        return reason;
    }
}
