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
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLTelaInicialController implements Initializable {

    @FXML
    private MenuItem menuItemCadastroVeiculos;

    @FXML
    private MenuItem menuItemCadastroFuncionarios;

    @FXML
    private MenuItem menuItemCadastroCliente;

    @FXML
    private MenuItem menuItemCadastroFornecedor;

    @FXML
    private MenuItem menuItemCadastroPecaRepo;

    @FXML
    private MenuItem menuItemCadastroManutencao;

    @FXML
    private MenuItem menuItemProcessoVenda;

    @FXML
    private MenuItem menuItemProcessoTestDrive;

    @FXML
    private MenuItem menuItemProcessoConsorcio;

    @FXML
    private MenuItem menuItemRelatorioTestDrive;

    @FXML
    private MenuItem menuItemRelatorioVenda;

    @FXML
    private MenuItem menuItemRelatorioConsorcio;
    
    @FXML
    private AnchorPane anchorPane;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }     
    
    @FXML
    void handleMenuItemCadastroCliente(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/lojacarros/view/FXMLCadastroCliente.fxml"));
        anchorPane.getChildren().setAll(a);

    }

    @FXML
    void handleMenuItemCadastroFornecedor(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/lojacarros/view/FXMLCadastroFornecedor.fxml"));
        anchorPane.getChildren().setAll(a);

    }

    @FXML
    void handleMenuItemCadastroFuncionarios(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/lojacarros/view/FXMLCadastroFuncionario.fxml"));
        anchorPane.getChildren().setAll(a);


    }

    @FXML
    void handleMenuItemCadastroManutencao(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/lojacarros/view/FXMLCadastroManutencao.fxml"));
        anchorPane.getChildren().setAll(a);


    }

    @FXML
    void handleMenuItemCadastroPecaRepo(ActionEvent event) throws IOException {        
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/lojacarros/view/FXMLCadastroPecasReposicao.fxml"));
        anchorPane.getChildren().setAll(a);


    }

    @FXML
    void handleMenuItemCadastroVeiculos(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/lojacarros/view/FXMLCadastroVeiculos.fxml"));
        anchorPane.getChildren().setAll(a);

    }

    @FXML
    void handleMenuItemProcessoConsorcio(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/lojacarros/view/FXMLProcessoConsorcio.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    void handleMenuItemProcessoTestDrive(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/lojacarros/view/FXMLProcessoTestDrive.fxml"));
        anchorPane.getChildren().setAll(a);

    }

    @FXML
    void handleMenuItemProcessoVenda(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/lojacarros/view/FXMLProcessoVenda.fxml"));
        anchorPane.getChildren().setAll(a);

    }

    @FXML
    void handleMenuItemRelatorioConsorcio(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/lojacarros/view/FXMLRelatorioConsorcio.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    void handleMenuItemRelatorioTestDrive(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/lojacarros/view/FXMLRelatorioTestDrive.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    void handleMenuItemRelatorioVenda(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/lojacarros/view/FXMLRelatorioVenda.fxml"));
        anchorPane.getChildren().setAll(a);        

    }


    
}
