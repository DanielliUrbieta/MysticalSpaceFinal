
package screensframework;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Angie
 */
public class Main extends Application {
    public static String home = "main";
    public static String homeFile = "home.fxml";
    public static String adicionar = "adicionar";
    public static String adicionarFile = "adicionarPartidas.fxml";
    public static String editar = "editar";
    public static String editarFile = "editarPartidas.fxml";
    public static String listar = "listar";
    public static String listarFile = "listarPartidas.fxml";
    public static String excluir = "excluir";
    public static String excluirFile = "excluirPartidas.fxml";
    
    @Override
    public void start(Stage primaryStage) {
        
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Main.home, Main.homeFile);
        mainContainer.loadScreen(Main.adicionar, Main.adicionarFile);
        mainContainer.loadScreen(Main.editar, Main.editarFile);
        mainContainer.loadScreen(Main.listar, Main.listarFile);
        mainContainer.loadScreen(Main.excluir, Main.excluirFile);
        
        mainContainer.setScreen(Main.home);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root,600,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mystical Space");
        primaryStage.show();
    }

       public static void main(String[] args) {
        launch(args);
    }
}