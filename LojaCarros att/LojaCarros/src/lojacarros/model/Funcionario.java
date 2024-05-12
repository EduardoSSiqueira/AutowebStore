/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.model;

import java.time.LocalDate;

/**
 *
 * @author Carlos
 */
public class Funcionario {
    
    private int codFunc;
    private String nome;
    private LocalDate dataNasc;
    private String cpf;
    
    public Funcionario(){
        
    }

    public Funcionario(int codFunc, String nome, LocalDate dataNasc, String cpf) {
        this.codFunc = codFunc;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
    }

    public int getCodFunc() {
        return codFunc;
    }

    public void setCodFunc(int codFunc) {
        this.codFunc = codFunc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
