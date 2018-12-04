/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Rabelais
 */
public class Reservation 
{
    private String refSalle;
    private String refAsso;
    private Date jour;
    private Time heure;

    public Reservation(String pRefAsso, String pRefSalle, Date pJour, Time pHeure)
    {
        this.refSalle = pRefSalle;
        this.refAsso = pRefAsso;
        this.jour = pJour;
        this.heure = pHeure;
    }
    
    

    public String getRefSalle()
    {
        return refSalle;
    }

    public void setRefSalle(String pRefSalle)
    {
        this.refSalle = pRefSalle;
    }

    public String getRefAsso()
    {
        return refAsso;
    }

    public void setRefAsso(String pRefAsso)
    {
        this.refAsso = pRefAsso;
    }

    public Date getJour()
    {
        return jour;
    }

    public void setJour(Date pJour)
    {
        this.jour = pJour;
    }

    public Time getHeure()
    {
        return heure;
    }
    
    
    
   public String affiche()
   {
       return this.getRefAsso() + " Dans la salle " + this.getRefSalle() + " le " + this.getJour() + " à " + this.getHeure();
   }
   
   
    public int getDD()
    {
        return this.getJour().getDay();
    }
    
}
