package com.climax.statistiques.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class clients {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer idclient;
	private String nompartenaire;
	private String nomclient;
	private String prenomclient;
	private Integer age;
	private String profession;
	private Double salaire;
	
	
	
	
	
	
	public clients() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public clients(String nompartenaire, String nomclient, String prenomclient, Integer age, String profession,
			Double salaire) {
		super();
		this.nompartenaire = nompartenaire;
		this.nomclient = nomclient;
		this.prenomclient = prenomclient;
		this.age = age;
		this.profession = profession;
		this.salaire = salaire;
	}



	public Integer getIdclient() {
		return idclient;
	}
	public void setIdclient(Integer idclient) {
		this.idclient = idclient;
	}
	public String getNompartenaire() {
		return nompartenaire;
	}
	public void setNompartenaire(String nompartenaire) {
		this.nompartenaire = nompartenaire;
	}
	public String getNomclient() {
		return nomclient;
	}
	public void setNomclient(String nomclient) {
		this.nomclient = nomclient;
	}
	public String getPrenomclient() {
		return prenomclient;
	}
	public void setPrenomclient(String prenomclient) {
		this.prenomclient = prenomclient;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public Double getSalaire() {
		return salaire;
	}
	public void setSalaire(Double salaire) {
		this.salaire = salaire;
	}



	@Override
	public String toString() {
		return "clients [idclient=" + idclient + ", nompartenaire=" + nompartenaire + ", nomclient=" + nomclient
				+ ", prenomclient=" + prenomclient + ", age=" + age + ", profession=" + profession + ", salaire="
				+ salaire + "]";
	}
	
	
	
	
	
}
