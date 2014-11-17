package screensframework;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class EditarPartidasController implements Initializable, ControlledScreen {

    PartidaDAO dao = new PartidaDAO();

    private final CampeonatoDAO campDAO = new CampeonatoDAO();
    private final RodadaDAO rodadaDAO = new RodadaDAO();

    @FXML
    TableView<Partida> table;

    @FXML
    TableColumn colun1;

    @FXML
    TableColumn colun2;
    ScreensController myController;

    @FXML
    TextField vencedor;

    @FXML
    ComboBox<Rodada> rodadaBox;

    @FXML
    ComboBox<Campeonato> campeonatoBox;

    @FXML
    ComboBox<String> tipoResultado;

    @FXML
    Label falha;

    private Rodada rodadaPai;
    private int idPartida;
    ObservableList<Partida> data;

    //ObservableList<String> listCampeonato = FXCollections.observableArrayList("Campeonato-1", "Campeonato-2", "Campeonato-3");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // rodadaBox.setItems(listRodada);
        listarCampeonatos();
        listarTipoResultado();

    }

    public void listarCampeonatos() {

        ObservableList<Campeonato> listCampeonato = FXCollections.observableArrayList(campDAO.findAll());
        campeonatoBox.setItems(listCampeonato);
    }

    public void listarTipoResultado() {

        ObservableList<String> tipoResultadoList = FXCollections.observableArrayList("Empate", "Vitória");
        tipoResultado.setItems(tipoResultadoList);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void goToScreen1(ActionEvent event) {
        myController.setScreen(Main.home);
    }

    @FXML
    private void goToScreen2(ActionEvent event) {
        myController.setScreen(Main.adicionar);
    }

    @FXML
    private void goToScreen4(ActionEvent event) {
        myController.setScreen(Main.listar);
    }

    @FXML
    private void goToScreen5(ActionEvent event) {
        myController.setScreen(Main.excluir);
    }

    @FXML
    private void clear() {
        tipoResultado.getSelectionModel().clearSelection();
        vencedor.clear();
        // campeonatoBox.getSelectionModel().clearSelection();
        // rodadaBox.getSelectionModel().clearSelection();
    }

    @FXML
    public void listarRodada() {

        if (campeonatoBox.getValue() != null) {

            ObservableList<Rodada> listRodada
                    = FXCollections.observableArrayList(rodadaDAO.findAllById(
                                    campeonatoBox.getValue().getIdCampeonato()));

            rodadaBox.setDisable(false);
            rodadaBox.setItems(listRodada);
        }
    }

    @FXML
    public void comboBoxActionCampeonato() {

        listarRodada();
        falha.setVisible(false);

    }

    @FXML
    private void atualizaTabela() {
        if (rodadaBox.getValue() != null) {
            data = FXCollections.observableArrayList(
                    dao.findAllById(rodadaBox.getValue().getIdRodada()));
            table.setEditable(true);
            table.setItems(data);
            colun1.setCellValueFactory(new PropertyValueFactory<Partida, String>("vencedor"));
            colun2.setCellValueFactory(new PropertyValueFactory<Partida, String>("tipoResultado"));
            table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                //Check whether item is selected and set value of selected item to Label
                if (table.getSelectionModel().getSelectedItem() != null) {

                    falha.setVisible(false);
                    vencedor.setDisable(false);
                    tipoResultado.setDisable(false);
                    vencedor.setText(newValue.getVencedor());
                    tipoResultado.getSelectionModel().select(newValue.getTipoResultado());
                    idPartida = newValue.getIdPartida();
                    rodadaPai = newValue.getRodada();
                }

            });
        } else {
            data.clear();
        }
    }

    @FXML
    private void comboboxActionRodada() {
        atualizaTabela();
        falha.setVisible(false);
        vencedor.clear();
        tipoResultado.getSelectionModel().clearSelection();
        vencedor.setDisable(true);
        tipoResultado.setDisable(true);

    }

    @FXML
    private void tipoResultadoAction() {
        if (tipoResultado.getValue() == "Empate") {
            vencedor.setText("Nenhum");
            vencedor.setEditable(false);
        } else {
            vencedor.setEditable(true);
        }
    }

    @FXML
    private void salvarAction(ActionEvent actionEvent) {

        if ((vencedor.getText() == null || vencedor.getText().isEmpty())) {
            falha.setVisible(true);
            falha.setText("Por favor, selecione uma partida e preencha todos os campos abaixo");

        } else {
            //FAZER UPDATE aqui passando apenas os campos que não são nulos
            Partida novoObj = new Partida();
            novoObj.setIdPartida(idPartida);
            novoObj.setTipoResultado(tipoResultado.getValue());
            novoObj.setVencedor(vencedor.getText());
            novoObj.setRodada(rodadaPai);

            dao.update(novoObj);
            atualizaTabela();
            clear();
            System.out.println("Mensagem de Sucesso");
            falha.setVisible(false);

        }

    }
}
