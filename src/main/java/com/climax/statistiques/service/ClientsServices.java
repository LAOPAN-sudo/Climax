package com.climax.statistiques.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.climax.statistiques.entities.clients;

public interface ClientsServices {

	clients saveclients(clients c);
	clients updateclients(clients c);
	clients readclients(clients c);
	double averagesalary(String profession)throws SQLException;
	public Map<String, Double> allaveragesalary() throws SQLException;
	void deleteclients(clients c);
	void deleclientsById(Integer Id);
	clients getclients(Integer Id);
	List<clients> getAllclients();
	
}
