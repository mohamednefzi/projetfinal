package entities;

public class Etablishment
{
	private int id;
	private String nom;
	private String adresse;
	private String tel;
	private int type;
	
	// Getters
	public int getId() { return this.id; }
	
	public String getNom() { return this.nom; }
	
	public String getAdresse() { return this.adresse; }
	
	public String getTel() { return this.tel; }
	
	public int getType() { return this.type; }
	
	// Setters
	public void setId(int id) { this.id = id; }
	
	public void setNom(String nom) { this.nom = nom; }
	
	public void setAdresse(String adresse) { this.adresse = adresse; }

	public void setTel(String tel) { this.tel = tel; }
	
	public void setType(int type) { this.type = type; }
}