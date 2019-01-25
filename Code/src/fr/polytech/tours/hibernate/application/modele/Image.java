package fr.polytech.tours.hibernate.application.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Classe representant les images contenus dans un message.
 * 
 * @author MOUTAS RIBEIRO et LIN
 *
 */
@Entity
public class Image {
	/**
	 * ImageID : identifiant de l'image.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ImageID;
	/**
	 * ParcourFichier : represente pour chaque image le parcours du fichier dans
	 * le serveur.
	 */
	private String ParcourFichier;
	/**
	 * Message : le message qui contient cet image. Un message peut avoir
	 * plusieurs images, mais une image ne caracterise qu'un seul message.
	 */
	@ManyToOne
	private Message Message;

	/**
	 * Constructeur par default.
	 */
	public Image() {
		this("", new Message());
	}

	/**
	 * Constructeur a 1 parametre.
	 * 
	 * @param parcoursFichier
	 */
	public Image(String parcoursFichier) {
		this(parcoursFichier, new Message());
	}

	/**
	 * Constructeur a 2 parametres.
	 * 
	 * @param parcourFichier
	 * @param message
	 */
	public Image(String parcourFichier, Message message) {
		ImageID = -1;
		this.ParcourFichier = parcourFichier;
		this.Message = message;
	}

	/**
	 * Redefinition de la methode toString, pour l'affichage de l'attribut
	 * ParcoursFichier.
	 */
	@Override
	public String toString() {
		return ParcourFichier;
	}

	/**
	 * Accesseurs et Mutatteurs.
	 */
	public int getImageID() {
		return ImageID;
	}

	public void setImageID(int imageID) {
		ImageID = imageID;
	}

	public String getParcourFichier() {
		return ParcourFichier;
	}

	public void setParcourFichier(String parcourFichier) {
		ParcourFichier = parcourFichier;
	}

	public Message getMessage() {
		return Message;
	}

	public void setMessage(Message message) {
		Message = message;
	}

}
