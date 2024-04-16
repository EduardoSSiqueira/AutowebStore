/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

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
    
}
