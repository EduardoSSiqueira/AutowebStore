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
import lojacarros.model.Funcionario;
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
public class FXMLProcessoVendaAlterarRemoverController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
      @FXML
    private JFXComboBox<Cliente> comboBoxCliente;

    @FXML
    private JFXComboBox<Veiculo> comboBoxVeiculo;

    @FXML
    private JFXButton buttonAlterar;

    @FXML
    private JFXButton buttonRemover;

    @FXML
    private JFXListView<Venda> listViewVenda;

    @FXML
    private JFXDatePicker datePickerData;

    @FXML
    private JFXComboBox<Funcionario> comboBoxFuncionario;

    @FXML
    private JFXTextField textFieldValor;

    
    private List<Veiculo> listVeiculo;
    private List<Cliente> listCliente;
    private List<Funcionario> listFuncionario; 
    private List<Venda> listVenda;


    private ObservableList<Veiculo> observableListVeiculo;
    private ObservableList<Cliente> observableListCliente;
    private ObservableList<Funcionario> observableListFuncionario;
    private ObservableList<Venda> observableListVenda;


    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    private final VendaDAO vendaDAO = new VendaDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vendaDAO.setConnection(connection);
        clienteDAO.setConnection(connection);
        veiculoDAO.setConnection(connection);
        funcionarioDAO.setConnection(connection);

        carregarComboBoxCliente();
        carregarComboBoxVeiculo();
        carregarComboBoxFuncionario();
        carregarListView();
        
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

        listViewVenda.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemListView(newValue));
    }    
    
    
    
     public void carregarListView(){
        listVenda = vendaDAO.listar();
        observableListVenda = FXCollections.observableArrayList(listVenda);
        listViewVenda.setItems(observableListVenda);
    }
    
    public void selecionarItemListView(Venda venda){
        if(venda != null){
            comboBoxCliente.getSelectionModel().select(venda.getClienteVenda());            
            comboBoxVeiculo.getSelectionModel().select(venda.getVeiculoVenda());
            comboBoxFuncionario.getSelectionModel().select(venda.getFuncVenda());
            datePickerData.setValue(venda.getDataVenda());
            textFieldValor.setText(String.valueOf(venda.getValorVenda()));
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
    
    public void carregarComboBoxFuncionario(){
        comboBoxFuncionario.setEditable(true);
        comboBoxFuncionario.getEditor().setEditable(false);
        comboBoxFuncionario.setConverter(new StringConverter<Funcionario>() {
            @Override
            public String toString(Funcionario object) {
                if (object == null) return null;
                return object.toString();
            }

            @Override
            public Funcionario fromString(String string) {
                return comboBoxFuncionario.getSelectionModel().getSelectedItem();
            }
        });
                
        listFuncionario = funcionarioDAO.listar();
        observableListFuncionario = FXCollections.observableArrayList(listFuncionario);
        comboBoxFuncionario.setItems(observableListFuncionario);
    }
    
    
    @FXML
    public void handleButtonAlterar(){
        if(listViewVenda.getSelectionModel().getSelectedItem() != null){
                Venda venda = listViewVenda.getSelectionModel().getSelectedItem();
                try{
                    connection.setAutoCommit(false);
                    vendaDAO.setConnection(connection);
                    clienteDAO.setConnection(connection);
                    Cliente cliente = venda.getClienteVenda();
          
                    vendaDAO.alterar(venda);
                    clienteDAO.alterar(cliente);
                    
                    venda.setClienteVenda((Cliente)comboBoxCliente.getSelectionModel().getSelectedItem());
                    venda.setFuncVenda((Funcionario)comboBoxFuncionario.getSelectionModel().getSelectedItem());
                    venda.setVeiculoVenda((Veiculo)comboBoxVeiculo.getSelectionModel().getSelectedItem());
                    venda.setDataVenda(datePickerData.getValue());
                    venda.setValorVenda(textFieldValor.getText());
                    
                
                    vendaDAO.alterar(venda);
                    cliente = venda.getClienteVenda();
                    clienteDAO.alterar(cliente);
                    connection.commit();
                    carregarListView();
                    limparCampos();
                    listViewVenda.getSelectionModel().clearSelection();
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
        if(listViewVenda.getSelectionModel().getSelectedItem() != null){
            Venda venda = listViewVenda.getSelectionModel().getSelectedItem();
            
            try{
                connection.setAutoCommit(false);
                Cliente cliente = venda.getClienteVenda();
                
                clienteDAO.alterar(cliente);
                vendaDAO.remover(venda);
                connection.commit();
                
                listViewVenda.getSelectionModel().clearSelection();
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
        comboBoxFuncionario.getSelectionModel().clearSelection();
        datePickerData.getEditor().clear();
        datePickerData.setValue(null);
        textFieldValor.clear();
    }
    
    
   
    


    
    
}
