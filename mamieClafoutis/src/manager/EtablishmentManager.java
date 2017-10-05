package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Etablishment;

public class EtablishmentManager
{
	private static String queryAll = "select * from etablisement";
	private static String queryUser = "select etablisement_id from utilisateur where id = ?"; 
	private static String queryType = "select * from etablisement where type_id = (select id from type where denomination = ?)";
	private static String queryInsert = "insert into etablisement ('name') values(?)";
	private static String queryUpdate = "update etablisement set id=?, name=?, adresse=?, tel=?, type_id=? where id=?";

	public static ArrayList<Etablishment> getAll()
	{
		ArrayList<Etablishment> etablishment = null;
		try
		{
			PreparedStatement ps = ConnexionBDD.getPs(queryAll);
			ResultSet result = ps.executeQuery();
			
			if (result.isBeforeFirst())
			{
				etablishment = new ArrayList<>();
				while (result.next())
				{
					Etablishment e = new Etablishment();
					e.setId(result.getInt());
					e.setNom(result.getString());
					etablishment.add(e);
				}
			}
		}
		
		catch (SQLException e) { e.printStackTrace(); }
		
		finally { ConnexionBDD.closeConnection(); }

		return etablishment;
	}
	
	// Retourne l'etablissement ou travaille un employé
	public static Etablishment getByIdUser(int idUser)
	{
		ArrayList<Product> etablishment = null;

		try
		{
			PreparedStatement ps = ConnexionBDD.getPs(queryUser);
			ps.setInt(1,idUser);
			ResultSet result = ps.executeQuery();
			
			if (result != null)
				etablishment = new ArrayList<>();
			
			while (result.next())
			{
				Etablishment e = new Etablishment();
				e.setId(result.getInt());
				e.setNom(result.getString());
				etablishment.add(e);
			}
		}
		
		catch (SQLException e) { e.printStackTrace(); }
		
		finally { ConnexionBDD.closeConnection(); }
		
		return etablishment;
	}
	
	// Retourne les etablissements d'un type
	public static ArrayList<Etablishment> getBytype(String type)
	{
		ArrayList<Product> etablishment = null;

		try
		{
			PreparedStatement ps = ConnexionBDD.getPs(queryType);
			ps.setString(1,type);
			ResultSet result = ps.executeQuery();
			
			if (result != null)
				etablishment = new ArrayList<>();
			
			while (result.next())
			{
				Etablishment e = new Etablishment();
				e.setId(result.getInt("id"));
				e.setNom(result.getString("denomination"));
				etablishment.add(e);
			}
		}
		
		catch (SQLException e) { e.printStackTrace(); }
		
		finally { ConnexionBDD.closeConnection(); }
		
		return etablishment;
	}
	
	
	// En cas d'ajout d'un nouveau client restaurateur franchise ou corporate
	public static boolean Insert(Etablishment etab)
	{
		boolean retour = false;
		try
		{
			PreparedStatement ps = ConnexionBDD.getPs(queryInsert);
			ps.setInt(1,etab.getId());
			ps.setString(2,etab.getNom());
			ps.setString(3,etab.getAdresse());
			ps.setString(4,etab.getTel());
			ps.setInt(5,etab.getType());
			retour = ps.execute();
			ps.ex
		}
		
		catch (SQLException e) { e.printStackTrace(); }
		
		finally { ConnexionBDD.closeConnection(); }

		return retour;
	}
	
	
	// Dans le cas ou un franchise devient corporate ou inverse
	public static boolean Update(Etablishment etab)
	{
		int nbrUpdate = 0;
		boolean retour = false;
		try
		{
			PreparedStatement ps = ConnexionBDD.getPs(queryUpdate);
			ps.setInt(1,etab.getId());
			ps.setString(2,etab.getNom());
			ps.setString(3,etab.getAdresse());
			ps.setString(4,etab.getTel());
			ps.setInt(5,etab.getType());
			nbrUpdate = ps.executeUpdate();
			if (nbrUpdate > 0) retour = true;
		}
		
		catch (SQLException e) { e.printStackTrace(); }
		
		finally { ConnexionBDD.closeConnection(); }

		return retour;
	}
}