/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.Dimension;
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
import javafx.scene.control.TextField;
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    @SuppressWarnings("empty-statement")
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        lesReservations = connBDD.getReservations();
        
        String jours[] = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
        int i, j, heure;
        heure = 7;
        ObservableList<Label> labs = FXCollections.observableArrayList();
        int noLab = 0;
        Label lab;
        
        for (j=0; j<=6;j++)
        {
        for(i=0; i<=13; i++)
        {
                
                if(i!=0)
                {
                    lab = new Label(String.valueOf(heure) + "h00 - " + String.valueOf(heure + 1) + "h00");
                    for(int k = 0; k<=lesReservations.size() - 1;k++)
                    {
                        if(lesReservations.get(k).getHeure() == heure)
                        {
                            lab.setBackground(new Background(new BackgroundFill(Color.valueOf("#CCCCCC"), CornerRadii.EMPTY, Insets.EMPTY)));
                        }
                        else
                        {
                            lab.setBackground(new Background(new BackgroundFill(Color.valueOf("#45FE20"), CornerRadii.EMPTY, Insets.EMPTY)));
                        }
                        System.out.println("Heure de réservation : " + lesReservations.get(k).getHeure() + "\n heure : " + heure);
                    }
                }
                else
                {
                        Calendar calendar = Calendar.getInstance();
    
                        // get a date to represent "today"
                        java.util.Date today = calendar.getTime();
                        //System.out.println("today:    " + today);
 
                        // add one day to the date/calendar
                        calendar.add(Calendar.DAY_OF_YEAR, j);
    
                        // now get "tomorrow"
                        java.util.Date tomorrow = calendar.getTime();

                        // print out tomorrow's date
                        lab = new Label(tomorrow.toString());
                }
                lab.setMinWidth(300);
                labs.add(lab);
                paneAgenda.add(labs.get(noLab), j, i, 1, 1);
                //paneAgenda.getChildren().remove(labs.get(noLab));
                noLab++;
                heure++;
            
        }
         heure = 7;
        }
    }

    public void setMainApp(mainApp pMainApp)
    {
        this.MainApp = pMainApp;
        //cmbEmp.setValue("Liste employés");
    }
    
}
