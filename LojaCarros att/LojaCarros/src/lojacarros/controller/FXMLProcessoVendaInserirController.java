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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lojacarros.model.Cliente;
import lojacarros.model.Funcionario;
import lojacarros.model.TestDrive;
import lojacarros.model.Veiculo;
import lojacarros.model.Venda;
import lojacarros.model.dao.ClienteDAO;
import lojacarros.model.dao.FuncionarioDAO;
import lojacarros.model.dao.VeiculoDAO;
import lojacarros.model.dao.VendaDAO;
import lojacarros.model.database.Database;
import lojacarros.model.database.DatabaseFactory;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLProcessoVendaInserirController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private JFXDatePicker datePickerData;

    @FXML
    private JFXComboBox<Veiculo> comboBoxVeiculo;

    @FXML
    private JFXComboBox<Cliente> comboBoxCliente;

    @FXML
    private JFXButton buttonConfirmar;

    @FXML
    private JFXComboBox<Funcionario> comboBoxFuncionario;

    @FXML
    private JFXTextField textFieldValor;
    
    private List<Veiculo> listVeiculo;
    private List<Cliente> listCliente;
    private List<Funcionario> listFuncionario;
    
    private ObservableList<Veiculo> observableListVeiculo;
    private ObservableList<Cliente> observableListCliente;
    private ObservableList<Funcionario> observableListFuncionario;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    private final VendaDAO vendaDAO = new VendaDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    private Venda venda;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        veiculoDAO.setConnection(connection);
        funcionarioDAO.setConnection(connection);
        vendaDAO.setConnection(connection);
        
        carregarComboBoxVeiculo();
        carregarComboBoxCliente();
        carregarComboBoxFuncionario();
      
        //Declaração dos objetos de validações de dados
        RequiredFieldValidator validadorCampoObrigatorio = new RequiredFieldValidator();
        IntegerValidator validadorInteiro = new IntegerValidator();
        
        //Configuração dos objetos de validações de dados
        validadorCampoObrigatorio.setMessage("Campo deve ser preenchido");
        validadorInteiro.setMessage("Insira um inteiro");
        
        //Adição dos objetos de validações de dados nos componentes visuais
        textFieldValor.getValidators().add(validadorInteiro);
        textFieldValor.getValidators().add(validadorCampoObrigatorio);
        
        //Criação de Listeners para validação imediata após inserção de algum valor
        textFieldValor.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal)textFieldValor.validate();
        });
    }
    
    
    public Venda getVenda() {
        return this.venda;
    }
    
    public void setVenda(Venda venda) {
        this.venda = venda;
        
        
        comboBoxVeiculo.getSelectionModel().select(venda.getVeiculoVenda());
        comboBoxCliente.getSelectionModel().select(venda.getClienteVenda());
        comboBoxFuncionario.getSelectionModel().select(venda.getFuncVenda());

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
    
    public void carregarComboBoxFuncionario() {
        listFuncionario = funcionarioDAO.listar();
        observableListFuncionario = FXCollections.observableArrayList(listFuncionario);
        comboBoxFuncionario.setItems(observableListFuncionario);
    }
    
    
    
    @FXML
    public void handleButtonConfirmar() {
            this.venda = new Venda();
            venda.setClienteVenda(((Cliente)comboBoxCliente.getSelectionModel().getSelectedItem()));
            venda.setVeiculoVenda(((Veiculo) comboBoxVeiculo.getSelectionModel().getSelectedItem()));
            venda.setFuncVenda(((Funcionario) comboBoxFuncionario.getSelectionModel().getSelectedItem()));
            venda.setValorVenda((textFieldValor.getText()));
            venda.setDataVenda(datePickerData.getValue());
            //Regra de negocio
                    try{
                        connection.setAutoCommit(false);
                        veiculoDAO.setConnection(connection);
                        clienteDAO.setConnection(connection);
                        funcionarioDAO.setConnection(connection);
                        vendaDAO.inserir(venda);
                        vendaDAO.alterar(venda);
                        connection.commit();
                        limparCampos();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("Venda Registrada");
                        alert.show();
                    }catch (SQLException ex){
                        try{
                            connection.rollback();
                        } catch (SQLException ex1) {
                            Logger.getLogger(FXMLProcessoVendaAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                        Logger.getLogger(FXMLProcessoVendaAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex);
                    }            
        
    }

    
    
    public void limparCampos(){
        comboBoxVeiculo.getSelectionModel().clearSelection();
        comboBoxCliente.getSelectionModel().clearSelection();
        comboBoxFuncionario.getSelectionModel().clearSelection();
    //    textFieldVenda.clear();
        datePickerData.getEditor().clear();
        datePickerData.setValue(null);
    }
    
    
    
}
