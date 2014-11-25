/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystical.controller.Rodada;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class EditarRodadaController implements Initializable, ControlledScreen {

    ScreensController myController;
    private final RodadaDAO rodadaDAO = new RodadaDAO();
    private final CampeonatoDAO campDAO = new CampeonatoDAO();
    ObservableList<Rodada> listRodada;

    @FXML
    private TableView<Rodada> table;
    @FXML
    private TableColumn colun2;
    @FXML
    private TableColumn colun1;
    @FXML
    private ComboBox<Campeonato> campeonatoBox;
    @FXML
    private Label falha;
    @FXML
    private ComboBox<String> horas;
    @FXML
    private ComboBox<String> minutos;

    ObservableList<String> listHoras = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
            "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24");
    ObservableList<String> listMinutos = FXCollections.observableArrayList("00", "15", "30", "45");
    int idRodada;
    Campeonato campeonatoPai;
    int numeroRodada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listarCampeonatos();
        horas.setItems(listHoras);
        minutos.setItems(listMinutos);
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
    private void comboBoxActionCampeonato(ActionEvent event) {
        if (campeonatoBox.getValue() != null && !campeonatoBox.getValue().toString().isEmpty()) {
            listarRodadas();
        } else {
            System.out.println("Nao deu");
        }
    }

    @FXML
    private void tipoResultadoAction(ActionEvent event) {
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    private void listarCampeonatos() {
        ObservableList<Campeonato> listCampeonato = FXCollections.observableArrayList(campDAO.findAll());
        campeonatoBox.setItems(listCampeonato);
    }

    private void listarRodadas() {
        atualizaTabela();
    }

    private void clear() {
        campeonatoBox.getSelectionModel().clearSelection();
        horas.getSelectionModel().clearSelection();
        minutos.getSelectionModel().clearSelection();
    }

    private void atualizaTabela() {
        if (campeonatoBox.getValue() != null) {
            listRodada = FXCollections.observableArrayList(rodadaDAO.findAllById(
                    campeonatoBox.getValue().getIdCampeonato()));
            table.setItems(listRodada);
            colun1.setCellValueFactory(new PropertyValueFactory<Rodada, String>("numero"));
            colun2.setCellValueFactory(new PropertyValueFactory<Rodada, String>("tempo"));
            table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                //Check whether item is selected and set value of selected item to Label
                if (table.getSelectionModel().getSelectedItem() != null) {

                    falha.setVisible(false);
                    horas.setDisable(false);
                    minutos.setDisable(false);

                    horas.getSelectionModel().select(newValue.getTempo().substring(0, 2));
                    minutos.getSelectionModel().select(newValue.getTempo().substring(3, 5));
                    numeroRodada = newValue.getNumero();
                    idRodada = newValue.getIdRodada();
                    campeonatoPai = newValue.getCampeonato();
                }

            });
        } else {
            listRodada.clear();
        }

    }

    @FXML
    private void salvarAction(ActionEvent actionEvent) {

        if (horas.getValue() == null) {
            falha.setVisible(true);
            falha.setText("Por favor, selecione uma partida e preencha todos os campos abaixo");

        } else {
            
            Rodada novoObj = new Rodada();
            novoObj.setIdRodada(idRodada);
            String duracao = horas.getValue() + ":" + minutos.getValue();
            novoObj.setTempo(duracao);
            novoObj.setCampeonato(campeonatoPai);
            novoObj.setNumero(numeroRodada);
            rodadaDAO.update(novoObj);
            atualizaTabela();
            clear();
            System.out.println("Mensagem de Sucesso");
            falha.setVisible(false);

        }

    }

}
