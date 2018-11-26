/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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
        String mot = "f";
        int i, j;
        Label lab;
        for (j=0; j<=2;j++)
        {
        for( i = 0; i<=5; i++)
        {
            lab = new Label(mot);
            paneAgenda.add(lab, j, i, 1, 1);
            mot += "f";
        }
        }
    }

    public void setMainApp(mainApp pMainApp)
    {
        this.MainApp = pMainApp;
        //cmbEmp.setValue("Liste employés");
    }
    
}
