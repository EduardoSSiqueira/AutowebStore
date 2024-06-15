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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lojacarros.model.Peca;


public class FXMLCadastroPecasReposicaoInserirController implements Initializable {

    
    @FXML
    private AnchorPane anchorPaneCadastroPecasReposicaoInserir;

    @FXML
    private GridPane gridPaneCadastroPecas;

    @FXML
    private JFXTextField textFieldNomeCadastroPeca;

     @FXML
    private JFXTextField textFieldDescricaoCadastroPeca;

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
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false; 
    private Peca peca;

        
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
    
    public Peca getFornecedor() {
        return this.peca;
    }
    
    @FXML
    void handleButtonCancelar(ActionEvent event) {
        getDialogStage().close();
    }

    
    public Peca getPeca() {
        return this.peca;
    }
    
   void setPeca(Peca peca) {
        this.peca = peca;
        this.textFieldNomeCadastroPeca.setText(peca.getNomePeca());
        this.textFieldDescricaoCadastroPeca.setText(peca.getDescPeca());
        this.textFieldFabricanteCadastroPeca.setText((peca.getFabricante()));
        this.textFieldNumeroPecaCadastroPeca.setText((peca.getNumPeca()));
        this.textFieldPrecoCadastroPeca.setText(String.valueOf(peca.getPreco()));
        this.textFieldQtdEstoqueCadastroPeca.setText(String.valueOf(peca.getQtdEstoque()));
    }
    
    @FXML
    public void handleButtonConfirmar() {
       if (validarEntradaDeDados()) {
            peca.setNomePeca(textFieldNomeCadastroPeca.getText());
            peca.setDescPeca(textFieldDescricaoCadastroPeca.getText());
            peca.setFabricante(textFieldFabricanteCadastroPeca.getText());
            peca.setNumPeca(textFieldNumeroPecaCadastroPeca.getText());
             try {
            double preco = Double.parseDouble(textFieldPrecoCadastroPeca.getText());
            peca.setPreco((float) preco);
            } catch (NumberFormatException e) {
            
            System.err.println("Erro ao converter o preço para um número.");
            return; 
            }

             try {
                int qtdEstoque = Integer.parseInt(textFieldQtdEstoqueCadastroPeca.getText());
                peca.setQtdEstoque(qtdEstoque);
               } catch (NumberFormatException e) {
                System.err.println("Erro ao converter a quantidade em estoque para um número inteiro.");
                return; 
            }
            buttonConfirmarClicked = true;
            dialogStage.close();
       }
    }
    
      

    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (textFieldNomeCadastroPeca.getText() == null || textFieldNomeCadastroPeca.getText().length() == 0) {
           errorMessage += "Nome inválido!\n";
        }
        if (textFieldDescricaoCadastroPeca.getText() == null || textFieldDescricaoCadastroPeca.getText().length() == 0) {
            errorMessage += "Descrição inválida!\n";
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
