/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Luis
 */
public class SalleSport 
{
    private String Salle;

    public SalleSport(String pSalle) 
    {
        this.Salle = pSalle;
    }
    
    public String getSalle() 
    {
        return Salle;
    }
  
    @Override
    public String toString()
    {
        return this.getSalle();
    }
}
