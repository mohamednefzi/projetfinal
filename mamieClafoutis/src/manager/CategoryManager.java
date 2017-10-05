package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Category;
import service.ConnexionBDD;

public class CategoryManager {
	// les attribut Private static String Query
	private static String queryAll = "select * from categorie";
	private static String queryInsert = "insert into categorie ('denomination') values(?)";

	public static ArrayList<Category> getAll() {
		ArrayList<Category> categorie = null;
		try {
			PreparedStatement ps = ConnexionBDD.getConnection().prepareStatement(queryAll);
			ResultSet result = ps.executeQuery();
			if (result.isBeforeFirst()) {
				categorie = new ArrayList<>();
				while (result.next()) {
					Category c = new Category();
					c.setId(result.getInt("id"));
					c.setNom(result.getString("denomination"));
					categorie.add(c);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}

		return categorie;
	}

	public static boolean InsertCategory(String Category) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getConnection().prepareStatement(queryInsert);
			ps.setString(1, Category);
			retour = ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}

		return retour;
	}

}
