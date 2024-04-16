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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lojacarros.model.Servico;
import lojacarros.model.Veiculo;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLCadastroManutencaoController implements Initializable {
    
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

     @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<?> tableViewCadastroManutencao;

    @FXML
    private TableColumn<?, ?> tableColumnCadastroManutencaoVeiculo;

    @FXML
    private TableColumn<?, ?> tableColumnCadastroManutencaoTipoServico;

    @FXML
    private Label labelTipoServico;

    @FXML
    private Label labelVeiculo;

    @FXML
    private Label labelProprietarioVeiculo;

    @FXML
    private Label labelMaterialUtilizado;

    @FXML
    private Label labelPecasUtilizadas;

    @FXML
    private Label labelData;

    @FXML
    private Label labelCustos;

    @FXML
    private JFXButton buttonAlterar;

    @FXML
    private JFXButton buttonInserir;

    @FXML
    private JFXButton buttonRemover;
    
    
    
     private boolean showFXMLCadastroManutencaoInserir(Servico servico ) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastroManutencaoInserirController.class.getResource("/lojacarros/view/FXMLCadastroManutencaoInserir.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro Manutenção");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLCadastroVeiculoInserirController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setServico(servico);
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
        
}
    
   
    @FXML
    public void handleCadastrosManutencaoInserir() throws IOException {
        
        Servico servico = new Servico();
        showFXMLCadastroManutencaoInserir(servico);
        
    }
}
