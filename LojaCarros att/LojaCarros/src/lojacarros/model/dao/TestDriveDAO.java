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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lojacarros.model.Cliente;
import lojacarros.model.TestDrive;
import lojacarros.model.Veiculo;

/**
 *
 * @author 20201si029
 */
public class TestDriveDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(TestDrive testdrive) {
        String sql = "INSERT INTO testDrive(codCliente, codVeiculo, data, duracao) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, testdrive.getCliente().getCdCliente());
            stmt.setInt(2, testdrive.getVeiculo().getCdVeiculo());
            stmt.setDate(3, Date.valueOf(testdrive.getData()));
            stmt.setInt(4, testdrive.getDuracao());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TestDriveDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    public boolean alterar(TestDrive testdrive) {
        String sql = "UPDATE testDrive SET codCliente=?, codVeiculo=?, data=?, duracao=? WHERE codTestDrive =?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, testdrive.getCliente().getCdCliente());
            stmt.setInt(2, testdrive.getVeiculo().getCdVeiculo());
            stmt.setDate(3, Date.valueOf(testdrive.getData()));
            stmt.setInt(4, testdrive.getDuracao());
            stmt.setInt(5, testdrive.getCdTestDrive());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TestDriveDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    public boolean remover(TestDrive testdrive) {
        String sql = "DELETE FROM testDrive  WHERE codTestDrive=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, testdrive.getCdTestDrive());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TestDriveDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
       
    
    public List<TestDrive> listar() {
    String sql = "SELECT * FROM testDrive";
    List<TestDrive> retorno = new ArrayList<>();
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet resultado = stmt.executeQuery();
        while (resultado.next()) {
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.setConnection(connection);
            Cliente cliente = new Cliente();
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            veiculoDAO.setConnection(connection);
            Veiculo veiculo = new Veiculo();

            
            TestDrive testdrive = new TestDrive();
            testdrive.setCdTestDrive(resultado.getInt("codTestDrive"));
            testdrive.setDuracao(resultado.getInt("duracao"));
            testdrive.setData(resultado.getDate("data").toLocalDate());
            
            cliente.setCdCliente(resultado.getInt("codCliente"));
            veiculo.setCdVeiculo(resultado.getInt("codVeiculo"));
            
            cliente = clienteDAO.buscar(cliente);
            veiculo = veiculoDAO.buscar(veiculo);
            
            testdrive.setCliente(cliente);
            testdrive.setVeiculo(veiculo);
            retorno.add(testdrive);
        }
    } catch (SQLException ex) {
        Logger.getLogger(TestDriveDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return retorno;
}
    


    
    public TestDrive buscar(TestDrive testdrive) {
        String sql = "SELECT * FROM testdrive WHERE  codTestDrive=?";
        TestDrive retorno = new TestDrive();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, testdrive.getCdTestDrive());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                testdrive.setDuracao(resultado.getInt("duracao "));               
            
                retorno = testdrive;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDriveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    } 

    
 
     
    
}
