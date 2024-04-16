/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lojacarros.model.Funcionario;


/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLCadastroFuncionarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private TableView<?> tableViewCadastroVeiculos;

    @FXML
    private TableColumn<?, ?> tableColumnCadastroFuncionarioNome;

    @FXML
    private TableColumn<?, ?> tableColumnCadastroFuncionarioCPF;

    @FXML
    private Label labelNomeFuncionario;

    @FXML
    private Label labelDataNascFuncionario;

    @FXML
    private Label labelCPFFuncionario;

    @FXML
    private Label tableColumnCadastroFuncionarioUsuario;

    @FXML
    private JFXButton buttonAlterar;

    
    @FXML
    private JFXButton buttonInserir;

    @FXML
    private JFXButton buttonRemover;
    
    @FXML
    private AnchorPane anchorPane;
    
    
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
        showFXMLCadastroFuncionarioInserir(funcionario);
        
    }
    
    
}
