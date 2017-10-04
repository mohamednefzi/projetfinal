package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Ingredient;
import entities.Product;
import service.ConnexionBDD;

public class ProductManager {
	// les attribut Private static String Query
	private static String queryAll = "select * from produit";
	private static String queryByIdCategory = "select * from produit where categorie_id=?";
	private static String queryByName = "select * from produit where nom=?";
	private static String queryById = "select * from produit where id=?";
	private static String queryNameCategoryById = "select c.denomination as nomCategorie from produit as p inner join categorie as c on c.id=p.categorie_id where p.id=?";
	private static String queryInsert = "insert into produit ('nom','categorie_id','ref','description','prix','urlImage') values(?,?,?,?,?,?)";
	private static String queryUpdate = "update produit set nom=? ,categorie_id=?, description=?,prix=?, urlImage=? where id=?";
	private static String queryIngredientById = "select I.id as id,I.denomination as nom,LI.qantite as qte,I.ref as ref,LI.unite as unite from produit as P inner join ligne_ingredient as LI on P.id=LI.produit_id inner join ingredient I on LI.ingredient_id=I.id where p.id=?";
	private static String queryRecipeById="select * from recette where produit_id=?";
	private static String queryUpdateIngredientByID="update ligne_ingredient set quantite=?,unite=? where produit_id=?";
	private static String queryDeleteLineIngedientByID="delete from ligne_ingredient where product_id=? and ingredient_id=?";
	private static String queryInsertListIngredientByID="insert into ligne_ingredient ('produit_id','ingredient_id','quantite','unite') values (?,?,?,?)";
	
	
	// retourner tout les produit de la table produit
	public static ArrayList<Product> getAll() {
		ArrayList<Product> retour = null;

		try {
			PreparedStatement ps = ConnexionBDD.getConnection().prepareStatement(queryAll);
			ResultSet result = ps.executeQuery();
			if (result != null)
				retour = new ArrayList<>();
			while (result.next()) {
				Product p = new Product();
				p.setId(result.getInt("id"));
				p.setName(result.getString("nom"));
				p.setDescription(result.getString("description"));
				p.setPrice(result.getFloat("prix"));
				p.setUrlImage(result.getString("urlImage"));
				retour.add(p);
			}
			ConnexionBDD.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retour;
	}

	// retourner tout les produit d'une categorie d'un id donnee
	public static ArrayList<Product> getByCategoryId(int id) {
		ArrayList<Product> retour = null;

		try {
			PreparedStatement ps = ConnexionBDD.getConnection().prepareStatement(queryByIdCategory);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if (result != null)
				retour = new ArrayList<>();
			while (result.next()) {
				Product p = new Product();
				p.setId(result.getInt("id"));
				p.setName(result.getString("nom"));
				p.setDescription(result.getString("description"));
				p.setPrice(result.getFloat("prix"));
				p.setUrlImage(result.getString("urlImage"));
				retour.add(p);
			}
			ConnexionBDD.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retour;
	}

	// retourner tout les produit avec un nom "name"
	public static ArrayList<Product> getByName(String name) {
		ArrayList<Product> retour = null;

		try {
			PreparedStatement ps = ConnexionBDD.getConnection().prepareStatement(queryByName);
			ps.setString(1, name);
			ResultSet result = ps.executeQuery();
			if (result != null)
				retour = new ArrayList<>();
			while (result.next()) {
				Product p = new Product();
				p.setId(result.getInt("id"));
				p.setName(result.getString("nom"));
				p.setDescription(result.getString("description"));
				p.setPrice(result.getFloat("prix"));
				p.setUrlImage(result.getString("urlImage"));
				retour.add(p);
			}
			ConnexionBDD.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retour;

	}

	// retourner un produit
	public static Product getById(int id) {
		Product retour = null;

		try {
			PreparedStatement ps = ConnexionBDD.getConnection().prepareStatement(queryById);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if (result != null)
				retour = new Product();
			if (result.next()) {
				Product p = new Product();
				p.setId(result.getInt("id"));
				p.setName(result.getString("nom"));
				p.setDescription(result.getString("description"));
				p.setPrice(result.getFloat("prix"));
				p.setUrlImage(result.getString("urlImage"));

			}
			ConnexionBDD.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retour;
	}

	// retourner le nom d'une categorie d'une produit de cle id
	public static String getCategoryNameById(int id) {
		String retour = null;

		try {
			PreparedStatement ps = ConnexionBDD.getConnection().prepareStatement(queryNameCategoryById);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if (result != null)

				if (result.next()) {
					retour = result.getString("nomCategorie");
				}
			ConnexionBDD.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retour;

	}

	public static boolean Insert(Product item) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getConnection().prepareStatement(queryInsert);
			ps.setString(1, item.getName());
			ps.setInt(2, item.getIdCategroy());
			ps.setString(3, item.getRef());
			ps.setString(4, item.getDescription());
			ps.setFloat(5, item.getPrice());
			ps.setString(6, item.getUrlImage());
			retour = ps.execute();

			ConnexionBDD.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retour;
	}

	// une methode qui utilise la meme query qui modifie tout les champs d'un
	// produit meme s'il y a une seule colonne à modifier
	public static boolean Update(Product item) {
		int nbrUpdate = 0;
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getConnection().prepareStatement(queryUpdate);
			ps.setString(1, item.getName());
			ps.setInt(2, item.getIdCategroy());
			ps.setString(3, item.getDescription());
			ps.setFloat(4, item.getPrice());
			ps.setString(5, item.getUrlImage());
			ps.setInt(6, item.getId());
			nbrUpdate = ps.executeUpdate();
			if (nbrUpdate > 0)
				retour = true;

			ConnexionBDD.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}

	public static ArrayList<Ingredient> getAllIngredientById(int idProduct) {
		ArrayList<Ingredient> retour = null;
		try {
			PreparedStatement ps = ConnexionBDD.getConnection().prepareStatement(queryIngredientById);
			ps.setInt(1, idProduct);
			ResultSet result = ps.executeQuery();
			if (result != null)
				retour = new ArrayList<>();
			while (result.next()) {
				Ingredient I = new Ingredient();
				I.setId(result.getInt("id"));
				I.setNom(result.getString("nom"));
				I.setQte(result.getFloat("qte"));
				I.setRef(result.getString("ref"));
				I.setUnite(result.getString("unite"));
				retour.add(I);
			}
			ConnexionBDD.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}

	// dans le cas ou la recette est un textarea on retourne un String si l
	// recette est composé de plusieurs lignes on retourne Al<Receipe> ou on
	// ouvre un fichier editable
	public static String getRecipeById(int idProduct) {
	String recette=null;
	
		try {
			PreparedStatement ps=ConnexionBDD.getConnection().prepareStatement(queryRecipeById);
			ps.setInt(1, idProduct);
			ResultSet result=ps.executeQuery();
			if(result.isBeforeFirst())
				if(result.next())
					recette=result.getString("url_recette");
			ConnexionBDD.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recette;
	}

	// modifier les quantites des ingredients d'un produit par unite
	public static boolean UpdateIngeredientById(int idProduct, Ingredient ingerdient) {
		boolean retour=false;
		try {
			PreparedStatement ps=ConnexionBDD.getConnection().prepareStatement(queryUpdateIngredientByID);
			ps.setFloat(1, ingerdient.getQte());
			ps.setString(2, ingerdient.getUnite());
			ps.setInt(3, idProduct);
			int nbUpdate=ps.executeUpdate();
			if(nbUpdate>0)
				retour=true;
			
			ConnexionBDD.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}

	// supprimer un ingredient de la table ingredient des produits
	public static boolean removeIngredientById(int idProduct, int idIngredient) {
		boolean retour=false;
		try {
			PreparedStatement ps=ConnexionBDD.getConnection().prepareStatement(queryDeleteLineIngedientByID);
			ps.setInt(1, idProduct);
			ps.setInt(2, idIngredient);
			retour=ps.execute();
			
			ConnexionBDD.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}

	// inserer un ingredient de la table ingredient des produits
	public static boolean InsertIngredientById(int idProduct, ArrayList<Ingredient> ingeredients) {
		boolean retour=false;
		int nbInsert=0;
		try {
			for(Ingredient i:ingeredients){
			PreparedStatement ps=ConnexionBDD.getConnection().prepareStatement(queryInsertListIngredientByID);
			ps.setInt(1, idProduct);
			ps.setInt(2, i.getId());
			ps.setFloat(3, i.getQte());
			ps.setString(4, i.getUnite());
			if(ps.execute())
				nbInsert++;
			}
			if(nbInsert==ingeredients.size())
				retour=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
}
