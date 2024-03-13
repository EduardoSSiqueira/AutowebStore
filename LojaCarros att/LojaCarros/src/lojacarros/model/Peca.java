package lojacarros.model;



public class Peca{
    private String nomePeca;
    private String descPeca;
    private String numPeca;
    private String fabricante;
    private float preco;
    private int qtdEstoque;


    public Peca(String nomePeca, String descPeca, String numPeca, String fabricante, float preco, int qtdEstoque) {
        this.nomePeca = nomePeca;
        this.descPeca = descPeca;
        this.numPeca = numPeca;
        this.fabricante = fabricante;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
    }

    public String getNomePeca() {
        return nomePeca;
    }

    public void setNomePeca(String nomePeca) {
        this.nomePeca = nomePeca;
    }

    public String getDescPeca() {
        return descPeca;
    }

    public void setDescPeca(String descPeca) {
        this.descPeca = descPeca;
    }

    public String getNumPeca() {
        return numPeca;
    }

    public void setNumPeca(String numPeca) {
        this.numPeca = numPeca;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
}
