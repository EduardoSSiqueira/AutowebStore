/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.IntegerValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lojacarros.model.Cliente;
import lojacarros.model.TestDrive;
import lojacarros.model.Veiculo;
import lojacarros.model.dao.ClienteDAO;
import lojacarros.model.dao.TestDriveDAO;
import lojacarros.model.dao.VeiculoDAO;
import lojacarros.model.database.Database;
import lojacarros.model.database.DatabaseFactory;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLProcessoTestDriveInserirController implements Initializable {

    @FXML
    private JFXDatePicker datePickerData;

    @FXML
    private JFXComboBox<Veiculo> comboBoxVeiculo;

    @FXML
    private JFXComboBox<Cliente> comboBoxCliente;

    @FXML
    private JFXTextField textFieldDuracao; 
    
    @FXML
    private JFXButton buttonConfirmar;
    
    private List<Veiculo> listVeiculo;
    private List<Cliente> listCliente;
    
    private ObservableList<Veiculo> observableListVeiculo;
    private ObservableList<Cliente> observableListCliente;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    private final TestDriveDAO testDriveDAO = new TestDriveDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();
    
    private TestDrive testDrive;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        veiculoDAO.setConnection(connection);
        testDriveDAO.setConnection(connection);
        
        carregarComboBoxVeiculo();
        carregarComboBoxCliente();
      
        //Declaração dos objetos de validações de dados
        RequiredFieldValidator validadorCampoObrigatorio = new RequiredFieldValidator();
        IntegerValidator validadorInteiro = new IntegerValidator();
        
        //Configuração dos objetos de validações de dados
        validadorCampoObrigatorio.setMessage("Campo deve ser preenchido");
        validadorInteiro.setMessage("Insira um inteiro");
        
        //Adição dos objetos de validações de dados nos componentes visuais
        textFieldDuracao.getValidators().add(validadorInteiro);
        textFieldDuracao.getValidators().add(validadorCampoObrigatorio);
        
        //Criação de Listeners para validação imediata após inserção de algum valor
        textFieldDuracao.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal)textFieldDuracao.validate();
        });
    }    
    
    
    public TestDrive getTestDrive() {
        return this.testDrive;
    }
    
    public void setTestDrive(TestDrive testDrive) {
        this.testDrive = testDrive;
        
        
        comboBoxVeiculo.getSelectionModel().select(testDrive.getVeiculo());
        comboBoxCliente.getSelectionModel().select(testDrive.getCliente());

    }
    


  
    
    
    public void carregarComboBoxVeiculo() {
        listVeiculo = veiculoDAO.listar();
        observableListVeiculo = FXCollections.observableArrayList(listVeiculo);
        comboBoxVeiculo.setItems(observableListVeiculo);
    }

    public void carregarComboBoxCliente() {
        listCliente = clienteDAO.listar();
        observableListCliente = FXCollections.observableArrayList(listCliente);
        comboBoxCliente.setItems(observableListCliente);
    }
    
    
    
    @FXML
    public void handleButtonConfirmar() {
            this.testDrive = new TestDrive();
            testDrive.setCliente(((Cliente)comboBoxCliente.getSelectionModel().getSelectedItem()));
            testDrive.setVeiculo(((Veiculo) comboBoxVeiculo.getSelectionModel().getSelectedItem()));
            testDrive.setDuracao(Integer.parseInt(textFieldDuracao.getText()));
            testDrive.setData(datePickerData.getValue());
            //Regra de negocio
                    try{
                        connection.setAutoCommit(false);
                        veiculoDAO.setConnection(connection);
                        clienteDAO.setConnection(connection);
                        testDriveDAO.inserir(testDrive);
                        testDriveDAO.alterar(testDrive);
                        connection.commit();
                        limparCampos();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("Test Drive Registrado");
                        alert.show();
                    }catch (SQLException ex){
                        try{
                            connection.rollback();
                        } catch (SQLException ex1) {
                            Logger.getLogger(FXMLProcessoTestDriverAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                        Logger.getLogger(FXMLProcessoTestDriverAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex);
                    }            
        
    }

    
    
    public void limparCampos(){
        comboBoxVeiculo.getSelectionModel().clearSelection();
        comboBoxCliente.getSelectionModel().clearSelection();
        textFieldDuracao.clear();
        datePickerData.getEditor().clear();
        datePickerData.setValue(null);
    }
    
    
    
    
}
