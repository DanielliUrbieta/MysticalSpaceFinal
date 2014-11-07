
package screensframework;

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
import mystical.controllerDAO.CampeonatoDAO;
import mystical.controllerDAO.PartidaDAO;
import mystical.controllerDAO.RodadaDAO;
import mystical.model.Campeonato;
import mystical.model.Partida;
import mystical.model.Rodada;


/**
 * FXML Controller class
 *
 * @author Angie
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

    //ObservableList<String> listRodada = FXCollections.observableArrayList("Rodada-1", "Rodada-2", "Rodada-3"); 
    ObservableList<Campeonato> listCampeonato = FXCollections.observableArrayList(campDAO.findAll());
    ObservableList<String> listTpResultado = FXCollections.observableArrayList("Empate", "Vitória");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        campeonatoBox.setItems(listCampeonato);
        tipoResultado.setItems(listTpResultado);
    }

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void goToScreen1(ActionEvent event) {
        myController.setScreen(Main.home);
    }

    @FXML
    private void goToScreen3(ActionEvent event) {
        myController.setScreen(Main.editar);
    }

    @FXML
    private void goToScreen4(ActionEvent event) {
        myController.setScreen(Main.listar);
    }
    
    @FXML
    private void goToScreen5(ActionEvent event){
        myController.setScreen(Main.excluir);
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

    }

    @FXML
    public void salvarPartida(ActionEvent actionEvent) {
        
        
        if (campeonatoBox.getValue() != null && !campeonatoBox.getValue().toString().isEmpty() &&
            rodadaBox.getValue() != null && !rodadaBox.getValue().toString().isEmpty() &&
            vencedor.getText() != null && !vencedor.getText().toString().isEmpty() &&
            tipoResultado.getValue() != null && !tipoResultado.getValue().toString().isEmpty()) { 
            Partida novaPartida = new Partida();
            novaPartida.setRodada(rodadaBox.getValue());
            novaPartida.setTipoResultado(tipoResultado.getValue());
            novaPartida.setVencedor(vencedor.getText());
            partidaDAO.save(novaPartida);
            sucesso.setVisible(true);
            camposIncompletos.setVisible(false);
            clear();    
            
        }
        else{
            
             sucesso.setVisible(false);
             camposIncompletos.setVisible(true);
        }
        
        
       
    }

    @FXML
    public void comboBoxActionCampeonato() {
        if(campeonatoBox.getValue()!=null){
        ObservableList<Rodada> listRodada
                = FXCollections.observableArrayList(rodadaDAO.findAllById(
                                campeonatoBox.getValue().getIdCampeonato()));
        rodadaBox.setDisable(false);
        rodadaBox.setItems(listRodada);
        }

    }

    @FXML
    public void comboBoxActionRodada() {
        tipoResultado.setDisable(false);
    }

    @FXML
    public void comboBoxActiontResultado() {
        if("Empate".equals(tipoResultado.getValue())){
           vencedor.setText("Nenhum");
           vencedor.setEditable(false);
           vencedor.setDisable(false);
        }
        else{
            vencedor.setText(null);
            vencedor.setDisable(false);
            vencedor.setEditable(true);
        }
    }
    
    
    

}
