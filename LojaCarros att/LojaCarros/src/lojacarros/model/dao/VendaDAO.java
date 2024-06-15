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
import lojacarros.model.Funcionario;
import lojacarros.model.Veiculo;
import lojacarros.model.Venda;

/**
 *
 * @author 20201si029
 */
public class VendaDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Venda venda) {
        String sql = "INSERT INTO venda(clienteVenda, veiculoVenda, funcVenda, valorVenda, dataVenda) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, venda.getClienteVenda().getCdCliente());
            stmt.setInt(2, venda.getVeiculoVenda().getCdVeiculo());
            stmt.setInt(3, venda.getFuncVenda().getCodFunc());
            stmt.setString(4, venda.getValorVenda());
            stmt.setDate(5, Date.valueOf(venda.getDataVenda()));
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    public boolean alterar(Venda venda) {
        String sql = "UPDATE venda SET clienteVenda=?, veiculoVenda=?, funcVenda=?, valorVenda=?, dataVenda=? WHERE codVenda =?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, venda.getClienteVenda().getCdCliente());
            stmt.setInt(2, venda.getVeiculoVenda().getCdVeiculo());
            stmt.setInt(3, venda.getFuncVenda().getCodFunc());
            stmt.setString(4, venda.getValorVenda());
            stmt.setDate(5, Date.valueOf(venda.getDataVenda()));
            stmt.setInt(6, venda.getCodVenda());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    public boolean remover(Venda venda) {
        String sql = "DELETE FROM venda  WHERE codVenda=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, venda.getCodVenda());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
       
    
    public List<Venda> listar() {
    String sql = "SELECT * FROM venda";
    List<Venda> retorno = new ArrayList<>();
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
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarioDAO.setConnection(connection);
            Funcionario funcionario = new Funcionario();

            
            Venda venda = new Venda();
            venda.setCodVenda(resultado.getInt("codVenda"));
            venda.setValorVenda(resultado.getString("valorVenda"));
            venda.setDataVenda(resultado.getDate("dataVenda").toLocalDate());
            
            cliente.setCdCliente(resultado.getInt("clienteVenda"));
            veiculo.setCdVeiculo(resultado.getInt("veiculoVenda"));
            funcionario.setCodFunc(resultado.getInt("funcVenda"));
            
            cliente = clienteDAO.buscar(cliente);
            veiculo = veiculoDAO.buscar(veiculo);
            funcionario = funcionarioDAO.buscar(funcionario);
            
            venda.setClienteVenda(cliente);
            venda.setVeiculoVenda(veiculo);
            venda.setFuncVenda(funcionario);
            retorno.add(venda);
        }
    } catch (SQLException ex) {
        Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return retorno;
}
    


    
    public Venda buscar(Venda venda) {
        String sql = "SELECT * FROM venda WHERE  codVenda=?";
        Venda retorno = new Venda();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, venda.getCodVenda());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                venda.setValorVenda(resultado.getString("valorVenda "));               
            
                retorno = venda;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    } 
}
