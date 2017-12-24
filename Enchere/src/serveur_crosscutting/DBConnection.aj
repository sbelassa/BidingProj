package serveur_crosscutting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

import serveur.Objet;
import serveur.VenteImpl;

public aspect DBConnection{

	  Connection conn;
	  
	  /*
		 * un point de jonction qui concerne la mothode main de la class Serveur du package Serveur
		 * le but c'est de séparer l'initialisation la connexion avec la base de donnee du code metier.
	     */
	  
	  pointcut connection() : execution(void serveur.Serveur.main(..));
	  
	  before() : connection(){
	    	try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/enchere_db","root","root"); 
				System.out.println("Connection Done");
			} catch (Exception e) {
				System.out.println("Connection Refused");
			}
	  } 
	  
	  //advice for the object initialisation
	   
	  //avant chaque lecture de l'attribut listeAcheteur dans tous les classes.
	  pointcut listobject() : get(public Stack<Objet> *.listeAcheteurs);
	  
	  before() : listobject(){
		  String q = "select * from objet ;";
	      PreparedStatement stm;
		  try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp_oap","root","root");
			stm = conn.prepareStatement(q);
		    stm.clearParameters();
			ResultSet rst = stm.executeQuery();
			while(rst.next()){
				String name = rst.getString(1);
				String des  = rst.getString(2);
				Integer pb = Integer.valueOf(rst.getString(3));
				Integer pc = (rst.getString(4)!=null) ? Integer.valueOf(rst.getString(4)) : null;
				boolean dis = Boolean.valueOf(rst.getString(5));
				String gangnant = rst.getString(6);
				Objet o = new Objet(name,des,pb,pc,dis,gangnant);
				VenteImpl.listeObjets.push(o);
			}
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
}}
