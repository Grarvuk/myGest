/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Rabelais
 */
public class connBDD 
{
    
    public static ObservableList<Association> getAssociations()
    {
        Connection conn;
	Statement stmt;
	ResultSet rs;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = "jdbc:mysql://localhost/gymnase";	
        
        ObservableList<Association> lesAssociations = FXCollections.observableArrayList();
        
	try
	{
		Class.forName(pilote);
		conn = DriverManager.getConnection(url,"root","");
		stmt = (Statement) conn.createStatement();			            
		rs = stmt.executeQuery("select * from association");	
                
                
		while (rs.next())
		{
                    
                    Association unAssociation = new Association(rs.getString("refAsso"), rs.getString("ville"), rs.getString("adresse"), rs.getString("nomResponsable"));
                    
                    lesAssociations.add(unAssociation);
		}           			            
		rs.close();
		stmt.close();
		conn.close();
                
                return lesAssociations;
	}			        
	catch (SQLException E)
	{
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:   " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
	}
	catch (ClassNotFoundException e)
	{
            System.out.println("ERREUR Driver " + e.getMessage());
	} 
        return lesAssociations;
    }
    
    public static ObservableList<Reservation> getReservations()
    {
        Connection conn;
	Statement stmt;
	ResultSet rs;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = "jdbc:mysql://localhost/gymnase";	
        
        ObservableList<Reservation> lesReservations = FXCollections.observableArrayList();
        
	try
	{
		Class.forName(pilote);
		conn = DriverManager.getConnection(url,"root","");
		stmt = (Statement) conn.createStatement();			            
		rs = stmt.executeQuery("select * from reservation");	
                
		while (rs.next())
		{
                    
                    Reservation unReservation = new Reservation(rs.getString("refAsso"), rs.getString("refSalle"), rs.getDate("date"), rs.getTime("heure"));
                    
                    lesReservations.add(unReservation);
		}           			            
		rs.close();
		stmt.close();
		conn.close();
                
                return lesReservations;
	}			        
	catch (SQLException E)
	{
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:   " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
	}
	catch (ClassNotFoundException e)
	{
            System.out.println("ERREUR Driver " + e.getMessage());
	} 
        return lesReservations;
    }
    
    public static int[] getHorrairesPris(ObservableList<Reservation> pLesReservations)
    {
        int[] lesHeures = new int[pLesReservations.size()];
        int i = 0;
        pLesReservations.forEach((tab) -> 
        { 
            calendar.setTime(tab.getHeure());
            
        });
        
        return lesHeures;
        
    }
    
}
