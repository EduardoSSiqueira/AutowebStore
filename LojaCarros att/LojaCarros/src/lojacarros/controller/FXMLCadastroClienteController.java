/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lojacarros.model.Cliente;
import lojacarros.model.dao.ClienteDAO;
import lojacarros.model.database.Database;
import lojacarros.model.database.DatabaseFactory;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLCadastroClienteController implements Initializable {

   
    @FXML
    private JFXButton buttonRemover;

    @FXML
    private JFXButton buttonAlterar;

    @FXML
    private JFXButton buttonInserir;

    @FXML
    private Label labelClienteCodigo;

    @FXML
    private Label labelClienteCPF;

    @FXML
    private Label labelClienteDataNascimento;

    @FXML
    private Label labelClienteCidade;

    @FXML
    private Label labelClienteNome;
    
    @FXML
    private TableColumn<Cliente, String> tableColumnClienteCPF;

    @FXML
    private TableColumn<Cliente, String> tableColumnClienteNome;
    
    @FXML
    private TableView<Cliente> tableViewCliente;
    
    private List<Cliente> listCliente;
    private ObservableList<Cliente> observableListCliente;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO(); 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        carregarTableViewCliente();
        
        tableViewCliente.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewCliente(newValue));
    }    
    
    
    public void carregarTableViewCliente() {
        tableColumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnClienteCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        listCliente = clienteDAO.listar();

        observableListCliente = FXCollections.observableArrayList(listCliente);
        tableViewCliente.setItems(observableListCliente);
        
    }  
    
    
    public void selecionarItemTableViewCliente(Cliente cliente){
        if(cliente != null){
        labelClienteCodigo.setText(String.valueOf(cliente.getCdCliente()));
        labelClienteNome.setText(cliente.getNome());
        labelClienteCPF.setText(cliente.getCpf());
        labelClienteCidade.setText(cliente.getCidade());
        labelClienteDataNascimento.setText(String.valueOf(cliente.getDatadenascimento()));
        } else {
            
        labelClienteCodigo.setText("");
        labelClienteNome.setText("");
        labelClienteCPF.setText("");
        labelClienteCidade.setText("");
        labelClienteDataNascimento.setText("");
            
        }
    }
    
    
    
    
    
    
    @FXML
    void handleButtonAlterar(ActionEvent event) throws IOException {
        Cliente cliente = tableViewCliente.getSelectionModel().getSelectedItem();//Obtendo cliente selecionado
        if (cliente != null) {
            boolean buttonConfirmarClicked = showFXMLCadastroClienteInserir(cliente);
            if (buttonConfirmarClicked) {
                clienteDAO.alterar(cliente);
                carregarTableViewCliente();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um arbitro na Tabela!");
            alert.show();
        }        

    }

    @FXML
    void handleButtonRemover(ActionEvent event) {
     
        Cliente cliente = tableViewCliente.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            clienteDAO.remover(cliente);
            carregarTableViewCliente();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }        
    }

    @FXML
    void handleCadastrosClienteInserir(ActionEvent event) throws IOException {
        Cliente cliente = new Cliente();
        boolean buttonConfirmarClicked = showFXMLCadastroClienteInserir(cliente);
        if (buttonConfirmarClicked) {
            clienteDAO.inserir(cliente);
            carregarTableViewCliente();
        }

    }
    
    private boolean showFXMLCadastroClienteInserir( Cliente cliente) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastroClienteInserirController.class.getResource("/lojacarros/view/FXMLCadastroClienteInserir.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro do Cliente");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLCadastroClienteInserirController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente);
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
        
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cadastrojogo/view/FXMLCadastroArbitroInserir.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stageArbitroInserir = new Stage();
        stageArbitroInserir.setScene(scene);
        stageArbitroInserir.setWidth(630); 
        stageArbitroInserir.setHeight(450); 
        stageArbitroInserir.show();*/
    }
    
}
