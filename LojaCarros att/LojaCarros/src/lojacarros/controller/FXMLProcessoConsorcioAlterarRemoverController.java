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
import lojacarros.model.Consorcio;
import lojacarros.model.Veiculo;
import lojacarros.model.dao.ClienteDAO;
import lojacarros.model.dao.ConsorcioDAO;
import lojacarros.model.dao.VeiculoDAO;
import lojacarros.model.database.Database;
import lojacarros.model.database.DatabaseFactory;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLProcessoConsorcioAlterarRemoverController implements Initializable {

    @FXML
    private JFXDatePicker datePickerPrazoPagamento;

    @FXML
    private JFXComboBox<Veiculo> comboBoxVeiculo;

    @FXML
    private JFXComboBox<Cliente> comboBoxCliente;

    @FXML
    private JFXTextField textFieldValorCartaCredito;

    @FXML
    private JFXTextField textFieldValorParcelas;

    @FXML
    private JFXTextField textFieldTaxasAdministrativas;

    @FXML
    private JFXListView<Consorcio> listViewConsorcio;

    @FXML
    private JFXButton buttonAlterar;

    @FXML
    private JFXButton buttonRemover;

    
    private List<Veiculo> listVeiculo;
    private List<Cliente> listCliente; 
    private List<Consorcio> listConsorcio;


    private ObservableList<Veiculo> observableListVeiculo;
    private ObservableList<Cliente> observableListCliente;
    private ObservableList<Consorcio> observableListConsorcio;


    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    private final ConsorcioDAO consorcioDAO = new ConsorcioDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        consorcioDAO.setConnection(connection);
        clienteDAO.setConnection(connection);
        veiculoDAO.setConnection(connection);
        
        
        carregarComboBoxCliente();
        carregarComboBoxVeiculo();
        carregarListView();
   
        listViewConsorcio.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemListView(newValue));
    }    
    
    public void carregarListView(){
        listConsorcio = consorcioDAO.listar();
        observableListConsorcio = FXCollections.observableArrayList(listConsorcio);
        listViewConsorcio.setItems(observableListConsorcio);
    }
       
    public void selecionarItemListView(Consorcio consorcio){
        if(consorcio != null){
            comboBoxCliente.getSelectionModel().select(consorcio.getCliente());            
            comboBoxVeiculo.getSelectionModel().select(consorcio.getVeiculo());
            consorcio.setPrazoPagamento(datePickerPrazoPagamento.getValue());
            consorcio.setValorCartaCredito(textFieldValorCartaCredito.getText());
            consorcio.setValorParcelas(textFieldValorParcelas.getText());
            consorcio.setTaxasAdministrativas(textFieldTaxasAdministrativas.getText());
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
        if(listViewConsorcio.getSelectionModel().getSelectedItem() != null){
                Consorcio consorcio = listViewConsorcio.getSelectionModel().getSelectedItem();
                try{
                    connection.setAutoCommit(false);
                    consorcioDAO.setConnection(connection);
                    clienteDAO.setConnection(connection);
                    Cliente cliente = consorcio.getCliente();
          
                    consorcioDAO.alterar(consorcio);
                    clienteDAO.alterar(cliente);
                    
                    consorcio.setCliente((Cliente)comboBoxCliente.getSelectionModel().getSelectedItem());
                    consorcio.setVeiculo((Veiculo)comboBoxVeiculo.getSelectionModel().getSelectedItem());
                    consorcio.setPrazoPagamento(datePickerPrazoPagamento.getValue());
                    consorcio.setValorCartaCredito(textFieldValorCartaCredito.getText());
                    consorcio.setValorParcelas(textFieldValorParcelas.getText());
                    consorcio.setTaxasAdministrativas(textFieldTaxasAdministrativas.getText());
                    
                
                    consorcioDAO.alterar(consorcio);
                    cliente = consorcio.getCliente();
                    clienteDAO.alterar(cliente);
                    connection.commit();
                    carregarListView();
                    limparCampos();
                    listViewConsorcio.getSelectionModel().clearSelection();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Alteração registrada com sucesso");
                    alert.show();                            
           
                }catch(SQLException ex){
                    try{
                        connection.rollback();
                    }catch (SQLException ex1) {
                        Logger.getLogger(FXMLProcessoConsorcioAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    Logger.getLogger(FXMLProcessoConsorcioAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Os dados estão incorretos, vazios ou possuem valores negativos, favor revisar!");
                alert.show();
            }
    }
    @FXML
    public void handleButtonRemover(){
        if(listViewConsorcio.getSelectionModel().getSelectedItem() != null){
            Consorcio consorcio = listViewConsorcio.getSelectionModel().getSelectedItem();
            
            try{
                connection.setAutoCommit(false);
                Cliente cliente = consorcio.getCliente();
                
                clienteDAO.alterar(cliente);
                consorcioDAO.remover(consorcio);
                connection.commit();
                
                listViewConsorcio.getSelectionModel().clearSelection();
                limparCampos();
                carregarListView();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Plano deletado com sucesso!");
                alert.show(); 
            }catch (SQLException ex){
                try{
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(FXMLProcessoConsorcioAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(FXMLProcessoConsorcioAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex);   
            }
        }else{
            
        }
    }
        public void limparCampos(){
        comboBoxVeiculo.getSelectionModel().clearSelection();
        comboBoxCliente.getSelectionModel().clearSelection();
        textFieldValorCartaCredito.clear();
        datePickerPrazoPagamento.getEditor().clear();
   //     datePwickerPrazoPagamento.setValue(null);
        textFieldValorParcelas.clear();
        textFieldTaxasAdministrativas.clear();    
    }
}
