/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.model;

import java.time.LocalDate;

/**
 *
 * @author 20201si029
 */
public class Cliente {
    
    private String nome;
    private String cpf;    
    private String cidade;
    private LocalDate datadenascimento;

    
 private Cliente(String nome, String cpf, String cidade, LocalDate datadenascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.cidade = cidade;
        this.datadenascimento = datadenascimento;
    }
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the datadenascimento
     */
    public LocalDate getDatadenascimento() {
        return datadenascimento;
    }

    /**
     * @param datadenascimento the datadenascimento to set
     */
    public void setDatadenascimento(LocalDate datadenascimento) {
        this.datadenascimento = datadenascimento;
    }
    
}
