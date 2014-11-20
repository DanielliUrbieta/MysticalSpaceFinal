/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystical.controller.Rodada;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mystical.controller.ControlledScreen;
import mystical.controller.Main;
import mystical.controller.ScreensController;

/**
 * FXML Controller class
 *
 * @author Danielli
 */
public class ListarRodadaController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> colun2;
    @FXML
    private TableColumn<?, ?> colun3;
    @FXML
    private ComboBox<?> campeonatoBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
}
