package com.climax.statistiques.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.climax.statistiques.entities.clients;
import com.climax.statistiques.repository.ClientsRepository;

@Service
public class ClientsServicesImpl implements ClientsServices{

	@Autowired
	ClientsRepository clientsRepository;
	
	@Override
	public clients saveclients(clients c) {
		// TODO Auto-generated method stub
		return clientsRepository.save(c);
	}

	@Override
	public clients updateclients(clients c) {
		// TODO Auto-generated method stub
		return clientsRepository.save(c);
	}

	@Override
	public clients readclients(clients c) {
		// TODO Auto-generated method stub
		return clientsRepository.save(c);
	}

	@Override
	public void deleteclients(clients c) {
		// TODO Auto-generated method stub
		clientsRepository.delete(c);
	}

	@Override
	public void deleclientsById(Integer Id) {
		// TODO Auto-generated method stub
		clientsRepository.deleteById(Id);
	}

	@Override
	public List<clients> getAllclients() {
		// TODO Auto-generated method stub
		return clientsRepository.findAll();
	}

	@Override
	public clients getclients(Integer Id) {
		// TODO Auto-generated method stub
		return clientsRepository.findById(Id).get();
	}

	@Override
	public double averagesalary(String profession) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    double average = 0.0;

	    try {
	        // Créer la connexion à la base de données
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/climax_db", "root", "");
	     
	        // Préparer la requête SQL
	        String sql = "SELECT AVG(salaire) AS average_salary FROM clients WHERE profession = ?";
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, profession);
	        
	     // Exécuter la requête SQL
	        rs = stmt.executeQuery();
	        
	     // Récupérer le salaire moyen de la profession
	        if (rs.next()) {
	            average = rs.getDouble("average_salary");
	        }
	    } finally {
	        // Fermer les ressources JDBC
	        if (rs != null) rs.close();
	        if (stmt != null) stmt.close();
	        if (conn != null) conn.close();
	    }
	    return average;
	       
	}
	
	
	@Override
	public Map<String, Double> allaveragesalary() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    Map<String, Double> averageSalaryByProfession = new HashMap<>();
	    try {
	        // Créer la connexion à la base de données
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/climax_db", "root", "");
	        // Préparer la requête SQL
	        String sql = "SELECT profession, AVG(salaire) AS average_salary FROM clients GROUP BY profession";
	        stmt = conn.prepareStatement(sql);
	        // Exécuter la requête SQL
	        rs = stmt.executeQuery();
	        // Récupérer le salaire moyen de chaque profession
	        while (rs.next()) {
	            String profession = rs.getString("profession");
	            double averageSalary = rs.getDouble("average_salary");
	            averageSalaryByProfession.put(profession, averageSalary);
	        }
	    } finally {
	        // Fermer les ressources JDBC
	        if (rs != null) rs.close();
	        if (stmt != null) stmt.close();
	        if (conn != null) conn.close();
	    }
	    return averageSalaryByProfession;
	}

	

}
