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
import javafx.scene.control.Alert;
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
    private JFXTextField textFieldVeiculoCadastroManutencao;

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
    
    void setServico(Servico servico){
        this.servico = servico;
        this.textFieldTipoServicoCadastroManutencao.setText(servico.getTipoServico());
        this.textFieldProprietarioCadastroManutencao.setText(servico.getProprietario());
        this.textFieldMaterialUtilizadoCadastroManutencao.setText(servico.getMaterial());
        this.textFieldPecasUtilizadasCadastroManutencao.setText(servico.getPecasUtilizadas());
        this.textFieldVeiculoCadastroManutencao.setText(servico.getVeiculo());
        this.datePickerData.setValue(servico.getData());
        this.textFieldCustosCadastroManutencao.setText(servico.getCustos());  
        
    }
        
    @FXML
    public void handleButtonConfirmar() {
       if (validarEntradaDeDados()) {
            servico.setTipoServico(textFieldTipoServicoCadastroManutencao.getText());
            servico.setData(datePickerData.getValue());
            servico.setMaterial(textFieldMaterialUtilizadoCadastroManutencao.getText());
            servico.setPecasUtilizadas(textFieldPecasUtilizadasCadastroManutencao.getText());
            servico.setVeiculo(textFieldVeiculoCadastroManutencao.getText());
            servico.setCustos(textFieldCustosCadastroManutencao.getText());
            servico.setProprietario(textFieldProprietarioCadastroManutencao.getText());

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

        if (textFieldTipoServicoCadastroManutencao.getText() == null || textFieldTipoServicoCadastroManutencao.getText().length() == 0) {
            errorMessage += "Tipo de servico inválido!\n";
        }
        if (datePickerData.getValue() != null) {
        } else {
           errorMessage += "Data Invalida!\n";
        }
        
        if (textFieldMaterialUtilizadoCadastroManutencao.getText() == null || textFieldMaterialUtilizadoCadastroManutencao.getText().length() == 0) {
            errorMessage += "Material inválido!\n";
        }
        if (textFieldPecasUtilizadasCadastroManutencao.getText() == null || textFieldPecasUtilizadasCadastroManutencao.getText().length() == 0) {
            errorMessage += "Peça invalida inválido!\n";
        } 
        
        if (textFieldVeiculoCadastroManutencao.getText() == null || textFieldVeiculoCadastroManutencao.getText().length() == 0) {
            errorMessage += "Veiculo invalida inválido!\n";
        }   
        
        if (textFieldCustosCadastroManutencao.getText() == null || textFieldCustosCadastroManutencao.getText().length() == 0) {
            errorMessage += "Valor invalida inválido!\n";
        }
        
        if (textFieldProprietarioCadastroManutencao.getText() == null || textFieldProprietarioCadastroManutencao.getText().length() == 0) {
            errorMessage += "Proprietário invalida inválido!\n";
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
    
    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }
}    
    
