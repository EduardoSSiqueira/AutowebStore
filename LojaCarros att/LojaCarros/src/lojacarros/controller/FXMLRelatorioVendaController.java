/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lojacarros.model.Cliente;
import lojacarros.model.Venda;
import lojacarros.model.dao.VendaDAO;
import lojacarros.model.database.Database;
import lojacarros.model.database.DatabaseFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLRelatorioVendaController implements Initializable {

  @FXML
    private TableView<Venda> tableViewTestDrive;

    @FXML
    private TableColumn<Venda, Cliente> tableColumnCliente;  

    //String
    @FXML
    private TableColumn<Venda, String> tableColumnVeiculo;
    
    @FXML
    private JFXButton  buttonImprimir;

    
    private List<Venda> listVenda;
    private ObservableList<Venda> observableListTestDrive;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final VendaDAO vendaDAO = new VendaDAO();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vendaDAO.setConnection(connection);
        carregarTableView();
    }    
    
    public void carregarTableView(){
        tableColumnCliente.setCellValueFactory(new PropertyValueFactory<>("clienteVenda"));
        tableColumnVeiculo.setCellValueFactory(new PropertyValueFactory<>("veiculoVenda"));
 
        
        listVenda = vendaDAO.listar();
        observableListTestDrive = FXCollections.observableArrayList(listVenda);
        tableViewTestDrive.setItems(observableListTestDrive);
    } 
    
    public void handleImprimir() throws JRException{
        
        URL url = getClass().getResource("/lojacarros/relatorios/Relatorio Venda.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);//null: caso não existam filtros
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);//false: não deixa fechar a aplicação principal
        jasperViewer.setVisible(true);
    }
    
}
