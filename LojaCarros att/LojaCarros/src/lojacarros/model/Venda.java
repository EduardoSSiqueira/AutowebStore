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
public class Venda {
   
    private int codVenda;
    private Cliente clienteVenda;
    private Veiculo veiculoVenda;
    private Funcionario funcVenda;
    private LocalDate dataVenda;
    private String valorVenda;
    
    private Venda(int codVenda, Cliente clienteVenda, Veiculo veiculoVenda, Funcionario funcVenda, LocalDate dataVenda, String valorVenda) {
        this.codVenda = codVenda;
        this.clienteVenda = clienteVenda;
        this.veiculoVenda = veiculoVenda;
        this.funcVenda = funcVenda;
        this.dataVenda = dataVenda;
        this.valorVenda = valorVenda;
    }

     public Venda() {
    }
    
    public int getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(int codVenda) {
        this.codVenda = codVenda;
    }

    public String getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(String valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Cliente getClienteVenda() {
        return clienteVenda;
    }

    public void setClienteVenda(Cliente clienteVenda) {
        this.clienteVenda = clienteVenda;
    }

    public Veiculo getVeiculoVenda() {
        return veiculoVenda;
    }

    public void setVeiculoVenda(Veiculo veiculoVenda) {
        this.veiculoVenda = veiculoVenda;
    }

    public Funcionario getFuncVenda() {
        return funcVenda;
    }

    public void setFuncVenda(Funcionario funcVenda) {
        this.funcVenda = funcVenda;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
    
    @Override
    public String toString() {
      return clienteVenda.getNome();
    }

    
}

