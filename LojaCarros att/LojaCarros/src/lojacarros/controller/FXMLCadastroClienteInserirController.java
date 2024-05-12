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
import lojacarros.model.Cliente;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */

public class FXMLCadastroClienteInserirController implements Initializable {

    @FXML
    private JFXTextField textFieldNomeCadastroCliente;

    @FXML
    private JFXTextField textFieldCPFCadastroCliente;

    @FXML
    private JFXDatePicker datePickerCadastroCliente;

    @FXML
    private JFXTextField textFieldCidadeCliente;

    @FXML
    private JFXButton buttonConfirmar;

    @FXML
    private JFXButton buttonCancelar;
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false; 
    
    private Cliente cliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }
    
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
        this.textFieldNomeCadastroCliente.setText(cliente.getNome());
        this.textFieldCPFCadastroCliente.setText(cliente.getCpf());
        this.textFieldCidadeCliente.setText(cliente.getCidade());
        this.datePickerCadastroCliente.setValue(cliente.getDatadenascimento());
        
        
    }
    
    
    @FXML
    public void handleButtonConfirmar() {
        if (validarEntradaDeDados()) {
            cliente.setNome(textFieldNomeCadastroCliente.getText());
            cliente.setCpf(textFieldCPFCadastroCliente.getText());
            cliente.setDatadenascimento(datePickerCadastroCliente.getValue());
            cliente.setCidade(textFieldCidadeCliente.getText());

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

        if (textFieldNomeCadastroCliente.getText() == null || textFieldNomeCadastroCliente.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
        }
        if (textFieldCPFCadastroCliente.getText() == null || textFieldCPFCadastroCliente.getText().length() == 0) {
            errorMessage += "CPF inválido!\n";
        }
        if (textFieldCidadeCliente.getText() == null || textFieldCidadeCliente.getText().length() == 0) {
            errorMessage += "Cidade inválido!\n";
        }
        if (datePickerCadastroCliente.getValue() != null) {
        } else {
           errorMessage += "Data Invalida!\n";
        }
    
        // Implementar a validação do Date Picker
        

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
