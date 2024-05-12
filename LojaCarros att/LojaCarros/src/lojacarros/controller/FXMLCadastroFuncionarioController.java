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
import lojacarros.model.Funcionario;
import lojacarros.model.dao.FuncionarioDAO;
import lojacarros.model.database.Database;
import lojacarros.model.database.DatabaseFactory;


/**
 * FXML Controller class
 *
 * @author 20201si029
 */


public class FXMLCadastroFuncionarioController implements Initializable {


      
      @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<Funcionario> tableViewCadastroFuncionarios;

    @FXML
    private TableColumn<Funcionario, String> tableColumnCadastroFuncionarioNome;

    @FXML
    private TableColumn<Funcionario, String> tableColumnCadastroFuncionarioCPF;

    @FXML
    private Label labelNomeFuncionario;

    @FXML
    private Label labelDataNascFuncionario;

    @FXML
    private Label labelCPFFuncionario;

    @FXML
    private Label labelCodigoFuncionario;

    @FXML
    private JFXButton buttonAlterar;

    @FXML
    private JFXButton buttonInserir;

    @FXML
    private JFXButton buttonRemover;

    private List<Funcionario> listFuncionario;
    private ObservableList<Funcionario> observableListFuncionario;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO(); 
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
         funcionarioDAO.setConnection(connection);
        carregarTableViewFuncionario();
        
        tableViewCadastroFuncionarios.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewFuncionario(newValue));
    } 
    
    
      public void carregarTableViewFuncionario() {
        tableColumnCadastroFuncionarioNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnCadastroFuncionarioCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        listFuncionario = funcionarioDAO.listar();

        observableListFuncionario = FXCollections.observableArrayList(listFuncionario);
        tableViewCadastroFuncionarios.setItems(observableListFuncionario);
        
    } 
      
    public void selecionarItemTableViewFuncionario(Funcionario funcionario){
        if(funcionario != null){
        labelCodigoFuncionario.setText(String.valueOf(funcionario.getCodFunc()));
        labelNomeFuncionario.setText(funcionario.getNome());
        labelDataNascFuncionario.setText(String.valueOf(funcionario.getDataNasc()));
        labelCPFFuncionario.setText(funcionario.getCpf());
        } else {
            
        labelCodigoFuncionario.setText("");
        labelNomeFuncionario.setText("");
        labelDataNascFuncionario.setText("");
        labelCPFFuncionario.setText("");
            
        }
    }
    
    private boolean showFXMLCadastroFuncionarioInserir(Funcionario funcionario ) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastroFuncionarioInserirController.class.getResource("/lojacarros/view/FXMLCadastroFuncionarioInserir.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro do Funcionário");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLCadastroFuncionarioInserirController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFuncionario(funcionario);
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
        
}
    
    @FXML
    public void handleCadastrosFuncionarioInserir() throws IOException {
        
        Funcionario funcionario = new Funcionario();
        boolean buttonConfirmarClicked = showFXMLCadastroFuncionarioInserir(funcionario);
        if (buttonConfirmarClicked) {
            funcionarioDAO.inserir(funcionario);
            carregarTableViewFuncionario();
        }
    }
    
     @FXML
    void handleButtonAlterar(ActionEvent event) throws IOException {
        Funcionario funcionario = tableViewCadastroFuncionarios.getSelectionModel().getSelectedItem();//Obtendo funcionario selecionado
        if (funcionario != null) {
            boolean buttonConfirmarClicked = showFXMLCadastroFuncionarioInserir(funcionario);
            if (buttonConfirmarClicked) {
                funcionarioDAO.alterar(funcionario);
                carregarTableViewFuncionario();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Funcionário na Tabela!");
            alert.show();
        }        

    }
    
        @FXML
    void handleButtonRemover(ActionEvent event) {
     
        Funcionario funcionario = tableViewCadastroFuncionarios.getSelectionModel().getSelectedItem();
        if (funcionario != null) {
            funcionarioDAO.remover(funcionario);
            carregarTableViewFuncionario();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um funcionário na Tabela!");
            alert.show();
        }        
    }
    
   
    
    
    
}

