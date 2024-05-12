/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    private JFXDatePicker datePickerCadastroFuncionario;

    @FXML
    private JFXTextField textFieldCPFCadastroFuncionario;

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
    
 

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }
    
    void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.textFieldNomeCadastroFuncionario.setText(funcionario.getNome());
        this.datePickerCadastroFuncionario.setValue(funcionario.getDataNasc());
        this.textFieldCPFCadastroFuncionario.setText((funcionario.getCpf()));
    }
    
    @FXML
    public void handleButtonConfirmar() {
       if (validarEntradaDeDados()) {
            funcionario.setNome(textFieldNomeCadastroFuncionario.getText());
            funcionario.setDataNasc(datePickerCadastroFuncionario.getValue());
            funcionario.setCpf(textFieldCPFCadastroFuncionario.getText());
            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }
    
      

     @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    } 
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (textFieldNomeCadastroFuncionario.getText() == null || textFieldNomeCadastroFuncionario.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
        }
        if (datePickerCadastroFuncionario.getValue() != null) {
        } else {
           errorMessage += "Data Invalida!\n";
        }
        if (textFieldCPFCadastroFuncionario.getText() == null || textFieldCPFCadastroFuncionario.getText().length() == 0) {
            errorMessage += "CPF inválido!\n";
        }
    

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
    
}
