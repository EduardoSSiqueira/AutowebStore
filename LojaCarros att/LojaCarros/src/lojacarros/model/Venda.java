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
    private Veículo veiculoVenda;
    private Funcionario funcVenda;
    private String dataVenda;
    
    public Venda(){
        
    }

    public Cliente getClienteVenda() {
        return clienteVenda;
    }

    public void setClienteVenda(Cliente clienteVenda) {
        this.clienteVenda = clienteVenda;
    }

    public Veículo getVeiculoVenda() {
        return veiculoVenda;
    }

    public void setVeiculoVenda(Veículo veiculoVenda) {
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

