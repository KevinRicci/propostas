package br.com.brzupacademy.propostas.proposta.analiseFinanceira;

import br.com.brzupacademy.propostas.proposta.EstadoAnaliseFinanceira;

public enum StatusAnalise {
    COM_RESTRICAO, SEM_RESTRICAO;

    public EstadoAnaliseFinanceira normaliza(){
        if(this.equals(SEM_RESTRICAO)){
            return EstadoAnaliseFinanceira.ELEGIVEL;
        }
        else return EstadoAnaliseFinanceira.NAO_ELEGIVEL;
    }
}
