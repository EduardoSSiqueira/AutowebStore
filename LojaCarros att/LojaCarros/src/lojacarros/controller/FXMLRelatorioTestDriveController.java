/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojacarros.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lojacarros.model.Cliente;
import lojacarros.model.TestDrive;
import lojacarros.model.dao.TestDriveDAO;
import lojacarros.model.database.Database;
import lojacarros.model.database.DatabaseFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author 20201si029
 */
public class FXMLRelatorioTestDriveController implements Initializable {

  @FXML
    private TableView<TestDrive> tableViewTestDrive;

    @FXML
    private TableColumn<TestDrive, Cliente> tableColumnCliente;  

    //String
    @FXML
    private TableColumn<TestDrive, String> tableColumnVeiculo;
    
    @FXML
    private JFXButton  buttonImprimir;

    
    private List<TestDrive> listTestDrive;
    private ObservableList<TestDrive> observableListTestDrive;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final TestDriveDAO testdriveDAO = new TestDriveDAO();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        testdriveDAO.setConnection(connection);
        carregarTableView();
    }    
    
    public void carregarTableView(){
        tableColumnCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tableColumnVeiculo.setCellValueFactory(new PropertyValueFactory<>("veiculo"));
 
        
        listTestDrive = testdriveDAO.listar();
        observableListTestDrive = FXCollections.observableArrayList(listTestDrive);
        tableViewTestDrive.setItems(observableListTestDrive);
    }
    
    
    public void handleImprimir() throws JRException{
        
        URL url = getClass().getResource("/lojacarros/relatorios/RelatorioTestDrive.jasper");
         JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);//null: caso não existam filtros
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);//false: não deixa fechar a aplicação principal
        jasperViewer.setVisible(true);
    }
}
