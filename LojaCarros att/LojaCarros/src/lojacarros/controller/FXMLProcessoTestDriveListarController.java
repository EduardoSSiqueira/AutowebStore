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
import lojacarros.model.TestDrive;
import lojacarros.model.Veiculo;
import lojacarros.model.dao.TestDriveDAO;
import lojacarros.model.database.Database;
import lojacarros.model.database.DatabaseFactory;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLProcessoTestDriveListarController implements Initializable {

    @FXML
    private TableView<TestDrive> tableViewTestDrive;

    @FXML
    private TableColumn<TestDrive, Integer> tableColumnCodigo;

    @FXML
    private TableColumn<TestDrive, Cliente> tableColumnCliente;

    @FXML
    private TableColumn<TestDrive, Veiculo> tableColumnVeiculo;

    @FXML
    private TableColumn<TestDrive, String> tableColumnData;

    @FXML
    private TableColumn<TestDrive, String> tableColumnDuracao;  
    
        
    private List<TestDrive> listTestDrive;
    private ObservableList<TestDrive> observableListTestDrive;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();   
    private final TestDriveDAO testDriveDAO = new TestDriveDAO();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        testDriveDAO.setConnection(connection);
        carregarTableView();
    }    
    
    public void carregarTableView(){
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("cdTestDrive"));
        tableColumnCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tableColumnVeiculo.setCellValueFactory(new PropertyValueFactory<>("veiculo"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableColumnDuracao.setCellValueFactory(new PropertyValueFactory<>("duracao"));
 
        
        listTestDrive = testDriveDAO.listar();
        observableListTestDrive = FXCollections.observableArrayList(listTestDrive);
        tableViewTestDrive.setItems(observableListTestDrive);
    }
    
}
