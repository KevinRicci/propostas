package br.com.brzupacademy.propostas.proposta;

import br.com.brzupacademy.propostas.validacao.CpfCnpj;
import com.fasterxml.jackson.databind.annotation.JsonAppend;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @CpfCnpj
    private String documento;
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @Min(0)
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private EstadoAnaliseFinanceira estadoAnaliseFinanceira = EstadoAnaliseFinanceira.AGUARDANDO;
    private String idCartao;

    public Proposta(@CpfCnpj String documento, @Email String email, @NotBlank String nome, @NotBlank String endereco, @Min(0) BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    @Deprecated
    public Proposta(){}

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setIdCartao(String idCartao) {
        this.idCartao = idCartao;
    }

    public void setEstadoAnaliseFinanceira(EstadoAnaliseFinanceira estadoAnaliseFinanceira) {
        this.estadoAnaliseFinanceira = estadoAnaliseFinanceira;
    }

    public EstadoAnaliseFinanceira getEstadoAnaliseFinanceira() {
        return estadoAnaliseFinanceira;
    }
}
