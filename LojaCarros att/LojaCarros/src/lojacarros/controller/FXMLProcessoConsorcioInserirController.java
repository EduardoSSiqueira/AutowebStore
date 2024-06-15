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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lojacarros.model.Cliente;
import lojacarros.model.Consorcio;
import lojacarros.model.TestDrive;
import lojacarros.model.Veiculo;
import lojacarros.model.dao.ClienteDAO;
import lojacarros.model.dao.ConsorcioDAO;
import lojacarros.model.dao.TestDriveDAO;
import lojacarros.model.dao.VeiculoDAO;
import lojacarros.model.database.Database;
import lojacarros.model.database.DatabaseFactory;


/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLProcessoConsorcioInserirController implements Initializable {


    @FXML
    private JFXDatePicker datePickerPrazoPagamento;

    @FXML
    private JFXComboBox<Veiculo> comboBoxVeiculo;

    @FXML
    private JFXComboBox<Cliente> comboBoxCliente;

    @FXML
    private JFXButton buttonConfirmar;

    @FXML
    private JFXTextField textFieldValorCartaCredito;

    @FXML
    private JFXTextField textFieldValorParcelas;

    @FXML
    private JFXTextField textFieldTaxasAdministrativas;
       
    private List<Veiculo> listVeiculo;
    private List<Cliente> listCliente;
    
    private ObservableList<Veiculo> observableListVeiculo;
    private ObservableList<Cliente> observableListCliente;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
   private final ConsorcioDAO consorcioDAO = new ConsorcioDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();

    private Consorcio consorcio;
    @FXML

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        veiculoDAO.setConnection(connection);
        consorcioDAO.setConnection(connection);
        
        carregarComboBoxVeiculo();
        carregarComboBoxCliente();
    }    
    
    public Consorcio getConsorcio() {
        return this.consorcio;
    }
    
    public void setConsorcio(Consorcio consorcio) {
        this.consorcio = consorcio;
        
        
       comboBoxVeiculo.getSelectionModel().select(consorcio.getVeiculo());
       comboBoxCliente.getSelectionModel().select(consorcio.getCliente());
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
            this.consorcio = new Consorcio();
            consorcio.setCliente(((Cliente)comboBoxCliente.getSelectionModel().getSelectedItem()));
            consorcio.setVeiculo(((Veiculo) comboBoxVeiculo.getSelectionModel().getSelectedItem()));
            consorcio.setPrazoPagamento(datePickerPrazoPagamento.getValue());
            consorcio.setValorCartaCredito(textFieldValorCartaCredito.getText());
            consorcio.setValorParcelas(textFieldValorParcelas.getText());
            consorcio.setTaxasAdministrativas(textFieldTaxasAdministrativas.getText());
            
            //Regra de negocio
                    try{
                        connection.setAutoCommit(false);
                        veiculoDAO.setConnection(connection);
                        clienteDAO.setConnection(connection);
                        consorcioDAO.inserir(consorcio);
                        consorcioDAO.alterar(consorcio);
                        connection.commit();
                        limparCampos();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("Consorcio Registrado");
                        alert.show();
                    }catch (SQLException ex){
                        try{
                            connection.rollback();
                        } catch (SQLException ex1) {
                            Logger.getLogger(FXMLProcessoConsorcioAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                        Logger.getLogger(FXMLProcessoConsorcioAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex);
                    }            
        
    }
        public void limparCampos(){
        comboBoxVeiculo.getSelectionModel().clearSelection();
        comboBoxCliente.getSelectionModel().clearSelection();
        textFieldValorCartaCredito.clear();
        datePickerPrazoPagamento.getEditor().clear();
        datePickerPrazoPagamento.setValue(null);
        textFieldValorParcelas.clear();
        textFieldTaxasAdministrativas.clear();    
    }
    
    
        
}
