package fr.polytech.tours.hibernate.application.modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Classe qui gere les utilisateurs du blog. On peut avoir des informations
 * personnelles d'un utilisateur via cette classe en modifier son nom ou prenom
 * par exemple.
 * 
 * @author MOUTAS RIBEIRO et LIN
 *
 */
@Entity
public class Utilisateur {

	/**
	 * Attributs
	 * 
	 */
	/**
	 * Email: l'attribut est une chaine de caracteres et representant l'email de
	 * l'utilisateur du blog.
	 */
	@Id
	private String Email;
	/**
	 * Nom : attribut representant le nom de l'utlisateur, cet attribut est de
	 * type String (chaine de caracteres).
	 */
	private String Nom;
	/**
	 * Prenom : represente le prenom de l'utilisateur, c'est aussi un attribut
	 * du type chaine de caracteres.
	 */
	private String Prenom;
	/**
	 * Adresse : correspond a l'adresse d'un utilisateur du blog, c'est une
	 * chaine de caracteres.
	 */
	private String Adresse;
	/**
	 * MotDePasse: correspond au mot de passe choisi pour un utilisateur lors de
	 * sa connexion au blog.
	 */
	private String MotDePasse;

	/**
	 * ListeMessages : liste des messages publies dans le blog par cet
	 * utilisateur. Un message est publie par un utilisateur et un utilisateur
	 * peut publier plusieurs messages.
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "Utilisateur")
	private List<Message> ListeMessages = new ArrayList<Message>();

	/**
	 * Constructeur par default.
	 */

	public Utilisateur() {
		this("", "", "", "", "");
	}

	/**
	 * Constructeur a six parametres permettant d'initialiser les attributs de
	 * cette classe en utilisant les valeurs passes en parametre, que voici :
	 * 
	 * @param utilisateurID
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param email
	 * @param motDePasse
	 */
	public Utilisateur(String nom, String prenom, String adresse, String email, String motDePasse) {
		super();
		this.Nom = nom;
		this.Prenom = prenom;
		this.Adresse = adresse;
		this.Email = email;
		this.MotDePasse = motDePasse;
	}

	/**
	 * Redefinition de la methode toString pour l'affichage des attributs Prenom
	 * et Nom de la classe.
	 */
	@Override
	public String toString() {
		return Prenom + "." + Nom;
	}

	/**
	 * Accesseurs et Mutateurs des attributs de cette classe se trouvent
	 * ci-dessous.
	 */

	public int getUtilisateurID() {
		return -1;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getMotDePasse() {
		return MotDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		MotDePasse = motDePasse;
	}

	public List<Message> getListeMessages() {
		return ListeMessages;
	}

	public void setListeMessages(List<Message> listeMessages) {
		ListeMessages = listeMessages;
	}

}
