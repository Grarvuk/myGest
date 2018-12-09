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
public class Association 
{
    private String refAsso;
    private String ville;
    private String adresse;
    private String nomResponsable;

    public Association
        (String refAsso, String ville, String adresse, String nomResponsable) 
        {
        this.refAsso = refAsso;
        this.ville = ville;
        this.adresse = adresse;
        this.nomResponsable = nomResponsable;
    }
    
    

    public String getRefAsso() 
    {
        return refAsso;
    }

    public void setRefAsso(String prefAsso) 
    {
        this.refAsso = prefAsso;
    }

    public String getVille() 
    {
        return ville;
    }

    public void setVille(String pville) {
        this.ville = pville;
    }

    public String getAdresse() 
    {
        return adresse;
    }

    public void setAdresse(String padresse) 
    {
        this.adresse = padresse;
    }

    public String getNomResponsable() 
    {
        return nomResponsable;
    }

    public void setNomResponsable(String pnomResponsable) 
    {
        this.nomResponsable = pnomResponsable;
    }
    
    public String affiche()
    {
        return ("Référence association : " + this.getRefAsso() + " Ville : " + this.getVille()
                + " Adresse : " + this.getAdresse() + " Nom du responsable : " + this.getNomResponsable());
    }
    
    public String toString()
    {
        return this.getRefAsso();
    }
}
