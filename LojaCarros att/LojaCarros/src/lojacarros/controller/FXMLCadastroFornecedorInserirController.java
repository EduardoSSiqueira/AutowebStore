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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lojacarros.model.Fornecedor;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLCadastroFornecedorInserirController implements Initializable {

    @FXML
    private JFXTextField textFieldEmpresaCadastroFornecedor;

    @FXML
    private JFXTextField textFieldCnpjCadastroFornecedor;

    @FXML
    private JFXTextField textFieldLocalCadastroFornecedor;

    @FXML
    private JFXTextField textFieldCarrosCadastroFornecedor;

    @FXML
    private JFXButton buttonConfirmar;

    @FXML
    private JFXButton buttonCancelar;
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false; 
    
    private Fornecedor fornecedor;
    
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
    
    public Fornecedor getFornecedor() {
        return this.fornecedor;
    }
    
    public void setFornecedor(Fornecedor fornecedor){
        this.fornecedor = fornecedor;
        this.textFieldEmpresaCadastroFornecedor.setText(fornecedor.getEmpresa());
        this.textFieldCnpjCadastroFornecedor.setText(fornecedor.getCnpj());
        this.textFieldLocalCadastroFornecedor.setText(fornecedor.getLocal());
        this.textFieldCarrosCadastroFornecedor.setText(fornecedor.getCarros());
        
        
    }
    
    
    @FXML
    void handleButtonCancelar(ActionEvent event) {
        getDialogStage().close();
    }

    @FXML
    void handleButtonConfirmar(ActionEvent event) {
//      if (validarEntradaDeDados()) {
            fornecedor.setEmpresa(textFieldEmpresaCadastroFornecedor.getText());
            fornecedor.setCnpj(textFieldCnpjCadastroFornecedor.getText());
            fornecedor.setLocal(textFieldLocalCadastroFornecedor.getText());
            fornecedor.setCarros(textFieldCarrosCadastroFornecedor.getText());


            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    } 
    
    /*
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (textFieldEmpresaCadastroFornecedor.getText() == null || textFieldEmpresaCadastroFornecedor.getText().length() == 0) {
            errorMessage += "Empresa inválido!\n";
        }
        if (textFieldCnpjCadastroFornecedor.getText() == null || textFieldCnpjCadastroFornecedor.getText().length() == 0) {
            errorMessage += "CNPJ inválido!\n";
        }
        if (textFieldLocalCadastroFornecedor.getText() == null || textFieldLocalCadastroFornecedor.getText().length() == 0) {
            errorMessage += "Local inválido!\n";
        }
        if (textFieldCarrosCadastroFornecedor.getText() == null || textFieldCarrosCadastroFornecedor.getText().length() == 0) {
            errorMessage += "Carro Invalido!\n";
        } else {
           errorMessage += "Erro!\n";
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
    };*/
    
//}
