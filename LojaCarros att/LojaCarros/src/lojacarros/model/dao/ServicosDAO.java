
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
import lojacarros.model.Servico;

/**
 *
 * @author 20201si029
 */
public class ServicosDAO {
     private Connection connection;

     
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
        
    public boolean inserir(Servico servico) {
        String sql = "INSERT INTO servico(tipoServico ,  proprietario, material, pecasUtilizadas, data, custos, veiculo) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, servico.getTipoServico());
            stmt.setString(2, servico.getProprietario());
            stmt.setString(3, servico.getMaterial());
            stmt.setString(4, servico.getPecasUtilizadas());
            stmt.setDate(5, Date.valueOf(servico.getData()));
            stmt.setString(6, servico.getCustos());
            stmt.setString(7, servico.getVeiculo());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
     public boolean alterar(Servico servico) {
        String sql = "UPDATE servico SET tipoServico=?, proprietario=?, material=?, pecasUtilizadas =?, data =?, custos =?, veiculo=? WHERE codServico=?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, servico.getTipoServico());
            stmt.setString(2, servico.getProprietario());
            stmt.setString(3, servico.getMaterial());
            stmt.setString(4, servico.getPecasUtilizadas());
            stmt.setDate(5, Date.valueOf(servico.getData()));
            stmt.setString(6, servico.getCustos());
            stmt.setString(7, servico.getVeiculo());
            stmt.setInt(8, servico.getCodServico());
            
            
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
        public boolean remover(Servico servico) {
        String sql = "DELETE FROM servico WHERE codServico=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, servico.getCodServico());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
        
       public List<Servico> listar() {
        String sql = "SELECT * FROM servico";
        List<Servico> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Servico servico = new Servico();
                servico.setCodServico(resultado.getInt("codServico"));
                servico.setTipoServico(resultado.getString("tipoServico"));
                servico.setProprietario(resultado.getString("proprietario"));
                servico.setMaterial(resultado.getString("material"));
                servico.setPecasUtilizadas(resultado.getString("pecasUtilizadas"));
                servico.setData(resultado.getDate("data").toLocalDate());
                servico.setCustos(resultado.getString("custos"));
                servico.setVeiculo(resultado.getString("veiculo"));
                retorno.add(servico);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
        public Servico buscar(Servico servico) {
        String sql = "SELECT * FROM servico WHERE codServico=?";
        Servico retorno = new Servico();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, servico.getCodServico());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                servico.setTipoServico(resultado.getString("tipoServico"));
                servico.setProprietario(resultado.getString("proprietario"));
                servico.setMaterial(resultado.getString("material"));
                servico.setPecasUtilizadas(resultado.getString("pecasUtilizadas"));
                servico.setData(resultado.getDate("data").toLocalDate());
                servico.setCustos(resultado.getString("custos"));
                servico.setVeiculo(resultado.getString("veiculo"));
                
                retorno = servico;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }         
}
