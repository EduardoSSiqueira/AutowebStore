/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.controller;

import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLProcessoConsorcioController implements Initializable {    
    
    @FXML
    private JFXTabPane tabPaneConsorcio;;

    @FXML
    private Tab tabInserir;

    @FXML
    private Tab tabAlterarRemover;

    @FXML
    private Tab tabListar;

    @FXML
    private AnchorPane anchorPane;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                   try {
         handleProcessoConsorcioInserir();
        } catch (IOException ex) {
            Logger.getLogger(FXMLProcessoTestDriveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            tabPaneConsorcio.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() { 
            @Override 
            public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
                if(newTab.equals(tabInserir)) {            
                    try {
                        handleProcessoConsorcioInserir();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLTelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(newTab.equals(tabAlterarRemover)){
                    try {
                        handleProcessoConsorcioAlterarRemover();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLProcessoConsorcioController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(newTab.equals(tabListar)){
                    try {
                        handleProcessoConsorcioListar();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLProcessoConsorcioController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }      
    
    
    public void handleProcessoConsorcioInserir() throws IOException {
      AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/lojacarros/view/FXMLProcessoConsorcioInserir.fxml"));
      anchorPane.getChildren().setAll(a);
    }
    
    public void handleProcessoConsorcioAlterarRemover() throws IOException {
      AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/lojacarros/view/FXMLProcessoConsorcioAlterarRemover.fxml"));
      anchorPane.getChildren().setAll(a);
    }
    
    public void handleProcessoConsorcioListar() throws IOException {
      AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/lojacarros/view/FXMLProcessoConsorcioListar.fxml"));
      anchorPane.getChildren().setAll(a);
    }
    
}
