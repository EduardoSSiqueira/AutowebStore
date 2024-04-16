package lojacarros.model;

import java.util.Date;

public class Servico// extends Veiculo 
{
    private String tipoServico;
    private String proprietario;
    private String material;
    private String pecasUtilizadas;
    private Date data;
    private float custos;
    private Veiculo veiculo;

    // Construtor
    public Servico(String tipoServico, String proprietario,String material, String pecasUtilizadas, Date data, float custos) {
        
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getCustos() {
        return custos;
    }

    public void setCustos(float custos) {
        this.custos = custos;
    }
    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
}
