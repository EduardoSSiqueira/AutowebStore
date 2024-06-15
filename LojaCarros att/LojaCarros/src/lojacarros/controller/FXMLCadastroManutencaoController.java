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
import lojacarros.model.Servico;
import lojacarros.model.dao.ServicosDAO;
import lojacarros.model.database.Database;
import lojacarros.model.database.DatabaseFactory;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLCadastroManutencaoController implements Initializable {
    
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServicosDAO.setConnection(connection);
        carregarTableViewServico();
        
        tableViewCadastroManutencao.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewServico(newValue));
    }    
    

     @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<Servico> tableViewCadastroManutencao;

    @FXML
    private TableColumn<Servico, String> tableColumnCadastroManutencaoVeiculo;

    @FXML
    private TableColumn<Servico, String> tableColumnCadastroManutencaoTipoServico;

    @FXML
    private Label labelCodSerivco;
    @FXML
    private Label labelTipoServico;

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
    
    private List<Servico> listServico;
    private ObservableList<Servico> observableListServico;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ServicosDAO ServicosDAO = new ServicosDAO(); 
    
    
    
    
    
     public void carregarTableViewServico() {
        tableColumnCadastroManutencaoVeiculo.setCellValueFactory(new PropertyValueFactory<>("veiculo"));
        tableColumnCadastroManutencaoTipoServico.setCellValueFactory(new PropertyValueFactory<>("tipoServico"));

        listServico = ServicosDAO.listar();

        observableListServico = FXCollections.observableArrayList(listServico);
        tableViewCadastroManutencao.setItems(observableListServico);
        
        
        
    }
     
    public void selecionarItemTableViewServico(Servico servico){
        if(servico != null){
        labelCodSerivco.setText(String.valueOf(servico.getCodServico()));
        labelTipoServico.setText(servico.getTipoServico());
        labelProprietarioVeiculo.setText(servico.getProprietario());
        labelMaterialUtilizado.setText(servico.getMaterial());
        labelPecasUtilizadas.setText(servico.getPecasUtilizadas());
        labelData.setText(String.valueOf(servico.getData()));
        labelCustos.setText(String.valueOf(servico.getCustos()));
        
        
        } else {
            
        labelCodSerivco.setText("");
        labelTipoServico.setText("");
        labelProprietarioVeiculo.setText("");
        labelMaterialUtilizado.setText("");
        labelPecasUtilizadas.setText("");
        labelData.setText("");
        labelCustos.setText("");
        
            
        }
    }
    
    @FXML
    void handleButtonAlterar(ActionEvent event) throws IOException {
        Servico servico = tableViewCadastroManutencao.getSelectionModel().getSelectedItem();//Obtendo cliente selecionado
        if (servico != null) {
            boolean buttonConfirmarClicked = showFXMLCadastroManutencaoInserir(servico);
            if (buttonConfirmarClicked) {
                ServicosDAO.alterar(servico);
                carregarTableViewServico();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um veiculo na Tabela!");
            alert.show();
        }        

    }
    
    @FXML
    void handleCadastroManutencaoInserir(ActionEvent event) throws IOException {
      Servico servico = new Servico();
        boolean buttonConfirmarClicked = showFXMLCadastroManutencaoInserir(servico);
        if (buttonConfirmarClicked) {
            ServicosDAO.inserir(servico);
            carregarTableViewServico();
        }

    }
    
     private boolean showFXMLCadastroManutencaoInserir(Servico servico ) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastroManutencaoInserirController.class.getResource("/lojacarros/view/FXMLCadastroManutencaoInserir.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro Manutenção");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLCadastroManutencaoInserirController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setServico(servico);
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
        
}
    
    @FXML
    void handleButtonRemover(ActionEvent event) {
     
        Servico servico = tableViewCadastroManutencao.getSelectionModel().getSelectedItem();
        if (servico != null) {
            ServicosDAO.remover(servico);
            carregarTableViewServico();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Servico na Tabela!");
            alert.show();
        }        
    }
}
