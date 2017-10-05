package manager;

import java.util.ArrayList;
import entities.Establishment;

public class EstablishmentManager {
	
	public static ArrayList<Establishment> getAll(){
		return null;
	}
	//retourne l'etablissement ou il travaille un employ�
	public static Establishment getByIdUser(int idUser){
		return null;
	}
	
	// retourne les etablissement d'un type
	public static ArrayList<Establishment> getBytype(){
		return null;
	}
	
	
	// en cas d'ajout d'un nouveau client restaurateur franchise ou corporate
	public static boolean Insert(Establishment etab){
		return false;
	}
	
	
	// dans le cas ou un franchise devient corporate ou inverse
	public static boolean Update(Establishment etab){
		return false;
	}
}
