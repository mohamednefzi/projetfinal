package manager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entities.User;

public class UserManager
{
	private static String queryUser= = "select * from utilisateur where userName = ? and password = ?";
	private static String queryAll = "select * from utilisateur where id = ?";
	private static String queryInsert = "insert into utilisateur ('id', 'nom', 'prenom', 'etablisement_id', 'role_id', 'userName', 'password') values(?,?,?,?,?,?,?)";
	private static String queryUpdate = "update utilisateur set id = ?, nom = ?, prenom = ?, etablisement_id = ?, role_id = ?, userName = ?, password = ?;
	  
	public static User getUser(String login, String pwd)
	{
		ArrayList<User> user = null;

		try
		{
			PreparedStatement ps = ConnexionBDD.getPs(queryUser);
			ps.setInt(1,login);
			ps.setInt(2,pwd);
			ResultSet result = ps.executeQuery();
			
			if (result != null) user = new ArrayList<>();
			
			while (result.next())
			{
				user u = new User();
				u.setId(result.getInt("id"));
				u.setLastName(result.getString("nom"));
				u.setFirstName(result.getString("prenom"));
				u.setEstablishmentId(result.getInt("etablisement_id"));
				u.setRoleId(result.getInt("role_id"));
				u.setUsername(result.getString("userName"));
				u.setPassword(result.getString("password"));
				user.add(u);
			}
		}
		
		catch (SQLException e) { e.printStackTrace(); }
		
		finally { ConnexionBDD.closeConnection(); }
		
		return user;	
	}

	public boolean verifyUser(String login, String pwd)
	{
		if(getUser(login,pwd) != null) return true;
		
		return false;
	}

	public User getAllInformationById(int id)
	{
		ArrayList<User> user = null;

		try
		{
			PreparedStatement ps = ConnexionBDD.getPs(queryAll);
			ps.setInt(1,id);
			ResultSet result = ps.executeQuery();
			
			if (result != null) user = new ArrayList<>();
			
			while (result.next())
			{
				user u = new User();
				u.setId(result.getInt("id"));
				u.setLastName(result.getString("nom"));
				u.setFirstName(result.getString("prenom"));
				u.setEstablishmentId(result.getInt("etablisement_id"));
				u.setRoleId(result.getInt("role_id"));
				u.setUsername(result.getString("userName"));
				u.setPassword(result.getString("password"));
				user.add(u);
			}
		}
		
		catch (SQLException e) { e.printStackTrace(); }
		
		finally { ConnexionBDD.closeConnection(); }
		
		return user;		
	}

	// a l'inscription on appelle insert user
	public static boolean Insert(User newUser)
	{
		boolean retour = false;
		try
		{
			PreparedStatement ps = ConnexionBDD.getPs(queryInsert);
			ps.setInt(1,newUser.getId());
			ps.setString(2,newUser.getLastName());
			ps.setString(3,newUser.getFirstName());
			ps.setString(4,newUser.getEstablishmentId());
			ps.setInt(5,newUser.getRoleId());
			ps.setInt(6,newUser.getUsername());
			ps.setInt(7,newUser.getPassword());
			retour = ps.execute();
		}
		
		catch (SQLException e) { e.printStackTrace(); }
		
		finally { ConnexionBDD.closeConnection(); }

		return retour;
	}

	// mis a jour d'un employé type de fonction 
	//ou retirer de la liste avec un boolean
	public static boolean Update(User user)
	{
		int nbrUpdate = 0;
		boolean retour = false;
		try
		{
			PreparedStatement ps = ConnexionBDD.getPs(queryUpdate);
			ps.setInt(1,newUser.getId());
			ps.setString(2,newUser.getLastName());
			ps.setString(3,newUser.getFirstName());
			ps.setString(4,newUser.getEstablishmentId());
			ps.setInt(5,newUser.getRoleId());
			ps.setInt(6,newUser.getUsername());
			ps.setInt(7,newUser.getPassword());
			nbrUpdate = ps.executeUpdate();
			
			if (nbrUpdate > 0) retour = true;
		}
		
		catch (SQLException e) { e.printStackTrace(); }
		
		finally { ConnexionBDD.closeConnection(); }

		return retour;
	}

}