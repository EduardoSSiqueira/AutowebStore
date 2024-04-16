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
    
    void setVeiculo(Veiculo veiculo) {

    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    void setServico(Servico servico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
