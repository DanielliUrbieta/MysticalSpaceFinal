/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Danielli
 */
public class ExcluirPartidasController implements Initializable, ControlledScreen {

    ScreensController myController;
    PartidaDAO dao = new PartidaDAO();
    Partida partida = new Partida();

    private final CampeonatoDAO campDAO = new CampeonatoDAO();
    private final RodadaDAO rodadaDAO = new RodadaDAO();
    ObservableList<Campeonato> listCampeonato = FXCollections.observableArrayList(campDAO.findAll());
    @FXML
    TableView<Partida> table;

    @FXML
    TableColumn colun1;

    @FXML
    TableColumn colun2;
    @FXML
    ComboBox<Rodada> rodadaBox;
    @FXML
    ComboBox<Campeonato> campeonatoBox;
    @FXML
    TableColumn colun3;
    @FXML
    Label sucesso;
    @FXML
    Label falha;
    ObservableList<Partida> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        campeonatoBox.setItems(listCampeonato);

    }

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
    private void goToScreen4(ActionEvent event) {
        myController.setScreen(Main.listar);
    }

    
    @FXML
    public void comboBoxActionCampeonato() {

       if (campeonatoBox.getValue() != null) {

            ObservableList<Rodada> listRodada
                    = FXCollections.observableArrayList(rodadaDAO.findAllById(
                                    campeonatoBox.getValue().getIdCampeonato()));

            rodadaBox.setDisable(false);
            rodadaBox.setItems(listRodada);
        } 
       falha.setVisible(false);
       sucesso.setVisible(false);

    }
    
    @FXML
    private void clear(){
        table.getSelectionModel().select(null);
        campeonatoBox.getSelectionModel().clearSelection();
        rodadaBox.getSelectionModel().clearSelection();
        sucesso.setVisible(true);
        
    }

    @FXML
    private void atualizaTabela() {
        if (rodadaBox.getValue() != null) {
            data = FXCollections.observableArrayList(
                    dao.findAllById(rodadaBox.getValue().getIdRodada()));
            table.setEditable(true);
            table.setItems(data);
            colun1.setCellValueFactory(new PropertyValueFactory<Partida, String>("idPartida"));
            colun2.setCellValueFactory(new PropertyValueFactory<Partida, String>("vencedor"));
            colun3.setCellValueFactory(new PropertyValueFactory<Partida, String>("tipoResultado"));
            table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue,
                    newValue) -> {
                //Check whether item is selected and set value of selected item to Label
                if (table.getSelectionModel().getSelectedItem() != null) {

                    partida = newValue;
                }

            });
        }
        else 
            data.clear();
    }
    
    @FXML
    private void comboboxActionRodada() {
        atualizaTabela();

    }
    
    @FXML 
    private void excluirAction(){
        if(partida!=null&&(data!=null || !data.isEmpty())){
            dao.delete(partida);
            System.out.println("Excluir");
            atualizaTabela();
            sucesso.setVisible(true);
            clear();
        }
        else{
            System.out.println("Selecione Partida");
            falha.setVisible(true);
        }
    }
}
