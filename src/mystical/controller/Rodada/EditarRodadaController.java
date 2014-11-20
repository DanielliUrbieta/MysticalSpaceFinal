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
import javafx.scene.control.Label;
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
public class EditarRodadaController implements Initializable , ControlledScreen{
    
    ScreensController myController;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> colun2;
    @FXML
    private TableColumn<?, ?> colun1;
    @FXML
    private ComboBox<?> campeonatoBox;
    @FXML
    private Label falha;
    @FXML
    private ComboBox<?> tipoResultado;
    @FXML
    private ComboBox<?> tipoResultado1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goToAdicionarPartida(ActionEvent event) {
        myController.setScreen(Main.adicionar);
    }

    @FXML
    private void goToEditarPartida(ActionEvent event) {
         myController.setScreen(Main.editar);
    }

    @FXML
    private void goToBuscarPartida(ActionEvent event) {
         myController.setScreen(Main.listar);
    }
    
    @FXML
    private void goToExcluirPartida(ActionEvent event) {
         myController.setScreen(Main.excluir);
    }

    @FXML
    private void goToBuscarRodada(ActionEvent event) {
         myController.setScreen(Main.buscarRodada);
    }

    @FXML
    private void goToExcluirRodada(ActionEvent event) {
         myController.setScreen(Main.excluirRodada);
    }

    @FXML
    private void goToAdicionarRodada(ActionEvent event) {
         myController.setScreen(Main.adicionarRodada);
    }

    @FXML
    private void salvarAction(ActionEvent event) {
    }

    @FXML
    private void comboBoxActionCampeonato(ActionEvent event) {
    }

    @FXML
    private void tipoResultadoAction(ActionEvent event) {
    }

   @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
}
