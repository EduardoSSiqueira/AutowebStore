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
import lojacarros.model.Funcionario;

/**
 *
 * @author 20201si029
 */

public class FuncionarioDAO {
    
     private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario(nome, datanasc, cpf) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setDate(2, Date.valueOf(funcionario.getDataNasc()));
            stmt.setString(3, funcionario.getCpf());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nome=?, dataNasc=?, cpf=? WHERE codFunc=?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setDate(2, Date.valueOf(funcionario.getDataNasc()));
            stmt.setString(3, funcionario.getCpf());
            stmt.setInt(4, funcionario.getCodFunc());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    public boolean remover(Funcionario funcionario) {
        String sql = "DELETE FROM funcionario WHERE codFunc=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, funcionario.getCodFunc());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
 
    public List<Funcionario> listar() {
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setCodFunc(resultado.getInt("codFunc"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setDataNasc(resultado.getDate("dataNasc").toLocalDate());
                funcionario.setCpf(resultado.getString("cpf"));
                retorno.add(funcionario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    
    public Funcionario buscar(Funcionario funcionario) {
        String sql = "SELECT * FROM funcionario WHERE codFunc=?";
        Funcionario retorno = new Funcionario();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, funcionario.getCodFunc());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setDataNasc(resultado.getDate("dataNasc").toLocalDate());
                funcionario.setCpf(resultado.getString("cpf"));
                retorno = funcionario;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }    
      
    
}
