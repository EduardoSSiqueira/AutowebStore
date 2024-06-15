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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import lojacarros.Main;
import lojacarros.model.database.DatabasePostgreSQL;




/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLTelaLoginController implements Initializable {

    private Connection connection;

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
        
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/FXMLCadastroLogin.fxml"));
        
        Stage stageTelaCadastro = new Stage();
        stageTelaCadastro.setTitle("Tela Cadastro Sistema");

        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stageTelaCadastro.setScene(scene);
        stageTelaCadastro.show();
    } 
    
    
    @FXML
    void handleLogin(ActionEvent event) throws IOException {
            String nome = textFieldUsuario.getText();
            String Senha = textFieldSenha.getText();
            if(consultarUsuario(nome, Senha)){
                showFXMLTelaInicial();
                buttonLogin.getScene().getWindow().hide();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Ocorreu um erro no login");
            }


    }
    
    private void showFXMLTelaInicial() throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lojacarros/view/FXMLTelaInicial.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stageTelaInicial = new Stage();     
        stageTelaInicial.setScene(scene);
        stageTelaInicial.show();

    } 
    
    public boolean consultarUsuario(String nomeUsuario, String senha) {
    try {
        // Conexão com o banco de dados
        DatabasePostgreSQL conn = new DatabasePostgreSQL();
        connection = conn.conectar();
        
        // Preparação da consulta
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Usuario WHERE nomeUsuario=? AND senha=?");
        ps.setString(1, nomeUsuario);
        ps.setString(2, senha);

        // Execução da consulta e verificação do resultado
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }

    } catch (SQLException e) {
        System.out.println("Erro ao consultar o usuário: " + e.getMessage());
        return false;
    }
    
} 

}
