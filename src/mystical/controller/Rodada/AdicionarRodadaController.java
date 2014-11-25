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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
public class AdicionarRodadaController implements Initializable, ControlledScreen {

    ScreensController myController;
    private final CampeonatoDAO campDAO = new CampeonatoDAO();
    private final RodadaDAO rodadaDAO = new RodadaDAO();

    @FXML
    Label alertaNumeroRodada;
    
    @FXML
    Label alertaCamposIncompletos;
    
    @FXML
    Label alertaRodadaExistente;
    
    @FXML
    Label sucesso;

    @FXML
    ComboBox<Campeonato> campeonatoBox;

    @FXML
    TextField numeroRodada;

    @FXML
    ComboBox<String> horas;

    @FXML
    ComboBox<String> minutos;

    ObservableList<String> listHoras = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09","10", "11", "12",
            "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24");
    ObservableList<String> listMinutos = FXCollections.observableArrayList("00", "15", "30", "45");

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        listarCampeonatos();
        horas.setItems(listHoras);
        minutos.setItems(listMinutos);
        alertaNumeroRodada.setVisible(false);
        alertaNumeroRodada.setText("*Por favor coloque um número válido");
        alertaCamposIncompletos.setVisible(false);
        alertaCamposIncompletos.setText("Por favor preencha todos os campos");
        alertaRodadaExistente.setVisible(false);
        sucesso.setVisible(false);
        sucesso.setText("Rodada inserida com sucesso");

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
        campeonatoBox.setItems(listCampeonato);

    }

    @FXML
    private void campeonatoBoxAction() {
        numeroRodada.setDisable(false);
        horas.setDisable(false);
        minutos.setDisable(false);
    }

    @FXML
    private void clear() {
        campeonatoBox.getSelectionModel().clearSelection();
        numeroRodada.clear();
        horas.getSelectionModel().clearSelection();
        minutos.getSelectionModel().clearSelection();
        alertaNumeroRodada.setVisible(false);
        alertaCamposIncompletos.setVisible(false);
        alertaRodadaExistente.setVisible(false);
        sucesso.setVisible(false);

    }

    @FXML
    private boolean verificaEhNumero() {
        int value;
        try {
            value = Integer.parseInt(numeroRodada.getText());
            if (value > 0) {
                System.out.println("eh numero");
                return true;
            } else {
                alertaNumeroRodada.setVisible(true);
                alertaCamposIncompletos.setVisible(false);
                alertaRodadaExistente.setVisible(false);
                sucesso.setVisible(false);
                 System.out.println(" nao vale");
                return false;
            }
        } catch (NumberFormatException ex) {
            System.err.println(ex);
            alertaNumeroRodada.setVisible(true);
            alertaCamposIncompletos.setVisible(false);
            alertaRodadaExistente.setVisible(false);
            sucesso.setVisible(false);
            System.out.println("não eh numero");
            return false;
        }

    }
    
    

    @FXML
    public void salvarRodada(ActionEvent actionEvent) {

        if (verificaEhNumero()) {
            if (campeonatoBox.getValue() != null && !campeonatoBox.getValue().toString().isEmpty()
                    && horas.getValue() != null && !horas.getValue().toString().isEmpty()
                    && numeroRodada.getText() != null && !numeroRodada.getText().toString().isEmpty()
                    && minutos.getValue() != null && !minutos.getValue().toString().isEmpty()) {
                if(!rodadaDAO.contemRodada(campeonatoBox.getValue().getIdCampeonato(),
                        Integer.parseInt(numeroRodada.getText()))){
                    Rodada novaRodada = new Rodada();
                    novaRodada.setNumero(Integer.parseInt(numeroRodada.getText()));
                    novaRodada.setCampeonato(campeonatoBox.getValue());
                    String duracao = horas.getValue() + ":" + minutos.getValue();
                    novaRodada.setTempo(duracao);

                    rodadaDAO.save(novaRodada);
                    alertaCamposIncompletos.setVisible(false);
                    alertaNumeroRodada.setVisible(false);
                    clear();
                    sucesso.setVisible(true);
                }
                else{
                 alertaRodadaExistente.setVisible(true);
                 alertaCamposIncompletos.setVisible(false);
                 sucesso.setVisible(false);
                 alertaNumeroRodada.setVisible(false);
                }

            } else {
                alertaCamposIncompletos.setVisible(true);

            }
        }

    }
}
