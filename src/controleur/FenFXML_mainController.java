/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.Dimension;
import java.net.URL;
import java.util.ResourceBundle;
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
        Label lab;
        for (j=0; j<=6;j++)
        {
        for(i=1; i<=15; i++)
        {
                lab = new Label(String.valueOf(heure) + "h00 - " + String.valueOf(heure + 1) + "h00");
                lab.setBackground(new Background(new BackgroundFill(Color.valueOf("#CCCCCC"), CornerRadii.EMPTY, Insets.EMPTY)));
                lab.setMinWidth(300);
                paneAgenda.add(lab, j, i, 1, 1);
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
