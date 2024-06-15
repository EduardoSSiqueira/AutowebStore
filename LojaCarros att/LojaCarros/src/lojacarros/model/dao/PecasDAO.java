/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lojacarros.model.Cliente;
import lojacarros.model.Peca;


public class PecasDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Peca peca) {
        String sql = "INSERT INTO peca(nomePeca ,  descricaoPeca, numeroPeca, fabricantePeca, precoPeca, qtdPeca) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, peca.getNomePeca());
            stmt.setString(2, peca.getDescPeca());
            stmt.setString(3, peca.getNumPeca());
            stmt.setString(4, peca.getFabricante());
            stmt.setFloat(5, peca.getPreco());
            stmt.setInt(6, peca.getQtdEstoque());           

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PecasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Peca peca) {
        String sql = "UPDATE peca SET nomePeca=?, descricaoPeca=?, numeroPeca=?, fabricantePeca =? precoPeca=? qtdPeca =? WHERE codPeca =?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, peca.getNomePeca());
            stmt.setString(2, peca.getDescPeca());
            stmt.setString(3, peca.getNumPeca());
            stmt.setString(4, peca.getFabricante());
            stmt.setFloat(5, peca.getPreco());
            stmt.setInt(6, peca.getQtdEstoque());
        
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PecasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    public boolean remover(Peca peca) {
        String sql = "DELETE FROM peca WHERE codPeca =?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, peca.getcodPeca());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PecasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
 
    public List<Peca> listar() {
        String sql = "SELECT * FROM peca";
        List<Peca> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Peca peca = new Peca();
                peca.setcodPeca(resultado.getInt("codPeca"));
                peca.setNomePeca(resultado.getString("nomePeca"));
                peca.setDescPeca(resultado.getString("descricaoPeca"));
                peca.setNumPeca(resultado.getString("numeroPeca"));
                peca.setFabricante(resultado.getString("fabricantePeca"));
                peca.setPreco(resultado.getFloat("precoPeca"));
                peca.setQtdEstoque(resultado.getInt("qtdPeca"));
                retorno.add(peca);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PecasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    
    public Peca buscar(Peca peca) {
        String sql = "SELECT * FROM peca WHERE codPeca=?";
        Peca retorno = new Peca();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, peca.getcodPeca());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                peca.setcodPeca(resultado.getInt("codPeca"));
                peca.setNomePeca(resultado.getString("nomePeca"));
                peca.setDescPeca(resultado.getString("descPeca"));
                peca.setNumPeca(resultado.getString("numeroPeca"));
                peca.setFabricante(resultado.getString("fabricantePeca"));
                peca.setPreco(resultado.getFloat("precoPeca"));
                peca.setQtdEstoque(resultado.getInt("qtdPeca"));
                retorno = peca;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PecasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }    
      
    
}
