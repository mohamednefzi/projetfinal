package manager;

import java.util.ArrayList;

import entities.Ingredient;
import entities.Product;

public class ProductManager {
	// les attribut Private static String Query
	
	
	public static ArrayList<Product> getAll() {
		return null;
	}

	public static ArrayList<Product> getByCategoryId(int id) {
		return null;
	}

	public static ArrayList<Product> getByName(String name) {
		return null;
	}
	
	public static Product getById(int id){
		return null;
	}
	
	public static String getCategoryNameById(int id){
		return null;
	}
	
	public static boolean Insert(Product item){
		return false;
	}
// une methode qui utilise la meme query qui modifie tout les champs d'un produit meme s'il y a une seule colonne � modifier	
	public static boolean Update (Product item){
		return false;
	}
	
	public static ArrayList<Ingredient> getAllIngredientById(int idProduct){
		return null;
	}
	
	// dans le cas ou la recette est un textarea on retourne un String si l recette est compos� de plusieurs lignes on retourne Al<Receipe> ou on ouvre un fichier editable 
	public static String getReceipeById(int idProduct){
		return null;
	}
	
	
	//modifier les quantites des ingredients d'un produit  par unite
	public static boolean UpdateIngeredientById(int idProduct,Ingredient ingerdient){
		return false;
	}
	
	// supprimer un ingredient de la table ingredient des produits
	public static boolean  removeIngredientById(int idProduct, Ingredient ingredient){
		return false;
	}
	
	//inserer un ingredient de la table  ingredient des produits
	public static boolean InsertIngredientById(int idProduct, ArrayList<Ingredient> ingeredients){
		return false;
	}
}
