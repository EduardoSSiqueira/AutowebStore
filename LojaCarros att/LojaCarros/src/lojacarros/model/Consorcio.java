package lojacarros.model;


public class Consorcio {
    private float valorCartaCredito;
    private int prazoPagamento;
    private float valorParcelas;
    private float taxasAdministrativas;
    private Cliente cliente;

    // Construtor
    public Consorcio(float valorCartaCredito, int prazoPagamento, float valorParcelas, float taxasAdministrativas,Cliente cliente) {
        this.valorCartaCredito = valorCartaCredito;
        this.prazoPagamento = prazoPagamento;
        this.valorParcelas = valorParcelas;
        this.taxasAdministrativas = taxasAdministrativas;
        this.cliente=cliente;
    }

    // Métodos getters e setters
    public float getValorCartaCredito() {
        return valorCartaCredito;
    }

    public void setValorCartaCredito(float valorCartaCredito) {
        this.valorCartaCredito = valorCartaCredito;
    }

    public int getPrazoPagamento() {
        return prazoPagamento;
    }

    public void setPrazoPagamento(int prazoPagamento) {
        this.prazoPagamento = prazoPagamento;
    }

    public float getValorParcelas() {
        return valorParcelas;
    }

    public void setValorParcelas(float valorParcelas) {
        this.valorParcelas = valorParcelas;
    }

    public float getTaxasAdministrativas() {
        return taxasAdministrativas;
    }

    public void setTaxasAdministrativas(float taxasAdministrativas) {
        this.taxasAdministrativas = taxasAdministrativas;
    }
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}


