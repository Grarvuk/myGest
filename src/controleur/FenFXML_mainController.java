/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import modele.Association;
import modele.Reservation;
import modele.connBDD;
/**
 * FXML Controller class
 *
 * @author Luis
 */
public class FenFXML_mainController implements Initializable 
{
    
    private ObservableList<Reservation> lesReservations = FXCollections.observableArrayList();
    private mainApp MainApp;
    
    @FXML
    GridPane paneAgenda;
    
    @FXML
    ToggleGroup salle;
    
    /**
     * Initializes the controller class.
     */
    @Override
    @SuppressWarnings("empty-statement")
    public void initialize(URL url, ResourceBundle rb) 
    {
        salle.getToggles().get(0).setSelected(true);
        afficherPlanning("A");
    }

    public void setMainApp(mainApp pMainApp)
    {
        this.MainApp = pMainApp;
    }
    
    public void lancerAffichagePlanning()
    {
        for(int i = 0; i<=salle.getToggles().size()-1;i++)
        {
            if(salle.getToggles().get(i).isSelected() == true)
            {
                RadioButton chk = (RadioButton)salle.getToggles().get(i);
                paneAgenda.getChildren().clear();
                afficherPlanning(chk.getText());
            }
        }
    }
    
    public void afficheFenReservation() throws IOException
    {
        MainApp.afficheResa();
    }
    
    public void afficherPlanning(String pSalle)
    {
        Calendar calendar = Calendar.getInstance();
        
        java.util.Date dd = new java.util.Date();
        
        int jour = dd.getDate();
        DateFormat datef = new SimpleDateFormat("dd-MM-yyyy");
        String date = datef.format(dd);
        
        
        lesReservations = connBDD.getReservations(pSalle);
        
        int i, j, heure;
        heure = 7;
        ObservableList<Label> labs = FXCollections.observableArrayList();
        int noLab = 0;
        boolean jourDejaPasse = false;
        Label lab;
        
        for (j=0; j<=6;j++)
        {
            for(i=7; i<=21; i++)
            {
        
                    if(i!=7)
                    {
                        lab = new Label(String.valueOf(heure) + "h00 - " + String.valueOf(heure + 1) + "h00");
                        for(int k = 0; k<=lesReservations.size() - 1;k++)
                        {
                            
                            if(lesReservations.get(k).getHeure().getHours() == i && lesReservations.get(k).getJour().getDate() == jour)
                            {
                                lab = new Label(String.valueOf(heure) + "h00 - " + String.valueOf(heure + 1) + "h00 " + lesReservations.get(k).getRefAsso());
                                lab.setBackground(new Background(new BackgroundFill(Color.valueOf("#CCCCCC"), CornerRadii.EMPTY, Insets.EMPTY)));                                
                                jourDejaPasse = true;
                            }
                            else if(jourDejaPasse == false)
                            {
                                lab.setBackground(new Background(new BackgroundFill(Color.valueOf("#45FE20"), CornerRadii.EMPTY, Insets.EMPTY)));
                            }        
                        }
                
                        jourDejaPasse = false;
                    }
                    else
                    {
        
                        java.util.Date today = calendar.getTime();
                        java.util.Date tomorrow = calendar.getTime();
        
                        lab = new Label(tomorrow.toString());
                        calendar.add(Calendar.DAY_OF_YEAR, 1);
        
                    }
                    lab.setMinWidth(300);
                    labs.add(lab);
                    paneAgenda.add(labs.get(noLab), j, i, 1, 1);
                    //paneAgenda.getChildren().remove(labs.get(noLab));
                    noLab++;
        heure++;
        
        }
        heure = 7;
        jour++;
        }
    }
    
    
}
