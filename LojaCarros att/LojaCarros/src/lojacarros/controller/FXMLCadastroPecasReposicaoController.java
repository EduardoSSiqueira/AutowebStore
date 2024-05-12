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
import lojacarros.model.Peca;
import lojacarros.model.Veiculo;
import lojacarros.model.dao.PecasDAO;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLCadastroPecasReposicaoController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<?> tableViewCadastroPecas;

    @FXML
    private TableColumn<?, ?> tableColumnCadastroPecasNome;

    @FXML
    private TableColumn<?, ?> tableColumnCadastroPecasDescricao;

    @FXML
    private Label labelNumeroPeca;

    @FXML
    private Label labelFabricante;

    @FXML
    private Label labelPrecoDePeca;

    @FXML
    private Label labelQtdEstoque;

    @FXML
    private JFXButton buttonAlterar;

    @FXML
    private JFXButton buttonInserir;

    @FXML
    private JFXButton buttonRemover;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
       
    private boolean showFXMLCadastroPecasReposicaoInserir(Peca peca) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastroPecasReposicaoInserirController.class.getResource("/lojacarros/view/CadastroPecasReposicaoInserir.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Peça");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLCadastroPecasReposicaoInserirController controller = loader.getController();
        controller.setDialogStage(dialogStage);
       // controller.setPeca(Peca);
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
        
}
    

     @FXML
    void handleCadastrosPecasReposicaoInserir(ActionEvent event) throws IOException {
        Peca peca = new Peca();
        boolean buttonConfirmarClicked = showFXMLCadastroPecasReposicaoInserir(peca);
        if (buttonConfirmarClicked) {
           // PecasDAO.inserir(peca);
          // carregarTableViewVeiculo();
        }

    }
}
