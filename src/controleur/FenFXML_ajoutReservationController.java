/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.util.Date;
import java.util.Locale;
import javafx.application.Platform;
import static javafx.collections.FXCollections.observableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import modele.Association;
import modele.Reservation;
import modele.connBDD;


/**
 * FXML Controller class
 *
 * @author Luis
 */
public class FenFXML_ajoutReservationController implements Initializable {
    
    @FXML
    ComboBox cmbAsso, cmbSalle, cmbHeure;
    @FXML
    DatePicker dpJourRese;
    mainApp MainApp;
    boolean jourPresent = false;
    
    ObservableList <Association> lesAssociations;
    ObservableList<Reservation> lesReservations;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        dpJourRese.setValue(LocalDate.now());
    }
    

    public void remplirLesCBM(ObservableList <Association> pLesAssociations)
    {
       ObservableList lesSalles = FXCollections.observableArrayList();
       
       lesAssociations = pLesAssociations;
       cmbAsso.setItems(lesAssociations);
       cmbAsso.getSelectionModel().select(1);
       remplirSalle();
       remplirHeure();
    }
    
    public void remplirHeure()
    {
        jourPresent = false;
        int[] tabReservation = new int[lesReservations.size()];
        int indexTab = 0;
        int jourRetenu=0;
        ObservableList <String> heures = FXCollections.observableArrayList();
        Date jourChoisis = java.sql.Date.valueOf(dpJourRese.getValue());
        
        for(int i = 0; i<= lesReservations.size()-1;i++)
        {
            if(jourChoisis.equals(lesReservations.get(i).getJour()))
            {
                System.out.println("If qui rend true");
                tabReservation[indexTab] = i;
                indexTab++;
                jourRetenu = i;
                jourPresent = true;
            }
        }
        if(jourPresent == false)
        {
            System.out.println("Par le if\nJourPresent : " + jourPresent);
            for(int i=8; i<=21; i++)
            {
                heures.add(String.valueOf(i));
            }
        }
        else
        {
            System.out.println("Par le else\njourPresent : " + jourPresent);
            for(int i=8; i<=21; i++)
            {
                boolean heureLibre = true;
                for(int k = 0; k<=indexTab;k++)
                {
                    int heure = lesReservations.get(tabReservation[k]).getHeure().getHours();
                    if(heure == i)
                    {
                        heureLibre = false;
                    }
                }
                if(heureLibre == true)
                {
                    heures.add(String.valueOf(i));
                }
                heureLibre = true;
            }
        }
        cmbHeure.setItems(heures);
        cmbHeure.getSelectionModel().select(0);
    }
    
    
    
    public void remplirSalle()
    {
        ObservableList lesSportsSalles;
        ObservableList DistinctlesSportsSalles = FXCollections.observableArrayList();    
        String pAsso = cmbAsso.getSelectionModel().getSelectedItem().toString();
        DistinctlesSportsSalles = connBDD.getLesSallesParAsso(pAsso);
        cmbSalle.setItems(DistinctlesSportsSalles);
        cmbSalle.getSelectionModel().select(0);
        lesReservations = connBDD.getReservations(cmbSalle.getSelectionModel().getSelectedItem().toString());
    }
    
    public void setReservations()
    {
        lesReservations = connBDD.getReservations(cmbSalle.getSelectionModel().getSelectedItem().toString());
        remplirHeure();
        System.out.println("setReservation");
    }
    
    public void ajoutReservation()
    {
        String heure = cmbHeure.getSelectionModel().getSelectedItem().toString() + ":00:00";
        String jour = java.sql.Date.valueOf(dpJourRese.getValue()).toString();
        String refSalle = cmbSalle.getSelectionModel().getSelectedItem().toString();
        String refAsso = cmbAsso.getSelectionModel().getSelectedItem().toString();
        
        System.out.println("Heure : " + heure + "\njour : " + jour + "\nRefSalle : " + refSalle + "\nrefAsso : " + refAsso);
        
        //connBDD.ajoutReservation(refSalle, jour, heure, refAsso);
        ;
    }
}
