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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class FXMLCadastroPecasReposicaoInserirController implements Initializable {

    
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
    
    @FXML
    private AnchorPane anchorPaneCadastroPecasReposicaoInserir;

    
    private Stage dialogStage;
    
    
    @FXML
    private GridPane gridPaneCadastroPecas;

    @FXML
    private JFXTextField textFieldNomeCadastroPeca;

    @FXML
    private JFXTextField textFieldCPFCadastroCliente;

    @FXML
    private JFXTextField textFieldFabricanteCadastroPeca;

    @FXML
    private JFXTextField textFieldNumeroPecaCadastroPeca;

    @FXML
    private JFXTextField textFieldPrecoCadastroPeca;

    @FXML
    private JFXTextField textFieldQtdEstoqueCadastroPeca;

    @FXML
    private JFXButton buttonConfirmar;

    @FXML
    private JFXButton buttonCancelar;

    @FXML
    void handleButtonCancelar(ActionEvent event) {

    }
    

    @FXML
    void handleButtonConfirmar(ActionEvent event) {

    }

    boolean isButtonConfirmarClicked() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
