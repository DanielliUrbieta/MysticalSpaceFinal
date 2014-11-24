/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystical.controller.Rodada;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mystical.controller.ControlledScreen;
import mystical.controller.Main;
import mystical.controller.ScreensController;
import mystical.controllerDAO.CampeonatoDAO;
import mystical.controllerDAO.RodadaDAO;
import mystical.model.Campeonato;
import mystical.model.Partida;
import mystical.model.Rodada;

/**
 * FXML Controller class
 *
 * @author Danielli
 */
public class ListarRodadaController implements Initializable, ControlledScreen {

    private final RodadaDAO rodadaDAO = new RodadaDAO();
    private final CampeonatoDAO campDAO = new CampeonatoDAO();
    ObservableList<Rodada> listRodada;
     
    ScreensController myController;
    @FXML
    private TableView<Rodada> table;
    @FXML
    private TableColumn colun2;
    @FXML
    private TableColumn colun3;
    @FXML
    private ComboBox<Campeonato> campeonatoBox;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       listarCampeonatos();
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    public void goToAdicionarPartida(ActionEvent event) {
        myController.setScreen(Main.adicionar);
    }

    @FXML
    public void goToEditarPartida(ActionEvent event) {
        myController.setScreen(Main.editar);
    }

    @FXML
    public void goToExcluirPartida(ActionEvent event) {
        myController.setScreen(Main.excluir);
    }

    @FXML
    public void goToListarPartida(ActionEvent event) {
        myController.setScreen(Main.listar);
    }

    @FXML
    public void goToAdicionarRodada(ActionEvent event){
        myController.setScreen(Main.adicionarRodada);
    }
    
     @FXML
    public void goToBuscarRodada(ActionEvent event){
        myController.setScreen(Main.buscarRodada);
    }
    
     @FXML
    public void goToExcluirRodada(ActionEvent event){
        myController.setScreen(Main.excluirRodada);
    }
    
     @FXML
    private void goToEditarRodada(ActionEvent event) {
        myController.setScreen(Main.editarRodada);
    }
    
    private void listarCampeonatos(){
        ObservableList<Campeonato> listCampeonato = FXCollections.observableArrayList(campDAO.findAll());
        campeonatoBox.setItems(listCampeonato);

    
    }
    
    @FXML
    private void campeonatoBoxAction() {

        if (campeonatoBox.getValue() != null && !campeonatoBox.getValue().toString().isEmpty()) {
            listarRodadas();
        } else {
            System.out.println("Nao deu");
        }

    }
    
    private void listarRodadas(){
        if(campeonatoBox.getValue()!=null)
        {
            listRodada =  FXCollections.observableArrayList(rodadaDAO.findAllById(
                campeonatoBox.getValue().getIdCampeonato()));
            table.setItems(listRodada);
            colun2.setCellValueFactory(new PropertyValueFactory<Rodada, String>("numero"));
            colun3.setCellValueFactory(new PropertyValueFactory<Rodada, String>("tempo"));
        }
        else 
            listRodada.clear();
    }
    
}
