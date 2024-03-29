package v.entities;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;

import java.time.LocalDate;

@DBTable(name = "employe", sequenceName="seq_employe")
public class Employe {
	@DBField(name = "id_employe", isPrimaryKey=true)
	int idEmploye;
	@DBField(name = "nom")
	String nom;
	@DBField(name = "prenom")
	String prenom;
	@DBField(name = "dtn")
	LocalDate dtn;
	@DBField(name = "id_departement", isForeignKey=true)
	Departement departement;

	public Employe(){}
	public Employe(int idEmploye,  String nom, String prenom, LocalDate dtn, Departement departement){
		 this.departement = departement;
		 this.nom = nom;
		 this.prenom = prenom;
		 this.idEmploye = idEmploye;
		 this.dtn = dtn;
	}
	public Employe(String nom, String prenom, LocalDate dtn, Departement departement){
		 this.departement = departement;
		 this.nom = nom;
		 this.prenom = prenom;
		 this.dtn = dtn;
	}

	public Departement getDepartement(){
		return this.departement;
	}

	public void setDepartement(Departement departement){
		this.departement = departement;
	}

	public String getNom(){
		return this.nom;
	}

	public void setNom(String nom){
		this.nom = nom;
	}

	public String getPrenom(){
		return this.prenom;
	}

	public void setPrenom(String prenom){
		this.prenom = prenom;
	}

	public int getIdEmploye(){
		return this.idEmploye;
	}

	public void setIdEmploye(int idEmploye){
		this.idEmploye = idEmploye;
	}

	public LocalDate getDtn(){
		return this.dtn;
	}

	public void setDtn(LocalDate dtn){
		this.dtn = dtn;
	}


}
