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
import lojacarros.model.Consorcio;
import lojacarros.model.TestDrive;
import lojacarros.model.Veiculo;
import lojacarros.model.dao.ConsorcioDAO;
import lojacarros.model.dao.TestDriveDAO;
import lojacarros.model.database.Database;
import lojacarros.model.database.DatabaseFactory;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLProcessoConsorcioListarController implements Initializable {

   
    @FXML
    private TableView<Consorcio> tableViewConsorcio;

    @FXML
    private TableColumn<Consorcio, Integer> tableColumnCodigo;

    @FXML
    private TableColumn<Consorcio, Cliente> tableColumnCliente;

    @FXML
    private TableColumn<Consorcio, Veiculo> tableColumnVeiculo;

    @FXML
    private TableColumn<Consorcio, String> tableColumnValorDataCredito;

    @FXML
    private TableColumn<Consorcio, String> tableColumnValorParcelas;

    @FXML
    private TableColumn<Consorcio, String> tableColumnTaxasAdministrativas;

    @FXML
    private TableColumn<Consorcio, String> tableColumnPrazoPagamento;

    
    private List<Consorcio> listConsorcio;
    private ObservableList<Consorcio> observableListTestDrive;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();   
    private final ConsorcioDAO consorcioDAO = new ConsorcioDAO();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        consorcioDAO.setConnection(connection);
        carregarTableView();
    }    
  
    
    public void carregarTableView(){
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("idConsorcio"));
        tableColumnCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tableColumnVeiculo.setCellValueFactory(new PropertyValueFactory<>("veiculo"));
        tableColumnValorDataCredito.setCellValueFactory(new PropertyValueFactory<>("valorCartaCredito"));
        tableColumnValorParcelas.setCellValueFactory(new PropertyValueFactory<>("valorParcelas"));
        tableColumnTaxasAdministrativas.setCellValueFactory(new PropertyValueFactory<>("taxasAdministrativas"));
        tableColumnPrazoPagamento.setCellValueFactory(new PropertyValueFactory<>("prazoPagamento"));
 
        
        listConsorcio = consorcioDAO.listar();
        observableListTestDrive = FXCollections.observableArrayList(listConsorcio);
        tableViewConsorcio.setItems(observableListTestDrive);
    }
}
