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
import java.time.Instant;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.util.Date;
import java.util.Locale;
import static javafx.collections.FXCollections.observableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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


    public void remplirLesCBM(ObservableList <Association> pLesAssociations, ObservableList<Reservation> plesReservations)
    {
       ObservableList lesSalles = FXCollections.observableArrayList();
       
       lesReservations = plesReservations;
       lesAssociations = pLesAssociations;
       cmbAsso.setItems(lesAssociations);
       cmbAsso.getSelectionModel().select(1);
       remplirHeure();
       remplirSalle();
    }
    
    public void remplirHeure()
    {
        jourPresent = false;
        int jourRetenu=0;
        ObservableList <String> heures = FXCollections.observableArrayList();
        Date jourChoisis = java.sql.Date.valueOf(dpJourRese.getValue());
        
        for(int i = 0; i<= lesReservations.size()-1;i++)
        {
            System.out.println("Jour du dp : " + jourChoisis + "\nJour resa : " + lesReservations.get(i).getJour());
            if(jourChoisis.equals(lesReservations.get(i).getJour()))
            {
                System.out.println("If qui rend true");
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
                int heure = lesReservations.get(1).getHeure().getHours();
                if(heure != i)
                {
                    heures.add(String.valueOf(i));
                }
            }
        }
        cmbHeure.setItems(heures);
    }
    
    
    
    public void remplirSalle()
    {
        ObservableList lesSportsSalles;
        ObservableList DistinctlesSportsSalles = FXCollections.observableArrayList();    
        String pAsso = cmbAsso.getSelectionModel().getSelectedItem().toString();
        DistinctlesSportsSalles = connBDD.getLesSallesParAsso(pAsso);
        cmbSalle.setItems(DistinctlesSportsSalles);
        cmbSalle.getSelectionModel().select(0);
    }
}
