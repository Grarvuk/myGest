/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Rabelais
 */
public class connBDD 
{

    public static void connexion()
    {
        Connection conn;
	Statement stmt;
	ResultSet rs;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = "jdbc:mysql://localhost/employe";	
        
        //ObservableList<Employe> lesEmployes = FXCollections.observableArrayList();
        
	try
	{
		Class.forName(pilote);
		conn = DriverManager.getConnection(url,"root","");
		stmt = (Statement) conn.createStatement();			            
		rs = stmt.executeQuery("select * from emp");	
                
                
		while (rs.next())
		{
                    
                    // Employe unEmploye = new Employe(rs.getInt("empo"), rs.getInt("deptno"), rs.getString("ename"), rs.getString("job"), rs.getString("hiredate"), rs.getDouble("sal"));
                    
                    //lesEmployes.add(unEmploye);
		}           			            
		rs.close();
		stmt.close();
		conn.close();
                
                //return lesEmployes;
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
        //return lesEmployes;
    }
    
    
}
