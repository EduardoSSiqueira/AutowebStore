/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.model;

/**
 *
 * @author 20201si029
 */



    
public class Fornecedor {
    private int cdFornecedor;
    private String local;
    private String carros;
    private String empresa;
    private String cnpj;
    
    public Fornecedor() {
    }

    private Fornecedor(int cdFornecedor, String local, String carros, String empresa, String cnpj) {
        this.cdFornecedor = cdFornecedor;
        this.local = local;
        this.carros = carros;
        this.empresa = empresa;
        this.cnpj = cnpj;
    }


    /**
     * @return the local
     */
    public String getLocal() {
        return local;
    }

    /**
     * @param local the local to set
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * @return the carros
     */
    public String getCarros() {
        return carros;
    }

    /**
     * @param carros the carros to set
     */
    public void setCarros(String carros) {
        this.carros = carros;
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the cdFornecedor
     */
    public int getCdFornecedor() {
        return cdFornecedor;
    }

    /**
     * @param cdFornecedor the cdFornecedor to set
     */
    public void setCdFornecedor(int cdFornecedor) {
        this.cdFornecedor = cdFornecedor;
    }
    
}
