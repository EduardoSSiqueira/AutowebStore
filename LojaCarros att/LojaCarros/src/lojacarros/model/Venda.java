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
public class Venda {
   
    private Cliente clienteVenda;
    private Veiculo veiculoVenda;
    private Funcionario funcVenda;
    private String dataVenda;
    
    private Venda(Cliente clienteVenda, Veiculo veiculoVenda, Funcionario funcVenda, String dataVenda) {
        this.clienteVenda = clienteVenda;
        this.veiculoVenda = veiculoVenda;
        this.funcVenda = funcVenda;
        this.dataVenda = dataVenda;
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

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }
    
    
    
}

