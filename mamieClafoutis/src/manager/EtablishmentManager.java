package manager;

import java.util.ArrayList;

import entities.Etablishment;

public class EtablishmentManager {
	
	public static ArrayList<Etablishment> getAll(){
		return null;
	}
	//retourne l'etablissement ou il travaille un employé
	public static Etablishment getByIdUser(int idUser){
		return null;
	}
	
	// retourne les etablissement d'un type
	public static ArrayList<Etablishment> getBytype(){
		return null;
	}
	
	
	// en cas d'ajout d'un nouveau client restaurateur franchise ou corporate
	public static boolean Insert(Etablishment etab){
		return false;
	}
	
	
	// dans le cas ou un franchise devient corporate ou inverse
	public static boolean Update(Etablishment etab){
		return false;
	}
}
