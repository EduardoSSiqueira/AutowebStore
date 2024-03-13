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

    // Construtor
    public Servico(String marca, String modelo, int ano, String cor, String tipoServico, String proprietario,
                          String material, String pecasUtilizadas, Date data, float custos) {
        //super(marca, modelo, ano, cor);
        this.tipoServico = tipoServico;
        this.proprietario = proprietario;
        this.material = material;
        this.pecasUtilizadas = pecasUtilizadas;
        this.data = data;
        this.custos = custos;
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
}
