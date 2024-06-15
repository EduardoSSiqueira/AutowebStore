package lojacarros.model;

import java.time.LocalDate;
import java.util.Date;

public class Servico// extends Veiculo 
{
    private int    codServico;
    private String tipoServico;
    private String proprietario;
    private String material;
    private String pecasUtilizadas;
    private LocalDate data;
    private String custos;
    private String veiculo;

    // Construtor
    public Servico(int codServico,String tipoServico, String proprietario,String material, String pecasUtilizadas, LocalDate data, String custos, String veiculo) {
        
        this.codServico = codServico;
        this.tipoServico = tipoServico;
        this.proprietario = proprietario;
        this.material = material;
        this.pecasUtilizadas = pecasUtilizadas;
        this.data = data;
        this.custos = custos;
        this.veiculo=veiculo;
    }
    public Servico(){
    
    }
    // Métodos getters e setters
    
    public int getCodServico() {
        return codServico;
    }

    public void setCodServico(int codServico) {
        this.codServico = codServico;
    }
    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPecasUtilizadas() {
        return pecasUtilizadas;
    }

    public void setPecasUtilizadas(String pecasUtilizadas) {
        this.pecasUtilizadas = pecasUtilizadas;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getCustos(){
        return custos;
    }
    

    public void setCustos(String custos) {
        this.custos = custos;
    }
    
    public String getVeiculo() {
        return veiculo;
    }

    
    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }
    
}
