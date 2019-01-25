package fr.polytech.tours.hibernate.application.modele;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * 
 * Classe qui gere les mots cles qui caracterisent un message qui sont publies
 * par un utilisateur.
 * 
 * @author MOUTAS RIBEIRO et LIN
 *
 */
@Entity
public class MotCle {
	/**
	 * MotCleID: atribut representant l'identifiant du mot cle, cet attribut est
	 * genere automatiquement.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int MotCleID;
	/**
	 * MotCle : est un attribut de type string, correspond au mot cle lui-meme.
	 */
	private String MotCle;
	/**
	 * ListeMessages : liste qui contient toutes les messages lui correspondant.
	 * C'est-a-dire que chaque message est cree en entrant des mots cles lui
	 * correspondant, donc chaque message a plusieurs mots cles lui
	 * correspondant, et chaque mot cle peut representer un ou plusieurs
	 * messages.
	 */
	@ManyToMany(mappedBy = "ListeMotCle")
	private List<Message> ListeMessages;

	/**
	 * Constructeur par default.
	 */
	public MotCle() {
		MotCleID = -1;
		MotCle = "";
		ListeMessages = null;
	}

	/**
	 * Constructeur a un parametre.
	 * 
	 * @param motCle
	 */
	public MotCle(String motCle) {
		MotCleID = -1;
		MotCle = motCle;
		ListeMessages = null;
	}

	/**
	 * Constructeur a deux parametres.
	 * 
	 * @param motCle
	 * @param listeMessages
	 */
	public MotCle(String motCle, List<Message> listeMessages) {
		MotCleID = -1;
		MotCle = motCle;
		ListeMessages = listeMessages;
	}

	/**
	 * Redefinition de la methode toString pour l'affichage de l'attribut
	 * MotCle.
	 */
	@Override
	public String toString() {
		return MotCle;
	}

	/**
	 * Les mutateurs et accesseurs se trouvent ci dessous.
	 */
	
	public int getMotCleID() {
		return MotCleID;
	}

	public void setMotCleID(int motCleID) {
		MotCleID = motCleID;
	}

	public String getMotCle() {
		return MotCle;
	}

	public void setMotCle(String motCle) {
		MotCle = motCle;
	}

	public List<Message> getListeMessages() {
		return ListeMessages;
	}

	public void setListeMessages(List<Message> listeMessages) {
		ListeMessages = listeMessages;
	}

}
