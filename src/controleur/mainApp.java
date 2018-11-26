/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Rabelais
 */
public class mainApp extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;


    
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Coordonnées employés");
        //lesEmployes = connBDD.getLesEmployes();
        
        try
        {
            FXMLLoader loader = new FXMLLoader(mainApp.class.getResource("/vue/FXML_noeudRacine.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            loader = new FXMLLoader(mainApp.class.getResource("/vue/FenFXML_main.fxml"));
            AnchorPane overviewPage = (AnchorPane)loader.load();
            rootLayout.setCenter(overviewPage);
            FenFXML_mainController controleur = loader.getController();
            controleur.setMainApp(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    


}
