/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lojacarros.model.Usuario;
import lojacarros.model.dao.UsuarioDAO;
import lojacarros.model.database.Database;
import lojacarros.model.database.DatabaseFactory;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLCadastroLoginController implements Initializable {

    @FXML
    private JFXButton buttonCadastroLogin;
       
    @FXML
    private  JFXTextField textFieldEmail;  
    
    @FXML
    private  JFXTextField textFieldNomeCompleto; 
    
    @FXML
    private  JFXTextField textFieldNomeUsuario; 
    
    @FXML
    private  JFXPasswordField textFieldSenha; 

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private void handleCadastrar(ActionEvent event) throws IOException {    
        
            Database database = DatabaseFactory.getDatabase("postgresql");
            Connection connection = database.conectar();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setConnection(connection);
            
            Usuario usuario = new Usuario();
            usuario.setEmail(textFieldEmail.getText());
            usuario.setNomeUsuario(textFieldNomeUsuario.getText());
            usuario.setNomeCompleto(textFieldNomeCompleto.getText());
            usuario.setSenha(textFieldSenha.getText());
            
            usuarioDAO.inserir(usuario);
            
            buttonCadastroLogin.getScene().getWindow().hide();
        
    } 
}

