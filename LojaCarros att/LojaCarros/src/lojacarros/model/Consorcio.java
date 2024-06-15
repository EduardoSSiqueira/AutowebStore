package lojacarros.model;

import java.sql.Date;
import java.time.LocalDate;


public class Consorcio {
    
    private int idConsorcio;
    private String valorCartaCredito;
    private LocalDate prazoPagamento;
    private String valorParcelas;
    private String taxasAdministrativas;
    private Cliente cliente;
    private Veiculo veiculo;

    // Construtor
    public Consorcio(int idConsorcio,String valorCartaCredito, LocalDate prazoPagamento, String valorParcelas, String taxasAdministrativas,Cliente cliente,Veiculo veiculo) {
        this.idConsorcio=idConsorcio;
        this.valorCartaCredito = valorCartaCredito;
        this.prazoPagamento = prazoPagamento;
        this.valorParcelas = valorParcelas;
        this.taxasAdministrativas = taxasAdministrativas;
        this.cliente=cliente;
        this.veiculo=veiculo;
    }

    public Consorcio() {
    }
    
    // Métodos getters e setters
    public String getValorCartaCredito() {
        return valorCartaCredito;
    }

    public void setValorCartaCredito(String valorCartaCredito) {
        this.valorCartaCredito = valorCartaCredito;
    }

    public LocalDate getPrazoPagamento() {
        return prazoPagamento;
    }

    public void setPrazoPagamento(LocalDate prazoPagamento) {
        this.prazoPagamento = prazoPagamento;
    }

    public String getValorParcelas() {
        return valorParcelas;
    }

    public void setValorParcelas(String valorParcelas) {
        this.valorParcelas = valorParcelas;
    }

    public String getTaxasAdministrativas() {
        return taxasAdministrativas;
    }

    public void setTaxasAdministrativas(String taxasAdministrativas) {
        this.taxasAdministrativas = taxasAdministrativas;
    }
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * @return the idConsorcio
     */
    public int getIdConsorcio() {
        return idConsorcio;
    }

    /**
     * @param idConsorcio the idConsorcio to set
     */
    public void setIdConsorcio(int idConsorcio) {
        this.idConsorcio = idConsorcio;
    }
     
    @Override
    public String toString() {
      return cliente.getNome();
    }
}


