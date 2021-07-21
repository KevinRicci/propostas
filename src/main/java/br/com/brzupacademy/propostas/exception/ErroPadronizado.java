package br.com.brzupacademy.propostas.exception;

import java.util.List;

public class ErroPadronizado {

    private List<String> mensagens;

    public ErroPadronizado(List<String> mensagens){
        this.mensagens = mensagens;
    }

    public List<String> getMensagens() {
        return mensagens;
    }
}
