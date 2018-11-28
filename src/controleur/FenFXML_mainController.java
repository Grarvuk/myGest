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
/**
 * FXML Controller class
 *
 * @author Luis
 */
public class FenFXML_mainController implements Initializable 
{
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

        
        String jours[] = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
        int i, j, heure;
        heure = 8;
        ObservableList<Label> labs = FXCollections.observableArrayList();
        int noLab = 0;
        Label lab;
        
        /*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();*/
        
        // get a calendar instance, which defaults to "now"

        
        for (j=0; j<=6;j++)
        {
        for(i=0; i<=15; i++)
        {
                
                if(i!=1)
                {
                    lab = new Label(String.valueOf(heure) + "h00 - " + String.valueOf(heure + 1) + "h00");
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
                        System.out.println("tomorrow: " + tomorrow);
                        lab = new Label(tomorrow.toString());
                }
                lab.setBackground(new Background(new BackgroundFill(Color.valueOf("#CCCCCC"), CornerRadii.EMPTY, Insets.EMPTY)));
                lab.setMinWidth(300);
                labs.add(lab);
                paneAgenda.add(labs.get(noLab), j, i, 1, 1);
                //paneAgenda.getChildren().remove(labs.get(noLab));
                noLab++;
                heure++;
            
        }
         heure = 8;
        }
    }

    public void setMainApp(mainApp pMainApp)
    {
        this.MainApp = pMainApp;
        //cmbEmp.setValue("Liste employés");
    }
    
}
