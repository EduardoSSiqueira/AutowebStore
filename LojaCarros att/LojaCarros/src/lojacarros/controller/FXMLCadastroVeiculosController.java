/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lojacarros.model.Veiculo;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLCadastroVeiculosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private TableView<Veiculo> tableViewCadastroVeiculos;

    @FXML
    private TableColumn<Veiculo, String> tableColumnCadastroVeiculoModelo;

    @FXML
    private TableColumn<Veiculo, Integer> tableColumnCadastroVeiculoAno;

    @FXML
    private Label labelModeloVeiculo;

    @FXML
    private Label labelAnoVeiculo;

    @FXML
    private Label labelPrecoVeiculo;

    @FXML
    private JFXButton buttonAlterar;

    @FXML
    private JFXButton buttonInserir;

    @FXML
    private JFXButton buttonRemover;
    
    @FXML
    private AnchorPane anchorPane;
    
    
   
       
         
   
    private boolean showFXMLCadastroVeiculoInserir(Veiculo veiculo ) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastroVeiculoInserirController.class.getResource("/lojacarros/view/FXMLCadastroVeiculoInserir.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro do Veículo");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLCadastroVeiculoInserirController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setVeiculo(veiculo);
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
        
}
    
    @FXML
    public void handleCadastrosVeiculoInserir() throws IOException {
        
        Veiculo veiculo = new Veiculo();
        showFXMLCadastroVeiculoInserir(veiculo);
        
    }
}
     
