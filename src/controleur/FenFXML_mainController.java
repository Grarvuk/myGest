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
import java.text.DecimalFormat;
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
import modele.Statistique;
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
    private Statistique stats = new Statistique();
    private double nbCreneau = 0;
    
    @FXML
    GridPane paneAgenda;
    
    @FXML
    ToggleGroup salle;
    
    @FXML
    Label labCreneauRes, labPourcPris, labCreneauPris, labAssoPresente, labSallePlus;
    
    /**
     * Initializes the controller class.
     */
    @Override
    @SuppressWarnings("empty-statement")
    public void initialize(URL url, ResourceBundle rb) 
    {
        salle.getToggles().get(0).setSelected(true);
        afficherPlanning("A");
        definirStats();
        affichageStats();
    }
    
    public void definirStats()
    {
        int[] tabNBsalles = new int[3];
        tabNBsalles = connBDD.getNBsalle();
        stats.setNbreserA(tabNBsalles[0]);
        stats.setNbreserB(tabNBsalles[1]);
        stats.setNbreserC(tabNBsalles[2]);
        
        int lePlus = -1;
        int indexLePlus = -1;
        
        for(int i = 0;i<=2;i++)
        {
            if(lePlus<=tabNBsalles[i])        
            {
                lePlus = tabNBsalles[i];
                indexLePlus = i;
            }
        }
        if(indexLePlus ==0)
        {
            labSallePlus.setText("A");
        }
        else if(indexLePlus ==1)
        {
            labSallePlus.setText("B");
        }
        if(indexLePlus ==2)
        {
            labSallePlus.setText("C");
        }
        
        DecimalFormat df = new DecimalFormat("##.##");
        
        double val = (double) tabNBsalles[0]; 
        double pourc = (val/nbCreneau)*100;
        stats.setPourcA(pourc);
        
        val = (double) tabNBsalles[1];
        pourc = (val/nbCreneau)*100;
        stats.setPourcB(pourc);
        
        val = (double) tabNBsalles[2];
        pourc = (val/nbCreneau)*100;
        stats.setPourcC(pourc);
        
        stats.setAssoA(connBDD.getAssoPlusPresente("A"));
        stats.setAssoB(connBDD.getAssoPlusPresente("B"));
        stats.setAssoC(connBDD.getAssoPlusPresente("C"));
        
        stats.setHeurePlusPriseA(connBDD.getHeurePlusPrise("A"));
        stats.setHeurePlusPriseB(connBDD.getHeurePlusPrise("B"));
        stats.setHeurePlusPriseC(connBDD.getHeurePlusPrise("C"));
        
        System.out.println("nb salle A : " + stats.getNbreserA() + "\npourcentage A : " + stats.getPourcA() +
                "\nasso le plus présent A : " + stats.getAssoA() + "\nheure plus prise A : " + stats.getHeurePlusPriseA());
    }
    
    public void affichageStats()
    {
        DecimalFormat df = new DecimalFormat("##.##");
        String pSalleCochee = "";
        for(int i = 0; i<=salle.getToggles().size()-1;i++)
        {
            if(salle.getToggles().get(i).isSelected() == true)
            {
                RadioButton chk = (RadioButton)salle.getToggles().get(i);
                pSalleCochee = chk.getText();
            }
        }
        if(pSalleCochee.equals("A"))
        {
            labAssoPresente.setText(stats.getAssoA());
            labCreneauPris.setText(stats.getHeurePlusPriseA());
            labCreneauRes.setText(String.valueOf(stats.getNbreserA()));
            labPourcPris.setText(df.format(stats.getPourcA()));
        }
        else if(pSalleCochee.equals("B"))
        {
            labAssoPresente.setText(stats.getAssoB());
            labCreneauPris.setText(stats.getHeurePlusPriseB());
            labCreneauRes.setText(String.valueOf(stats.getNbreserB()));
            labPourcPris.setText(df.format(stats.getPourcB()));
        }
        else if(pSalleCochee.equals("C"))
        {
            labAssoPresente.setText(stats.getAssoC());
            labCreneauPris.setText(stats.getHeurePlusPriseC());
            labCreneauRes.setText(String.valueOf(stats.getNbreserC()));
            labPourcPris.setText(df.format(stats.getPourcC()));
        }
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
        String psalle = "A";
        for(int i = 0; i<=salle.getToggles().size()-1;i++)
        {
            if(salle.getToggles().get(i).isSelected() == true)
            {
                RadioButton chk = (RadioButton)salle.getToggles().get(i);
                psalle = chk.getText();
            }
        }
        MainApp.afficheResa(psalle);
    }
    
    public void afficherPlanning(String pSalle)
    {
        Calendar calendar = Calendar.getInstance();
        
        java.util.Date dd = new java.util.Date();
        
        int jour = dd.getDate();
        DateFormat datef = new SimpleDateFormat("EEEE dd-MM-yyyy", java.util.Locale.FRENCH);
        System.out.println("Jour de la semaine : " + datef.toString());
        String date = datef.format(dd);
        
        java.util.Date now = new java.util.Date();
 
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
        System.out.println("Format court : "+simpleDateformat.format(now));
 
        simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        System.out.println("Format long : "+simpleDateformat.format(now));
        
        
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
                        nbCreneau++;
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
                        
                        simpleDateformat = new SimpleDateFormat("EEEE dd-MM-yyyy"); // the day of the week spelled out completely
                        
        
                        lab = new Label(simpleDateformat.format(tomorrow));
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
        affichageStats();
    }
}
