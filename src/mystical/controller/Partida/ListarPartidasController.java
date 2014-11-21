package mystical.controller.Partida;

import java.net.URL;
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
import mystical.controllerDAO.PartidaDAO;
import mystical.controllerDAO.RodadaDAO;
import mystical.model.Campeonato;
import mystical.model.Partida;
import mystical.model.Rodada;

public class ListarPartidasController implements Initializable, ControlledScreen {

    ScreensController myController;
    PartidaDAO dao = new PartidaDAO();
    private final CampeonatoDAO campDAO = new CampeonatoDAO();
    private final RodadaDAO rodadaDAO = new RodadaDAO();
    final Label lblTool = new Label();

    @FXML
    ComboBox<String> escolher;
    @FXML
    ComboBox<Campeonato> campeonatoBox;
    @FXML
    ComboBox<Rodada> rodadaBox;

    @FXML
    TableView<Partida> table;

    @FXML
    TableColumn colun1;

    @FXML
    TableColumn colun2;

    @FXML
    TableColumn colun3;

    //ObservableList<String> listEscolher = FXCollections.observableArrayList("Todas", "Campeonato", "Rodada");
    ObservableList<Rodada> listRodada;
    ObservableList<Partida> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        table.setEditable(true);

        listarCampeonatos();

    }

    public void listarCampeonatos() {

        ObservableList<Campeonato> listCampeonato = FXCollections.observableArrayList(campDAO.findAll());
        campeonatoBox.setItems(listCampeonato);
    }

    public void listarRodadas() {

        listRodada = FXCollections.observableArrayList(rodadaDAO.findAllById(
                campeonatoBox.getValue().getIdCampeonato()));

        rodadaBox.setDisable(false);
        rodadaBox.setItems(listRodada);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void goToScreen2(ActionEvent event) {
        myController.setScreen(Main.adicionar);
    }

    @FXML
    private void goToScreen3(ActionEvent event) {
        myController.setScreen(Main.editar);
    }

    @FXML
    private void goToScreen5(ActionEvent event) {
        myController.setScreen(Main.excluir);
    }
    @FXML
    private void goToAdicionarRodada(ActionEvent event) {
        myController.setScreen(Main.adicionarRodada);
    }
    @FXML
    private void goToExcluirRodada(ActionEvent event) {
        myController.setScreen(Main.excluirRodada);
    }
    @FXML
    private void goToListarRodada(ActionEvent event) {
        myController.setScreen(Main.buscarRodada);
    }

     @FXML
    private void goToEditarRodada(ActionEvent event) {
        myController.setScreen(Main.editarRodada);
    }
    /*
     @FXML
     private void escolherAction(ActionEvent event) {
     if (escolher.getValue() == "Campeonato") {
     ObservableList<Campeonato> listCampeonato = FXCollections.observableArrayList(campDAO.findAll());
     campeonatoBox.setItems(listCampeonato);
     campeonatoBox.setDisable(false);
     rodadaBox.getSelectionModel().clearSelection();
     rodadaBox.setDisable(true);
     } else if (escolher.getValue() == "Rodada") {
     ObservableList<Campeonato> listCampeonato = FXCollections.observableArrayList(campDAO.findAll());
     campeonatoBox.setItems(listCampeonato);
     campeonatoBox.setDisable(false);
     }
     }

     */
    @FXML
    private void campeonatoBoxAction() {

        if (campeonatoBox.getValue() != null && !campeonatoBox.getValue().toString().isEmpty()) {
            listarRodadas();
        } else {
            System.out.println("Nao deu");
        }

    }

    private void listarPartidas() {
        if (rodadaBox.getValue() != null) {
            data = FXCollections.observableArrayList(
                    dao.findAllById(rodadaBox.getValue().getIdRodada()));
            table.setEditable(true);
            table.setItems(data);
            colun1.setCellValueFactory(new PropertyValueFactory<Partida, String>("idPartida"));
            colun2.setCellValueFactory(new PropertyValueFactory<Partida, String>("vencedor"));
            colun3.setCellValueFactory(new PropertyValueFactory<Partida, String>("tipoResultado"));
        } else {
            data.clear();
        }
    }

    @FXML
    private void rodadaBoxAction() {

        listarPartidas();

    }

}
