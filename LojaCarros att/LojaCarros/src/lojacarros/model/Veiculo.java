/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.model;

/**
 *
 * @author Carlos
 */
public class Veiculo {
    
    private int cdVeiculo;
    private String modelo;
    private String ano;
    private String preco;
    
    public Veiculo(){
    }

    public Veiculo(int cdVeiculo, String modelo, String ano, String preco) {
        this.cdVeiculo = cdVeiculo;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCdVeiculo() {
        return cdVeiculo;
    }

    public void setCdVeiculo(int cdVeiculo) {
        this.cdVeiculo = cdVeiculo;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
    
    
     public String getModelo() {
        return modelo;
    }

   
    public void setLocal(String modelo) {
        this.modelo = modelo;
    }

    
    public String getAno() {
        return ano;
    }

   
    public void setAno(String ano) {
        this.ano = ano;
    }

  
    public String getPreço() {
        return preco;
    }

    @Override
    public String toString() {
        return this.modelo;
    }
    
    
    
  
}


