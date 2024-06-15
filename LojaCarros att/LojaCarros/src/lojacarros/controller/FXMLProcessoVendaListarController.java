/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.controller;

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
import lojacarros.model.Funcionario;
import lojacarros.model.Veiculo;
import lojacarros.model.Venda;
import lojacarros.model.dao.VendaDAO;
import lojacarros.model.database.Database;
import lojacarros.model.database.DatabaseFactory;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLProcessoVendaListarController implements Initializable {

    /**
     * Initializes the controller class.
     */ 
    
     @FXML
    private TableView<Venda> tableViewVenda;

    @FXML
    private TableColumn<Venda, Integer> tableColumnCodigo;

    @FXML
    private TableColumn<Venda, Cliente> tableColumnCliente;

    @FXML
    private TableColumn<Venda, Funcionario> tableColumnFuncionario;

    @FXML
    private TableColumn<Venda, Veiculo> tableColumnVeiculo;

    @FXML
    private TableColumn<Venda, String> tableColumnData;

    @FXML
    private TableColumn<Venda, String> tableColumnValor;
    
    
    private List<Venda> listVenda;
    private ObservableList<Venda> observableListVenda;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();   
    private final VendaDAO vendaDAO = new VendaDAO();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vendaDAO.setConnection(connection);
        carregarTableView();
    }    
    
    public void carregarTableView(){
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codVenda"));
        tableColumnCliente.setCellValueFactory(new PropertyValueFactory<>("clienteVenda"));
        tableColumnVeiculo.setCellValueFactory(new PropertyValueFactory<>("veiculoVenda"));
        tableColumnFuncionario.setCellValueFactory(new PropertyValueFactory<>("funcVenda"));
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));

 
        
        listVenda= vendaDAO.listar();
        observableListVenda = FXCollections.observableArrayList(listVenda);
        tableViewVenda.setItems(observableListVenda);
    }
    
    
}
