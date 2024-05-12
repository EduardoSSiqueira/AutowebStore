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
import lojacarros.model.Veiculo;
import lojacarros.model.dao.VeiculoDAO;
import lojacarros.model.database.Database;
import lojacarros.model.database.DatabaseFactory;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLCadastroVeiculosController implements Initializable {

    /**
     * Initializes the controller class.
     */
     
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
    private Label labelCodVeiculo;

    @FXML
    private JFXButton buttonAlterar;

    @FXML
    private JFXButton buttonInserir;

    @FXML
    private JFXButton buttonRemover;
    
    @FXML
    private AnchorPane anchorPane;
    
    private List<Veiculo> listVeiculo;
    private ObservableList<Veiculo> observableListVeiculo;
    
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO(); 
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        veiculoDAO.setConnection(connection);
        carregarTableViewVeiculo();
        
        tableViewCadastroVeiculos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewVeiculo(newValue));
        
    } 
   
   
   public void carregarTableViewVeiculo() {
        tableColumnCadastroVeiculoModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tableColumnCadastroVeiculoAno.setCellValueFactory(new PropertyValueFactory<>("ano"));

        listVeiculo = veiculoDAO.listar();

        observableListVeiculo = FXCollections.observableArrayList(listVeiculo);
        tableViewCadastroVeiculos.setItems(observableListVeiculo);
        
    }  
   
   public void selecionarItemTableViewVeiculo(Veiculo veiculo){
        if(veiculo != null){
        labelCodVeiculo.setText(String.valueOf(veiculo.getCdVeiculo()));
        labelModeloVeiculo.setText(veiculo.getModelo());
        labelAnoVeiculo.setText(veiculo.getAno());
        labelPrecoVeiculo.setText(veiculo.getPreco());
        } else {
            
        labelCodVeiculo.setText("");
        labelModeloVeiculo.setText("");
        labelAnoVeiculo.setText("");
        labelPrecoVeiculo.setText("");
            
        }
    }
         
   
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
    void handleCadastrosVeiculoInserir(ActionEvent event) throws IOException {
        Veiculo veiculo = new Veiculo();
        boolean buttonConfirmarClicked = showFXMLCadastroVeiculoInserir(veiculo);
        if (buttonConfirmarClicked) {
            veiculoDAO.inserir(veiculo);
            carregarTableViewVeiculo();
        }

    }
    
    
    
    
    
    @FXML
    void handleButtonAlterar(ActionEvent event) throws IOException {
        Veiculo veiculo = tableViewCadastroVeiculos.getSelectionModel().getSelectedItem();//Obtendo veiculo selecionado
        if (veiculo != null) {
            boolean buttonConfirmarClicked = showFXMLCadastroVeiculoInserir(veiculo);
            if (buttonConfirmarClicked) {
                veiculoDAO.alterar(veiculo);
                carregarTableViewVeiculo();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Veículo na Tabela!");
            alert.show();
        }        

    }

    @FXML
    void handleButtonRemover(ActionEvent event) {
     
        Veiculo veiculo = tableViewCadastroVeiculos.getSelectionModel().getSelectedItem();
        if (veiculo != null) {
            veiculoDAO.remover(veiculo);
            carregarTableViewVeiculo();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um veiculo na Tabela!");
            alert.show();
        }        
    }

   
    
}
     
