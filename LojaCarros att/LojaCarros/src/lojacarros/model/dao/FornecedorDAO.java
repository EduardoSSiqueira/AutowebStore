/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lojacarros.model.Cliente;
import lojacarros.model.Fornecedor;

/**
 *
 * @author 20201si029
 */
public class FornecedorDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor(empresa, local, cnpj, carros) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, fornecedor.getEmpresa());
            stmt.setString(2, fornecedor.getLocal());
            stmt.setString(3, fornecedor.getCnpj());
            stmt.setString(4, fornecedor.getCarros());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedor SET empresa=?, local=?, cnpj=?, carros =? WHERE codForn=?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, fornecedor.getEmpresa());
            stmt.setString(2, fornecedor.getLocal());
            stmt.setString(3, fornecedor.getCnpj());
            stmt.setString(4, fornecedor.getCarros());
            stmt.setInt(5, fornecedor.getCdFornecedor());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    public boolean remover(Fornecedor fornecedor) {
        String sql = "DELETE FROM fornecedor  WHERE codForn=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, fornecedor.getCdFornecedor());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
 
    public List<Fornecedor> listar() {
        String sql = "SELECT * FROM fornecedor";
        List<Fornecedor> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setCdFornecedor(resultado.getInt("codForn"));
                fornecedor.setEmpresa(resultado.getString("empresa"));
                fornecedor.setLocal(resultado.getString("local"));
                fornecedor.setCnpj(resultado.getString("cnpj"));

//               Date dataNascimento = resultado.getDate("data_nascimento");
//                arbitro.setDataDeNascimento(dataNascimento.toLocalDate());
                fornecedor.setCarros(resultado.getString("carros"));
                retorno.add(fornecedor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    
    public Fornecedor buscar(Fornecedor fornecedor) {
        String sql = "SELECT * FROM fornecedor WHERE codForn=?";
        Fornecedor retorno = new Fornecedor();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, fornecedor.getCdFornecedor());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                fornecedor.setEmpresa(resultado.getString("empresa"));
                fornecedor.setCnpj(resultado.getString("cnpj"));
    //            Date dataNascimento = resultado.getDate("data_nascimento");
     //           arbitro.setDataDeNascimento(dataNascimento.toLocalDate());
                fornecedor.setLocal(resultado.getString("local"));
                fornecedor.setCarros(resultado.getString("carros"));

                retorno = fornecedor;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }    
      
    
}
