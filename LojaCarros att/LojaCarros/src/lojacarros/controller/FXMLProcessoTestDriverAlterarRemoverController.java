/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
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
import javafx.util.StringConverter;
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
public class FXMLProcessoTestDriverAlterarRemoverController implements Initializable {

  @FXML
    private JFXComboBox<Cliente> comboBoxCliente;

    @FXML
    private JFXComboBox<Veiculo> comboBoxVeiculo;

    @FXML
    private JFXTextField textFieldDuracao;

    @FXML
    private JFXButton buttonAlterar;

    @FXML
    private JFXButton buttonRemover;

    @FXML
    private JFXListView<TestDrive> listViewTestDrive;

    @FXML
    private JFXDatePicker datePickerData; 
    
    private List<Veiculo> listVeiculo;
    private List<Cliente> listCliente; 
    private List<TestDrive> listTestDrive;


    private ObservableList<Veiculo> observableListVeiculo;
    private ObservableList<Cliente> observableListCliente;
    private ObservableList<TestDrive> observableListTestDrive;


    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    private final TestDriveDAO testDriveDAO = new TestDriveDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        testDriveDAO.setConnection(connection);
        clienteDAO.setConnection(connection);
        veiculoDAO.setConnection(connection);

        carregarComboBoxCliente();
        carregarComboBoxVeiculo();
        carregarListView();
        
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

        listViewTestDrive.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemListView(newValue));
    }    
    
    public void carregarListView(){
        listTestDrive = testDriveDAO.listar();
        observableListTestDrive = FXCollections.observableArrayList(listTestDrive);
        listViewTestDrive.setItems(observableListTestDrive);
    }
    
    public void selecionarItemListView(TestDrive testdrive){
        if(testdrive != null){
            comboBoxCliente.getSelectionModel().select(testdrive.getCliente());            
            comboBoxVeiculo.getSelectionModel().select(testdrive.getVeiculo());
            datePickerData.setValue(testdrive.getData());
            textFieldDuracao.setText(String.valueOf(testdrive.getDuracao()));
        }else{
            limparCampos();
        }
    }
    
    public void carregarComboBoxCliente(){
        comboBoxCliente.setEditable(true);
        comboBoxCliente.getEditor().setEditable(false);
        comboBoxCliente.setConverter(new StringConverter<Cliente>() {
            @Override
            public String toString(Cliente object) {
                if (object == null) return null;
                return object.toString();
            }

            @Override
            public Cliente fromString(String string) {
                return comboBoxCliente.getSelectionModel().getSelectedItem();
            }
        });
                
        listCliente = clienteDAO.listar();
        observableListCliente = FXCollections.observableArrayList(listCliente);
        comboBoxCliente.setItems(observableListCliente);
    }
    
    public void carregarComboBoxVeiculo(){
        comboBoxVeiculo.setEditable(true);
        comboBoxVeiculo.getEditor().setEditable(false);
        comboBoxVeiculo.setConverter(new StringConverter<Veiculo>() {
            @Override
            public String toString(Veiculo object) {
                if (object == null) return null;
                return object.toString();
            }

            @Override
            public Veiculo fromString(String string) {
                return comboBoxVeiculo.getSelectionModel().getSelectedItem();
            }
        });
                
        listVeiculo = veiculoDAO.listar();
        observableListVeiculo = FXCollections.observableArrayList(listVeiculo);
        comboBoxVeiculo.setItems(observableListVeiculo);
    }
    
    
    @FXML
    public void handleButtonAlterar(){
        if(listViewTestDrive.getSelectionModel().getSelectedItem() != null){
                TestDrive testdrive = listViewTestDrive.getSelectionModel().getSelectedItem();
                try{
                    connection.setAutoCommit(false);
                    testDriveDAO.setConnection(connection);
                    clienteDAO.setConnection(connection);
                    Cliente cliente = testdrive.getCliente();
          
                    testDriveDAO.alterar(testdrive);
                    clienteDAO.alterar(cliente);
                    
                    testdrive.setCliente((Cliente)comboBoxCliente.getSelectionModel().getSelectedItem());
                    testdrive.setVeiculo((Veiculo)comboBoxVeiculo.getSelectionModel().getSelectedItem());
                    testdrive.setData(datePickerData.getValue());
                    testdrive.setDuracao(Integer.parseInt(textFieldDuracao.getText()));
                    
                
                    testDriveDAO.alterar(testdrive);
                    cliente = testdrive.getCliente();
                    clienteDAO.alterar(cliente);
                    connection.commit();
                    carregarListView();
                    limparCampos();
                    listViewTestDrive.getSelectionModel().clearSelection();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Alteração registrada com sucesso");
                    alert.show();                            
           
                }catch(SQLException ex){
                    try{
                        connection.rollback();
                    }catch (SQLException ex1) {
                        Logger.getLogger(FXMLProcessoTestDriverAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    Logger.getLogger(FXMLProcessoTestDriverAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Os dados estão incorretos, vazios ou possuem valores negativos, favor revisar!");
                alert.show();
            }
    }
    
    @FXML
    public void handleButtonRemover(){
        if(listViewTestDrive.getSelectionModel().getSelectedItem() != null){
            TestDrive testdrive = listViewTestDrive.getSelectionModel().getSelectedItem();
            
            try{
                connection.setAutoCommit(false);
                Cliente cliente = testdrive.getCliente();
                
                clienteDAO.alterar(cliente);
                testDriveDAO.remover(testdrive);
                connection.commit();
                
                listViewTestDrive.getSelectionModel().clearSelection();
                limparCampos();
                carregarListView();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Test Drive deletado com sucesso!");
                alert.show(); 
            }catch (SQLException ex){
                try{
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(FXMLProcessoTestDriverAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(FXMLProcessoTestDriverAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex);   
            }
        }else{
            
        }
    }
    
    
    
    public void limparCampos(){
        comboBoxCliente.getSelectionModel().clearSelection();
        comboBoxVeiculo.getSelectionModel().clearSelection();
        datePickerData.getEditor().clear();
        datePickerData.setValue(null);
        textFieldDuracao.clear();
    }
    
    
   
    


    
}
