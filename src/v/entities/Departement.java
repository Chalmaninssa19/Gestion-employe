package v.entities;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;


@DBTable(name = "departement", sequenceName="seq_departement")
public class Departement {
	@DBField(name = "id_departement", isPrimaryKey=true)
	int idDepartement;
	@DBField(name = "nom")
	String nom;


	public Departement(){}
	public Departement(int idDepartement, String nom){
		 this.idDepartement = idDepartement;
		 this.nom = nom;
	}
	public Departement(String nom){
		 this.nom = nom;
	}

	public int getIdDepartement(){
		return this.idDepartement;
	}

	public void setIdDepartement(int idDepartement){
		this.idDepartement = idDepartement;
	}

	public String getNom(){
		return this.nom;
	}

	public void setNom(String nom){
		this.nom = nom;
	}


}
