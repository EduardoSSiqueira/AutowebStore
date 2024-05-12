/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lojacarros.model.Fornecedor;
import lojacarros.model.dao.FornecedorDAO;
import lojacarros.model.database.Database;
import lojacarros.model.database.DatabaseFactory;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLCadastroFornecedorController implements Initializable {
    
    @FXML
    private TableView<Fornecedor> tableViewFornecedor;

    @FXML
    private TableColumn<Fornecedor, String> tableColumnFornecedorEmpresa;

    @FXML
    private TableColumn<Fornecedor, String> tableColumnFornecedorCNPJ;

    @FXML
    private JFXButton buttonRemover;

    @FXML
    private JFXButton buttonAlterar;

    @FXML
    private JFXButton buttonInserir;

    @FXML
    private Label labelFornecedorCodigo;

    @FXML
    private Label labelFornecedorCNPJ;

    @FXML
    private Label labelFornecedorCarros;

    @FXML
    private Label labelFornecedorLocal;

    @FXML
    private Label labelFornecedorEmpresa;
    
    private List<Fornecedor> listFornecedor;
    private ObservableList<Fornecedor> observableListFornecedor;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final FornecedorDAO fornedorDAO = new FornecedorDAO(); 

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fornedorDAO.setConnection(connection);
        carregarTableViewFornecedor();
        
        tableViewFornecedor.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewFornecedor(newValue));
    }    
    
    
    public void carregarTableViewFornecedor() {
        tableColumnFornecedorEmpresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        tableColumnFornecedorCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));

        listFornecedor = fornedorDAO.listar();

        observableListFornecedor = FXCollections.observableArrayList(listFornecedor);
        tableViewFornecedor.setItems(observableListFornecedor);
        
    }  
    
    
    public void selecionarItemTableViewFornecedor(Fornecedor fornecedor){
        if(fornecedor != null){
        labelFornecedorCodigo.setText(String.valueOf(fornecedor.getCdFornecedor()));
        labelFornecedorCNPJ.setText(fornecedor.getCnpj());
        labelFornecedorCarros.setText(fornecedor.getCarros());
        labelFornecedorLocal.setText(fornecedor.getLocal());
        labelFornecedorEmpresa.setText(fornecedor.getEmpresa());
        } else {
            
        labelFornecedorCodigo.setText("");
        labelFornecedorCNPJ.setText("");
        labelFornecedorCarros.setText("");
        labelFornecedorLocal.setText("");
        labelFornecedorEmpresa.setText("");
            
        }
    }
    
    
    @FXML
    void handleButtonAlterar(ActionEvent event) throws IOException {
        Fornecedor fornecedor = tableViewFornecedor.getSelectionModel().getSelectedItem();//Obtendo cliente selecionado
        if (fornecedor != null) {
            boolean buttonConfirmarClicked = showFXMLCadastroFornecedorInserir(fornecedor);
            if (buttonConfirmarClicked) {
                fornedorDAO.alterar(fornecedor);
                carregarTableViewFornecedor();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um arbitro na Tabela!");
            alert.show();
        }        

    }

    @FXML
    void handleButtonRemover(ActionEvent event) {
        
        Fornecedor fornecedor = tableViewFornecedor.getSelectionModel().getSelectedItem();
        if (fornecedor != null) {
            fornedorDAO.remover(fornecedor);
            carregarTableViewFornecedor();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um fornecedor na Tabela!");
            alert.show();
        }   

    }

    @FXML
    void handleCadastrosFornecedorInserir(ActionEvent event) throws IOException {
      Fornecedor fornecedor = new Fornecedor();
        boolean buttonConfirmarClicked = showFXMLCadastroFornecedorInserir(fornecedor);
        if (buttonConfirmarClicked) {
            fornedorDAO.inserir(fornecedor);
            carregarTableViewFornecedor();
        }

    }
    
     
    private boolean showFXMLCadastroFornecedorInserir( Fornecedor fornecedor) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastroFornecedorInserirController.class.getResource("/lojacarros/view/FXMLCadastroFornecedorInserir.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro do Fornecedor");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLCadastroFornecedorInserirController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFornecedor(fornecedor);
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
        
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cadastrojogo/view/FXMLCadastroArbitroInserir.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stageArbitroInserir = new Stage();
        stageArbitroInserir.setScene(scene);
        stageArbitroInserir.setWidth(630); 
        stageArbitroInserir.setHeight(450); 
        stageArbitroInserir.show();*/
    }
}
