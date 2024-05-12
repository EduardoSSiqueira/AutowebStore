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
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lojacarros.model.Servico;
import lojacarros.model.Veiculo;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class FXMLCadastroVeiculoInserirController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false; 
    private Veiculo veiculo;
    
    @FXML
    private JFXTextField textFieldModeloCadastroVeiculo;

    @FXML
    private JFXTextField textFieldAnoCadastroVeiculo;

    @FXML
    private JFXTextField textFieldPreçoCadastroVeiculo;
    

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
    
    public Veiculo getVeiculo() {
        return this.veiculo;
    }
    
    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }


    
    void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        this.textFieldModeloCadastroVeiculo.setText(veiculo.getModelo());
        this.textFieldAnoCadastroVeiculo.setText(veiculo.getAno());
        this.textFieldPreçoCadastroVeiculo.setText(String.valueOf(veiculo.getPreco()));
    }
    
    @FXML
    public void handleButtonConfirmar() {
        if (validarEntradaDeDados()) {
        veiculo.setModelo(textFieldModeloCadastroVeiculo.getText());
        veiculo.setAno(textFieldAnoCadastroVeiculo.getText());
        veiculo.setPreco(textFieldPreçoCadastroVeiculo.getText());
        }
    }

     @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    } 
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (textFieldModeloCadastroVeiculo.getText() == null || textFieldModeloCadastroVeiculo.getText().length() == 0) {
            errorMessage += "Modelo inválido!\n";
        }
        if (textFieldAnoCadastroVeiculo.getText() == null || textFieldAnoCadastroVeiculo.getText().length() == 0) {
            errorMessage += "Ano inválido!\n";
        }
        if (textFieldPreçoCadastroVeiculo.getText() == null || textFieldPreçoCadastroVeiculo.getText().length() == 0) {
            errorMessage += "Preço inválido!\n";
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
