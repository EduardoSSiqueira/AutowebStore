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
public class TestDrive {

    
    private int cdTestDrive;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate data;
    private int duracao;

    
    private TestDrive(int cdTestDrive, Cliente cliente, Veiculo veiculo, LocalDate data, int duracao) {
        this.cdTestDrive = cdTestDrive;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.data = data;
        this.duracao= duracao;
    }

    public TestDrive() {
    }

        /**
     * @return the cdTestDrive
     */
    public int getCdTestDrive() {
        return cdTestDrive;
    }

    /**
     * @param cdTestDrive the cdTestDrive to set
     */
    public void setCdTestDrive(int cdTestDrive) {
        this.cdTestDrive = cdTestDrive;
    }
    
    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the veiculo
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * @param veiculo the veiculo to set
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * @return the data
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * @return the duracao
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * @param duracao the duracao to set
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    } 
    
    @Override
    public String toString() {
      return veiculo.getModelo();
    }

}
