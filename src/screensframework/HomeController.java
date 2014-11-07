
package screensframework;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class HomeController implements Initializable, ControlledScreen {

    ScreensController myController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void goToScreen2(ActionEvent event){
       myController.setScreen(Main.adicionar);
    }
    
    @FXML
    private void goToScreen3(ActionEvent event){
       myController.setScreen(Main.editar);
    }
    
    @FXML
    private void goToScreen4(ActionEvent event){
       myController.setScreen(Main.listar);
    }
    
    @FXML
    private void goToScreen5(ActionEvent event){
        myController.setScreen(Main.excluir);
    }
}