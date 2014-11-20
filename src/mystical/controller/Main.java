
package mystical.controller;

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
    public static String adicionarFile = "/mystical/controller/Partida/adicionarPartidas.fxml";
    public static String editar = "editar";
    public static String editarFile = "/mystical/controller/Partida/editarPartidas.fxml";
    public static String listar = "listar";
    public static String listarFile = "/mystical/controller/Partida/listarPartidas.fxml";
    public static String excluir = "excluir";
    public static String excluirFile = "/mystical/controller/Partida/excluirPartidas.fxml";
    public static String adicionarRodada = "adicionarRodada";
    public static String adicionarRodadaFile ="/mystical/controller/Rodada/adicionarRodada.fxml";
    public static String excluirRodada = "Excluir Rodada";
    public static String excluirRodadaFile = "/mystical/controller/Rodada/excluirRodada.fxml";
    public static String buscarRodada = "buscarRodada";
    public static String buscarRodadaFile = "/mystical/controller/Rodada/listarRodada.fxml";
    public static String editarRodada ="editarRodada";
    public static String editarRodadaFile = "/mystical/controller/Rodada/editarRodada.fxml";
    
    
    @Override
    public void start(Stage primaryStage) {
        
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Main.home, Main.homeFile);
        mainContainer.loadScreen(Main.adicionar, Main.adicionarFile);
        mainContainer.loadScreen(Main.editar, Main.editarFile);
        mainContainer.loadScreen(Main.listar, Main.listarFile);
        mainContainer.loadScreen(Main.excluir, Main.excluirFile);
        mainContainer.loadScreen(Main.adicionarRodada, Main.adicionarRodadaFile);
        mainContainer.loadScreen(Main.excluirRodada, Main.excluirRodadaFile);
        mainContainer.loadScreen(buscarRodada, buscarRodadaFile);
        mainContainer.loadScreen(editarRodada,editarRodadaFile);
        
       
       
        
        
        mainContainer.setScreen(Main.home);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root,600,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mystical Space");
        primaryStage.show();
       // primaryStage.setMaximized(true);
        primaryStage.setResizable(false); 
        setUserAgentStylesheet(STYLESHEET_CASPIAN);  
        
    }

       public static void main(String[] args) {
        launch(args);
    }
}