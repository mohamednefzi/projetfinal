package manager;

import java.util.ArrayList;
import java.util.Date;

import entities.Sales;

public class SalesManager {
	
	// les attributs static private String query
	
	public static ArrayList<Sales> getAll(){
		return null;
	}
	
	
	// on passe un tableau qui prend une valeur ou plus car on veut permettre a l'admin d'afficher un ou plusieur etablissements
	// on peut ajouter les orderBy Date ou produit cette methode affiche toutes les ventes de chaque jour
	public static ArrayList<Sales> getByIdEtablishement(int[] idEtablishement, Date dateFirst,Date dateEnd){
		
		return null;
	}
	// une methode qui affiche un groupement de vente pour un produit donn�e durant une date donn�e 
	public static ArrayList<Sales> getStatByEtablishement(int[] idEtablishement, Date dateFirst,Date dateEnd){
		
		return null;
	}
	// l'insertion des ventes des vendeur si le produit n'est pas existant pendant l'heure qui precede
	public static boolean Insert(int idProduct,int idUser,int qty,boolean isOutOfStock){
		return false;
	}

// update d'une vente existante si le produit a �t� entrer par un utilisateur donn�e pendant lheure qui pr�c�de
	
	public static boolean Update(int idVente, int qty, boolean isOutOfStock){
		return false;
	}
	
	// verifie si un produit donn�e a �t� ajouter a dans la derniere heure s'il existe on appelle update si -1 on appelle insert
	public static int isAddedInLastHour(int idProduct,int idUser){
		int idVente=-1;
		return idVente;
	}
	
	
}
