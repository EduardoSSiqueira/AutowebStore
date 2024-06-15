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
import lojacarros.model.Funcionario;
import lojacarros.model.Peca;
import lojacarros.model.dao.PecasDAO;
import lojacarros.model.database.Database;
import lojacarros.model.database.DatabaseFactory;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLCadastroPecasReposicaoController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<Peca> tableViewCadastroPecasManu;

    @FXML
    private TableColumn<Peca, String> tableColumnCadastroPecasN;
    
    @FXML
    private TableColumn<Peca, String> tableColumnCadastroPecasQtdEstoque;

    @FXML
    private Label labelNumeroPeca;
    
    @FXML
    private Label labelDescricao;

    @FXML
    private Label labelFabricante;

    @FXML
    private Label labelPrecoDePeca;

    @FXML
    private Label labelQtdEstoque;

    @FXML
    private JFXButton buttonAlterar;

    @FXML
    private JFXButton buttonInserirlabelQtdEstoque;

    @FXML
    private JFXButton buttonRemover;

    
    private List<Peca> listPeca;
    private ObservableList<Peca> observableListPeca;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final PecasDAO pecasDAO = new PecasDAO(); 
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        pecasDAO.setConnection(connection);
        carregarTableViewPecas();
        
        tableViewCadastroPecasManu.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewPecas(newValue));
        
    } 
    
    
      public void carregarTableViewPecas() {
          
          
        tableColumnCadastroPecasN.setCellValueFactory(new PropertyValueFactory<>("nomePeca"));
     
        listPeca = pecasDAO.listar();

        observableListPeca = FXCollections.observableArrayList(listPeca);
        tableViewCadastroPecasManu.setItems(observableListPeca);
        
    } 
      
    public void selecionarItemTableViewPecas(Peca peca){
        if(peca != null){
        labelNumeroPeca.setText(String.valueOf(peca.getNumPeca()));
        labelFabricante.setText(peca.getFabricante());
        labelDescricao.setText(peca.getDescPeca());
        labelPrecoDePeca.setText(String.valueOf(peca.getPreco()));
        labelQtdEstoque.setText(String.valueOf(peca.getQtdEstoque()));
        } else {
            
        labelNumeroPeca.setText("");
        labelFabricante.setText("");
        labelDescricao.setText("");
        labelPrecoDePeca.setText("");
        labelQtdEstoque.setText("");
            
        }
    }
    private boolean showFXMLCadastroPecasReposicaoInserir(Peca peca) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastroPecasReposicaoInserirController.class.getResource("/lojacarros/view/FXMLCadastroPecasReposicaoInserir.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Peça");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLCadastroPecasReposicaoInserirController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPeca(peca);
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
        
}
    

     @FXML
    void handleCadastrosPecasReposicaoInserir(ActionEvent event) throws IOException {
        Peca peca = new Peca();
        boolean buttonConfirmarClicked = showFXMLCadastroPecasReposicaoInserir(peca);
        if (buttonConfirmarClicked) {
            pecasDAO.inserir(peca);
            carregarTableViewPecas();
        }

    }
    @FXML
    void handleButtonAlterar(ActionEvent event) throws IOException {
        Peca peca = tableViewCadastroPecasManu.getSelectionModel().getSelectedItem();//Obtendo Peça selecionado
        if (peca != null) {
            boolean buttonConfirmarClicked = showFXMLCadastroPecasReposicaoInserir(peca);
            if (buttonConfirmarClicked) {
                pecasDAO.alterar(peca);
                carregarTableViewPecas();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma Peça na Tabela!");
            alert.show();
        }        

    }
    
        @FXML
    void handleButtonRemover(ActionEvent event) {
     
        Peca peca = tableViewCadastroPecasManu.getSelectionModel().getSelectedItem();
        if (peca != null) {
            pecasDAO.remover(peca);
            carregarTableViewPecas();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma Peça na Tabela!");
            alert.show();
        }        
    }
}
