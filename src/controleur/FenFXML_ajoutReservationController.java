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
import static javafx.collections.FXCollections.observableList;
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
    ComboBox cmbAsso, cmbSport, cmbSalle;
    @FXML
    DatePicker dpJourRese;
    mainApp MainApp;
    
    ObservableList <Association> lesAssociations;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        
        
    }


    public void remplirLesCBM(ObservableList <Association> pLesAssociations)
    {
       ObservableList lesSalles = FXCollections.observableArrayList();
       
       
       lesAssociations = pLesAssociations;
       cmbAsso.setItems(lesAssociations);
       cmbAsso.getSelectionModel().select(1);
       remplirSalleSport();
    }
    
    public void remplirSalleSport()
    {
        ObservableList lesSportsSalles;
        ObservableList DistinctlesSportsSalles = FXCollections.observableArrayList();
        
        String pAsso = cmbAsso.getSelectionModel().getSelectedItem().toString();
        lesSportsSalles = connBDD.getLesSallesParAsso(pAsso);
        
        
        cmbSalle.setItems(lesSportsSalles);
        cmbSalle.getSelectionModel().select(0);
        
        lesSportsSalles = connBDD.getLesSportsParAsso(pAsso);
        cmbSport.setItems(lesSportsSalles);
        cmbSport.getSelectionModel().select(0);
    }
    
}
