/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import lojacarros.model.Servico;




public class FXMLCadastroManutencaoInserirController implements Initializable {

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false; 
    private Servico servico;
    
    
  @FXML
    private JFXTextField textFieldTipoServicoCadastroManutencao;

    @FXML
    private JFXTextField textFieldProprietarioCadastroManutencao;

    @FXML
    private JFXTextField textFieldMaterialUtilizadoCadastroManutencao;

    @FXML
    private JFXTextField textFieldPecasUtilizadasCadastroManutencao;

    @FXML
    private JFXComboBox<?> comboBoxVeiculoCadastroManutencao;

    @FXML
    private JFXDatePicker datePickerData;

    @FXML
    private JFXTextField textFieldCustosCadastroManutencao;

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
    
    public Servico getServico() {
        return this.servico;
    }
    
    void setVeiculo(Servico servico) {
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }
}    
    
