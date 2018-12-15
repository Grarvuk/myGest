/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.application.Application;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.*;

/**
 *
 * @author Rabelais
 */
public class mainApp extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Association> lesAssociations = FXCollections.observableArrayList();

    
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Réservation gymnase");
        //lesEmployes = connBDD.getLesEmployes();
        
        try
        {
            FXMLLoader loader = new FXMLLoader(mainApp.class.getResource("/vue/FXML_noeudRacine.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            lesAssociations = connBDD.getAssociations();
            
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
    
    public void afficheResa() throws IOException
    {
            FXMLLoader loader = new
            FXMLLoader (mainApp.class.getResource("/vue/FenFXML_ajoutReservation.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Fen");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
    // Place l'étudiant dans le controleur
            FenFXML_ajoutReservationController controleur = loader.getController();
            controleur.remplirLesCBM(lesAssociations);
            dialogStage.show();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
    public ObservableList <Association> getLesAssociations()
    {
        return lesAssociations;
    }


}
