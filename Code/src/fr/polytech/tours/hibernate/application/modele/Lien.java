package fr.polytech.tours.hibernate.application.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Classe representant les liens d'un message, il est caracterise par l'adresse
 * du lien (ici represente par le nom du lien) et par le texte a visualiser pour
 * le lien (ici TexteVisualiser).
 * 
 * @author MOUTAS RIBEIRO et LIN
 *
 */
@Entity
public class Lien {

	/**
	 * Attributs
	 */
	/**
	 * LienID : identifiant du lien.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int LienID;
	/**
	 * NomLien : le nom du lien present dans un message.
	 */
	private String NomLien;
	/**
	 * TexteVisualiser : le texte a visualiser pour un lien.
	 */
	private String TexteVisualiser;
	/**
	 * Message : le message qui contient ce lien. Un message peut avoir
	 * plusieurs liens, mais un lien ne caracterise qu'un seul message.
	 */
	@ManyToOne
	private Message Message;

	/**
	 * Constructeur par default.
	 */
	public Lien() {
		this("", "", new Message());
	}

	/**
	 * Constructeur a deux parametres, qu'initilisent les attributs de la
	 * classe.
	 * 
	 * @param nomLien
	 * @param texteVisualiser
	 */
	public Lien(String nomLien, String texteVisualiser) {
		this(nomLien, texteVisualiser, new Message());
	}

	/**
	 * Constrcuteur a 3 parametres , initilisation des attributs de la classe
	 * par la valeur des parametres passes au constructeur.
	 * 
	 * @param nomLien
	 * @param texteVisualiser
	 * @param message
	 */
	public Lien(String nomLien, String texteVisualiser, Message message) {
		super();
		this.NomLien = nomLien;
		this.TexteVisualiser = texteVisualiser;
		this.Message = message;
	}

	/**
	 * Les accesseurs et muttateurs sont ci-dessous.
	 */
	public int getLienID() {
		return LienID;
	}

	public void setLienID(int lienID) {
		LienID = lienID;
	}

	public String getNomLien() {
		return NomLien;
	}

	public void setNomLien(String nomLien) {
		NomLien = nomLien;
	}

	public String getTexteVisualiser() {
		return TexteVisualiser;
	}

	public void setTexteVisualiser(String texteVisualiser) {
		TexteVisualiser = texteVisualiser;
	}

	public Message getMessage() {
		return Message;
	}

	public void setMessage(Message message) {
		Message = message;
	}
}
