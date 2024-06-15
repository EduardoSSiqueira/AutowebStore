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
import lojacarros.model.TestDrive;

import lojacarros.model.Consorcio;
import lojacarros.model.Veiculo;
public class ConsorcioDAO {
      private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    } 
        public boolean inserir(Consorcio consorcio) {
        String sql = "INSERT INTO consorcio(clienteConsorcio, veiculoConsorcio, valorCartaCredito, prazoPagamento,valorParcelas,taxasAdministrativas) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, consorcio.getCliente().getCdCliente());
            stmt.setInt(2, consorcio.getVeiculo().getCdVeiculo());
            stmt.setString(3, consorcio.getValorCartaCredito());
            stmt.setDate(4, Date.valueOf(consorcio.getPrazoPagamento()));
            stmt.setString(5, consorcio.getValorParcelas());
            stmt.setString(6, consorcio.getTaxasAdministrativas());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConsorcioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean alterar(Consorcio consorcio) {
        String sql = "UPDATE consorcio SET clienteConsorcio=?, veiculoConsorcio=?, valorCartaCredito=?, prazoPagamento=?, valorParcelas=?, taxasAdministrativas=? WHERE idConsorcio=?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, consorcio.getCliente().getCdCliente());
            stmt.setInt(2, consorcio.getVeiculo().getCdVeiculo());
            stmt.setString(3, consorcio.getValorCartaCredito());         
            stmt.setDate(4, Date.valueOf(consorcio.getPrazoPagamento())); 
            stmt.setString(5, consorcio.getValorParcelas());
            stmt.setString(6, consorcio.getTaxasAdministrativas());
            stmt.setInt(7, consorcio.getIdConsorcio());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConsorcioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }   
        
    public boolean remover(Consorcio consorcio) {
        String sql = "DELETE FROM consorcio  WHERE idConsorcio=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, consorcio.getIdConsorcio());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConsorcioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Consorcio> listar() {
    String sql = "SELECT * FROM consorcio";
    List<Consorcio> retorno = new ArrayList<>();
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

            
            Consorcio consorcio = new Consorcio();
            consorcio.setIdConsorcio(resultado.getInt("idConsorcio"));
            consorcio.setValorCartaCredito(resultado.getString("valorCartaCredito"));
            consorcio.setPrazoPagamento(resultado.getDate("prazoPagamento").toLocalDate());
            consorcio.setValorParcelas(resultado.getString("valorParcelas"));
            consorcio.setTaxasAdministrativas(resultado.getString("taxasAdministrativas"));
            
            cliente.setCdCliente(resultado.getInt("clienteConsorcio"));
            veiculo.setCdVeiculo(resultado.getInt("veiculoConsorcio"));
            
            cliente = clienteDAO.buscar(cliente);
            veiculo = veiculoDAO.buscar(veiculo);
            
            consorcio.setCliente(cliente);
            consorcio.setVeiculo(veiculo);
            retorno.add(consorcio);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ConsorcioDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return retorno;
}
    
        public Consorcio buscar(Consorcio consorcio) {
        String sql = "SELECT * FROM consorcio WHERE  idConsorcio=?";
        Consorcio retorno = new Consorcio();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, consorcio.getIdConsorcio());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                consorcio.setPrazoPagamento(resultado.getDate("prazoPagamento").toLocalDate());               
            
                retorno = consorcio;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsorcioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    } 

}
