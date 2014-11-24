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
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mystical.controller.ControlledScreen;
import mystical.controller.Main;
import mystical.controller.ScreensController;
import mystical.controllerDAO.CampeonatoDAO;
import mystical.controllerDAO.RodadaDAO;
import mystical.model.Campeonato;
import mystical.model.Rodada;

/**
 * FXML Controller class
 *
 * @author Danielli
 */
public class ExcluirRodadaController implements Initializable, ControlledScreen {

    private final RodadaDAO rodadaDAO = new RodadaDAO();
    private final CampeonatoDAO campDAO = new CampeonatoDAO();
    Rodada rodada;
    ObservableList<Rodada> listRodada;

    ScreensController myController;
    @FXML
    private ComboBox<Campeonato> campeonatoBox;
    @FXML
    private TableView<Rodada> table;
    @FXML
    private TableColumn colun1;
    @FXML
    private TableColumn colun2;

    @FXML
    private Label sucesso;
    @FXML
    private Label falha;
    
    @FXML
    Button excluir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listarCampeonatos();
        sucesso.setVisible(false);
        falha.setVisible(false);
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
    public void goToAdicionarRodada(ActionEvent event) {
        myController.setScreen(Main.adicionarRodada);
    }

    @FXML
    public void goToBuscarRodada(ActionEvent event) {
        myController.setScreen(Main.buscarRodada);
    }

    @FXML
    public void goToExcluirRodada(ActionEvent event) {
        myController.setScreen(Main.excluirRodada);
    }

    @FXML
    private void goToEditarRodada(ActionEvent event) {
        myController.setScreen(Main.editarRodada);
    }

    private void listarCampeonatos() {
        ObservableList<Campeonato> listCampeonato = FXCollections.observableArrayList(campDAO.findAll());
        listCampeonato.sorted();
        campeonatoBox.setItems(listCampeonato);
       // SortedList<Campeonato> teste = FXCollections.sort(campDAO.findAll());
        
    }

    private void listarRodadas() {
        if (campeonatoBox.getValue() != null) {
            listRodada = FXCollections.observableArrayList(rodadaDAO.findAllById(
                    campeonatoBox.getValue().getIdCampeonato()));
            table.setItems(listRodada);
            colun1.setCellValueFactory(new PropertyValueFactory<Rodada, String>("numero"));
            colun2.setCellValueFactory(new PropertyValueFactory<Rodada, String>("tempo"));
        } else {
            listRodada.clear();
        }
    }

    @FXML
    private void campeonatoBoxAction() {

        if (campeonatoBox.getValue() != null && !campeonatoBox.getValue().toString().isEmpty()) {
            atualizaTabela();
            sucesso.setVisible(false);
            falha.setVisible(false);
        } else {
            System.out.println("Nao deu");
        }

    }

    @FXML
    private void atualizaTabela() {
        if (campeonatoBox.getValue() != null) {
            listRodada = FXCollections.observableArrayList(rodadaDAO.findAllById(
                    campeonatoBox.getValue().getIdCampeonato()));
            table.setEditable(true);
            table.setItems(listRodada);
            colun1.setCellValueFactory(new PropertyValueFactory<Rodada, String>("numero"));
            colun2.setCellValueFactory(new PropertyValueFactory<Rodada, String>("tempo"));

            table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue,
                    newValue) -> {
                        //Check whether item is selected and set value of selected item to Label
                        if (table.getSelectionModel().getSelectedItem() != null) {

                            rodada = newValue;
                        }

                    });
        } else {
            listRodada.clear();
        }
    }

    @FXML
    private void clear() {

        campeonatoBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void excluirAction() {
        if (table.getSelectionModel().getSelectedItem() != null) {
            rodadaDAO.delete(rodada);
            System.out.println("Excluir");
            atualizaTabela();
            clear();
            sucesso.setVisible(true);
        } else {
            System.out.println("Selecione Partida");
         //   sucesso.setVisible(false);
            falha.setVisible(true);

        }
    }
}
