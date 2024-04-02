/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Parent;




/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLTelaLoginController implements Initializable {

   @FXML
    private JFXTextField textFieldUsuario;

    @FXML
    private JFXPasswordField textFieldSenha;

    @FXML
    private JFXButton buttonLogin;

    @FXML
    private JFXButton buttonCadastrarUsuario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    void handleCadastroLogin(ActionEvent event) throws IOException {
    } 
    
    
    @FXML
    void handleLogin(ActionEvent event) throws IOException {
        showFXMLTelaInicial();
        buttonLogin.getScene().getWindow().hide();


    }
    
    private void showFXMLTelaInicial() throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lojacarros/view/FXMLTelaInicial.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stageTelaInicial = new Stage();     
        stageTelaInicial.setScene(scene);
        stageTelaInicial.show();

    }
    
}
