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
    
    private String modelo;
    private int ano;
    private double preco;
    
    private Veiculo(String modelo, int ano, double preco) {
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
    }
     public String getModelo() {
        return modelo;
    }

   
    public void setLocal(String modelo) {
        this.modelo = modelo;
    }

    
    public int getAno() {
        return ano;
    }

   
    public void setAno(int ano) {
        this.ano = ano;
    }

  
    public Double getPreço() {
        return preco;
    }

 
    public void setEmpresa(Double preco) {
        this.preco = preco;
    }

    
    
  
}


