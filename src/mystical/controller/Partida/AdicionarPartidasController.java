package mystical.controller.Partida;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import mystical.controller.ControlledScreen;
import mystical.controller.Main;
import mystical.controller.ScreensController;
import mystical.controllerDAO.CampeonatoDAO;
import mystical.controllerDAO.PartidaDAO;
import mystical.controllerDAO.RodadaDAO;
import mystical.model.Campeonato;
import mystical.model.Partida;
import mystical.model.Rodada;

/**
 * FXML Controller class
 *
 * @author Danielli
 */
public class AdicionarPartidasController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    TextField vencedor;
    @FXML
    ComboBox<Rodada> rodadaBox;
    @FXML
    ComboBox<Campeonato> campeonatoBox;
    @FXML
    Label sucesso;
    @FXML
    Label camposIncompletos;
    @FXML
    ComboBox<String> tipoResultado;

    private final CampeonatoDAO campDAO = new CampeonatoDAO();
    private final RodadaDAO rodadaDAO = new RodadaDAO();
    private final PartidaDAO partidaDAO = new PartidaDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        listarCampeonatos();
        listarTipoResultado();

    }

    public void listarCampeonatos() {

        ObservableList<Campeonato> listCampeonato = FXCollections.observableArrayList(campDAO.findAll());
        campeonatoBox.setItems(listCampeonato);

    }

    public void listarTipoResultado() {

        ObservableList<String> listTpResultado = FXCollections.observableArrayList("Empate", "Vitória");
        tipoResultado.setItems(listTpResultado);

    }

    public void listarRodada() {
        ObservableList<Rodada> listRodada
                = FXCollections.observableArrayList(rodadaDAO.findAllById(
                                campeonatoBox.getValue().getIdCampeonato()));

        rodadaBox.setDisable(false);
        
        rodadaBox.setItems(listRodada.sorted());
        sucesso.setVisible(false);

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    

    @FXML
    private void goToEditarPartida(ActionEvent event) {
        myController.setScreen(Main.editar);
    }

    @FXML
    private void goToListarPartida(ActionEvent event) {
        myController.setScreen(Main.listar);
    }

    @FXML
    private void goToExcluirPartida(ActionEvent event) {
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
    private void goToBuscarRodada(ActionEvent event) {
        myController.setScreen(Main.buscarRodada);
    }
    
    @FXML
    private void goToEditarRodada(ActionEvent event) {
        myController.setScreen(Main.editarRodada);
    }

    @FXML
    public void clear() {

        vencedor.clear();
        campeonatoBox.getSelectionModel().clearSelection();
        rodadaBox.getSelectionModel().clearSelection();
        tipoResultado.getSelectionModel().clearSelection();
        rodadaBox.setDisable(true);
        vencedor.setDisable(true);
        tipoResultado.setDisable(true);
        camposIncompletos.setVisible(false);
        vencedor.setEditable(false);
        sucesso.setVisible(false);

    }

    @FXML
    public void salvarPartida(ActionEvent actionEvent) {

        if (campeonatoBox.getValue() != null && !campeonatoBox.getValue().toString().isEmpty()
                && rodadaBox.getValue() != null && !rodadaBox.getValue().toString().isEmpty()
                && vencedor.getText() != null && !vencedor.getText().toString().isEmpty()
                && tipoResultado.getValue() != null && !tipoResultado.getValue().toString().isEmpty()) {
            Partida novaPartida = new Partida();
            novaPartida.setRodada(rodadaBox.getValue());
            novaPartida.setTipoResultado(tipoResultado.getValue());
            novaPartida.setVencedor(vencedor.getText());
            partidaDAO.save(novaPartida);

            camposIncompletos.setVisible(false);
            clear();
            sucesso.setVisible(true);
        } else {

            sucesso.setVisible(false);
            camposIncompletos.setVisible(true);
        }

    }

    @FXML
    public void comboBoxActionCampeonato() {
        if (campeonatoBox.getValue() != null) {

            listarRodada();
        }

    }

    @FXML
    public void comboBoxActionRodada() {
        tipoResultado.setDisable(false);
    }

    @FXML
    public void comboBoxActiontResultado() {
        if ("Empate".equals(tipoResultado.getValue())) {
            vencedor.setText("Nenhum");
            vencedor.setEditable(false);
            vencedor.setDisable(false);
        } else {
            vencedor.setText(null);
            vencedor.setDisable(false);
            vencedor.setEditable(true);
        }
    }

}