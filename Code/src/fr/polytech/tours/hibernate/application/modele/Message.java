package fr.polytech.tours.hibernate.application.modele;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.UpdateTimestamp;

/**
 * Classe qui gere les messages publies par un utilisateur dans le blog. Il gere
 * la creation d'un message avec toutes ses caracteristiques : le titre, texte,
 * images, liens, date, mots cle.
 * 
 * @author MOUTAS RIBEIRO et LIN
 *
 */
@Entity
public class Message {

	/**
	 * Attributs
	 */
	/**
	 * MessageID : l'attribut messageID represente l'identifiant de message.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int MessageID;
	/**
	 * Titre : attribut representant le titre du message.
	 */
	private String Titre;
	/**
	 * Texte : le texte a introduire pour publier un message.
	 */
	private String Texte;
	/**
	 * ListeImages : Correspond la liste des images que l'utilisateur peut choisir
	 * pour publir avec son message, donc une meme image peut etre presente en deux
	 * messages differnts.
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "Message")
	private List<Image> ListeImages;
	/**
	 * ListeLiens : la liste des liens qui peuvent etre associes a un message.
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "Message")
	private List<Lien> ListeLiens;
	/**
	 * ListeMotCle : la liste des mots cles qui identifient un message. Un mot cle
	 * peut etre present en deux messages differents.
	 */
	@ManyToMany
	@JoinTable(name = "Message_MotCle", joinColumns = {
			@JoinColumn(name = "MessageID", referencedColumnName = "MessageID") }, inverseJoinColumns = {
					@JoinColumn(name = "MotCleID", referencedColumnName = "MotCleID") })
	private List<MotCle> ListeMotCle;
	/**
	 * Date : date a laquelle le message a ete publie.
	 */
	@UpdateTimestamp
	private LocalDateTime Date;
	/**
	 * Utilisateur : l'utilisateur qui publie le message. Un message ne peut etre
	 * publie que par un utilisateur, mais un utilisateur peut publier plusieurs
	 * messages.
	 */
	@ManyToOne
	private Utilisateur Utilisateur;

	/**
	 * Constructeur par default.
	 */
	public Message() {
		this("", "", null, null, null, null);
	}

	/**
	 * Constructeur a 3 parametres, initialisation des attributs de la classe par
	 * les valeurs des parametres passes au constructeur.
	 * 
	 * @param titre
	 * @param texte
	 * @param listeMotCle
	 */
	public Message(String titre, String texte, List<MotCle> listeMotCle) {
		this(titre, texte, null, null, listeMotCle, null);
	}

	/**
	 * Constructeur a 5 parametres, initialisation des attributs de la classe par
	 * les valeurs des parametres passes au constructeur.
	 * 
	 * @param titre
	 * @param texte
	 * @param listeImages
	 * @param listeLiens
	 * @param listeMotCle
	 */
	public Message(String titre, String texte, List<Image> listeImages, List<Lien> listeLiens,
			List<MotCle> listeMotCle) {
		this(titre, texte, listeImages, listeLiens, listeMotCle, null);
	}

	/**
	 * Constructeur a 6 parametres.
	 * 
	 * @param titre
	 * @param texte
	 * @param listeImages
	 * @param listeLiens
	 * @param listeMotCle
	 * @param utilisateur
	 */
	public Message(String titre, String texte, List<Image> listeImages, List<Lien> listeLiens,
			List<MotCle> listeMotCle, Utilisateur utilisateur) {
		super();
		this.Titre = titre;
		this.Texte = texte;
		this.ListeImages = listeImages;
		this.ListeLiens = listeLiens;
		this.ListeMotCle = listeMotCle;
		this.Utilisateur = utilisateur;
	}

	/**
	 * Accesseurs et Mutateurs des attributs de cette classe se trouvent ci-dessous.
	 */
	public int getMessageID() {
		return MessageID;
	}

	public void setMessageID(int messageID) {
		MessageID = messageID;
	}

	public String getTitre() {
		return Titre;
	}

	public void setTitre(String titre) {
		Titre = titre;
	}

	public String getTexte() {
		return Texte;
	}

	public void setTexte(String texte) {
		Texte = texte;
	}

	public List<Image> getListeImages() {
		return ListeImages;
	}

	public void setListeImages(List<Image> listeImages) {
		ListeImages = listeImages;
	}

	public List<Lien> getListeLiens() {
		return ListeLiens;
	}

	public void setListeLiens(List<Lien> listeLiens) {
		ListeLiens = listeLiens;
	}

	public List<MotCle> getListeMotCle() {
		return ListeMotCle;
	}

	public void setListeMotCle(List<MotCle> listeMotCle) {
		ListeMotCle = listeMotCle;
	}

	public LocalDateTime getDate() {
		return Date;
	}

	public void setDate(LocalDateTime date) {
		Date = date;
	}

	public Utilisateur getUtilisateur() {
		return Utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		Utilisateur = utilisateur;
	}
}
