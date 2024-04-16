/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import lojacarros.model.Funcionario;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class FXMLCadastroFuncionarioInserirController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false; 
    private Funcionario funcionario;
    
      @FXML
    private JFXTextField textFieldNomeCadastroFuncionario;

    @FXML
    private JFXTextField textFieldDataNascCadastroFuncionario;

    @FXML
    private JFXTextField textFieldCPFCadastroFuncionario;

    @FXML
    private JFXTextField textFieldUsuarioCadastroFuncionario;

    @FXML
    private JFXTextField textFieldSenhaCadastroFuncionario;

    @FXML
    private JFXButton buttonConfirmar;

    @FXML
    private JFXButton buttonCancelar;
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }
    
    public Funcionario getFuncionario() {
        return this.funcionario;
    }
    
    void setFuncionario(Funcionario funcionario) {

    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }
    
}
