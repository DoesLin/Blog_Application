package fr.polytech.tours.hibernate.application.controleur;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.polytech.tours.hibernate.application.controleur.dao.MessageDAO;
import fr.polytech.tours.hibernate.application.controleur.erreur.*;
import fr.polytech.tours.hibernate.application.controleur.gestion.*;
import fr.polytech.tours.hibernate.application.controleur.invite.*;
import fr.polytech.tours.hibernate.application.controleur.utilisateur.*;
import fr.polytech.tours.hibernate.application.modele.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Classe controleur qui gere tous les controlleurs.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class BlogControleur extends SceneControleur {

	private Utilisateur utilisateurCourant;
	private Stage primaryStage;
	private EntityManager em = Persistence.createEntityManagerFactory("MyPersistence").createEntityManager();
	private EntityTransaction et = em.getTransaction();

	/**
	 * Attribut controllerMap qui est une liste contenant la liste de tous les
	 * controlleurs.
	 */
	private HashMap<String, Object> controleurMap = new HashMap<>();
	/**
	 * Attribut VALID_EMAIL_ADDRESS_REGEX qui correspond au format qui doit
	 * avoir l'adresse mail de l'utilisateur, ce qui permet de controler si une
	 * adresse mailest valide ou pas.
	 */
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	/**
	 * Constructeur par default de la classe qui fait appel a tous les
	 * controleurs de l'application, initialisation de tous les controleurs.
	 */
	public BlogControleur(Stage primaryStage) {
		this.primaryStage = primaryStage;

		controleurMap.put("index", new IndexControleur(this));

		// invite
		controleurMap.put("inscription", new InscriptionControleur(this));
		controleurMap.put("connexion", new ConnexionControleur(this));
		controleurMap.put("resultatCherNonConn", new ResultatCherNonConnControleur(this));

		// erreur
		controleurMap.put("errCreation", new ErrCreationControleur(this));
		controleurMap.put("errConnexion", new ErrConnexionControleur(this));
		controleurMap.put("errModification", new ErrModificationControleur(this));
		controleurMap.put("errModiMessage", new ErrModiMessageControleur(this));
		controleurMap.put("errCreerMessage", new ErrCreerMessageControleur(this));

		// utilisateur
		controleurMap.put("visualiserMess", new VisualiserMessControleur(this));
		controleurMap.put("utilisateur", new UtilisateurControleur(this));
		controleurMap.put("mesMess", new MesMessagesControleur(this));
		controleurMap.put("resultatCher", new ResultatCherControleur(this));
		controleurMap.put("ecrireMess", new EcrireMessControleur(this));
		controleurMap.put("modifierMess", new ModifierMessControleur(this));

		// gestion
		controleurMap.put("gestUtil", new GestionUtilControleur(this));

	}

	public EntityManager getEm() {
		return em;
	}

	public EntityTransaction getEt() {
		return et;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public void setEt(EntityTransaction et) {
		this.et = et;
	}

	public void beginTran() {
		et.begin();
	}

	public void commitTran() {
		et.commit();
	}

	public void rollbackTran() {
		et.rollback();
	}

	/**
	 * @return the utilisateurCourant
	 */
	public Utilisateur getUtilisateurCourant() {
		return utilisateurCourant;
	}

	/**
	 * @param utilisateurCourant
	 *            the utilisateurCourant to set
	 */
	public void setUtilisateurCourant(Utilisateur utilisateurCourant) {
		this.utilisateurCourant = utilisateurCourant;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	/**
	 * Accesseur du controleur.
	 * 
	 * @param nom
	 * @return le nom du controlleur
	 */
	public Object getControleur(String nom) {
		return controleurMap.get(nom);
	}

	public boolean verifierTextField(TextField tf) {
		if ((tf.getText() != null && !tf.getText().isEmpty())) {
			return true;
		}
		return false;
	}

	public boolean verifierTextArea(TextArea ta) {
		if ((ta.getText() != null && !ta.getText().isEmpty())) {
			return true;
		}
		return false;
	}

	/**
	 * Verifier si un mot de pass a ete rentre par l'utilisateur.
	 * 
	 * @param pf
	 * @return true si oui, et false sinon
	 */
	public boolean verifierPwField(PasswordField pf) {
		if ((pf.getText() != null && !pf.getText().isEmpty())) {
			return true;
		}
		return false;
	}

	/**
	 * Cette fonction permet de verifier si le mot de pass rentre lors de son
	 * enregistrement est pareil que celui lors de sa connexion.
	 * 
	 * @param pf1
	 * @param pf2
	 * @return true si les deux mots de passe sont identiques, false sinon
	 */
	public boolean verifierPwPareil(PasswordField pf1, PasswordField pf2) {
		if (pf1.getText().equals(pf2.getText())) {
			return true;
		}
		return false;
	}

	/**
	 * Cette fonction permet de verifier si l'adresse mail rentre est valide ou
	 * pas.
	 * 
	 * @param email
	 * @return true si l'email rentre correspond a un format valide, false
	 *         sinon.
	 */
	public boolean verifierEmail(TextField email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText());
		return matcher.find();
	}

	/**
	 * Methode permettant de l'affichage des messages de salutation adapte a la
	 * demande de l'utilisateur.
	 * 
	 * @param utilisateur
	 */
	public void preparerSalution(Utilisateur utilisateur) {
		setUtilisateurCourant(utilisateur);

		UtilisateurControleur utilControleur = ((UtilisateurControleur) getControleur("utilisateur"));
		utilControleur.getTexteCompte().setText("Bonjour " + utilisateurCourant.getPrenom() + " !");

		ResultatCherControleur resuControleur = ((ResultatCherControleur) getControleur("resultatCher"));
		resuControleur.getTexteCompte().setText("Bonjour " + utilisateurCourant.getPrenom() + " !");

		MesMessagesControleur mesmControleur = ((MesMessagesControleur) getControleur("mesMess"));
		mesmControleur.getTexteCompte().setText("Bonjour " + utilisateurCourant.getPrenom() + " !");

		EcrireMessControleur ecriControleur = ((EcrireMessControleur) getControleur("ecrireMess"));
		ecriControleur.getTexteCompte().setText("Bonjour " + utilisateurCourant.getPrenom() + " !");

		ModifierMessControleur modiControleur = ((ModifierMessControleur) getControleur("modifierMess"));
		modiControleur.getTexteCompte().setText("Bonjour " + utilisateurCourant.getPrenom() + " !");

		VisualiserMessControleur visuControleur = ((VisualiserMessControleur) getControleur("visualiserMess"));
		visuControleur.getTexteCompte().setText("Bonjour " + utilisateurCourant.getPrenom() + " !");
	}

	public void preparerResNonConn(String motCher) {
		MessageDAO messDAO = new MessageDAO(em);

		ObservableList<Message> items = FXCollections.observableArrayList(messDAO.chercherParMot(motCher));

		((ResultatCherNonConnControleur) getControleur("resultatCherNonConn")).getAffiche().setItems(items);
	}

	public void preparerResConn(String motCher) {
		MessageDAO messDAO = new MessageDAO(em);

		ObservableList<Message> items = FXCollections.observableArrayList(messDAO.chercherParMot(motCher));

		((ResultatCherControleur) getControleur("resultatCher")).getAffiche().setItems(items);
	}

	public void preparerIndexNonConn() {
		MessageDAO messDAO = new MessageDAO(getEm());
		ObservableList<Message> items = FXCollections.observableArrayList(messDAO.chercherTous());
		((IndexControleur) getControleur("index")).getAffiche().setItems(items);
	}

	
	public void preparerIndexConn() {
		MessageDAO messDAO = new MessageDAO(getEm());
		ObservableList<Message> items = FXCollections.observableArrayList(messDAO.chercherTous());
		((UtilisateurControleur) getControleur("utilisateur")).getAffiche().setItems(items);
	}

	/**
	 * Methode pour l'affichage d'une image.
	 * 
	 * @param msg
	 * @param visualiser
	 */
	public void afficherImages(Message msg, VisualiserMessControleur visualiser) {
		visualiser.getTitre().setText(msg.getTitre());
		visualiser.getTexte().setText(msg.getTexte());
		visualiser.getUtilisateur().setText(msg.getUtilisateur().toString());
		;
		visualiser.getDate().setText(msg.getDate().toString());
		visualiser.getMotCles().setText(msg.getListeMotCle().toString());

		List<File> fichiers = new ArrayList<File>();
		for (Image image : msg.getListeImages()) {
			fichiers.add(new File(image.getParcourFichier()));
		}

		List<Tab> imagesTabs = new ArrayList<Tab>();
		for (int i = 0; i < fichiers.size(); i++) {
			try {

				ImageView curVue = new ImageView();
				curVue.setImage(SwingFXUtils.toFXImage(ImageIO.read(fichiers.get(i)), null));
				curVue.setFitHeight(135);
				curVue.setPreserveRatio(true);
				curVue.setSmooth(true);
				curVue.setCache(true);

				imagesTabs.add(new Tab("Image " + String.valueOf(i + 1), curVue));
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
		visualiser.getImagesVue().getTabs().clear();
		visualiser.getImagesVue().getTabs().addAll(imagesTabs);
	}

	/**
	 * Affichage des liens d'un message.
	 * 
	 * @param msg
	 * @param visualiser
	 */
	public void afficherLiens(Message msg, VisualiserMessControleur visualiser) {
		List<Tab> liensTabs = new ArrayList<Tab>();
		for (Lien lien : msg.getListeLiens()) {
			Tab tab = new Tab();
			tab.setText(lien.getNomLien());
			tab.setContent(new TextArea(lien.getTexteVisualiser()));
			liensTabs.add(tab);
		}
		visualiser.getLiensVue().getTabs().clear();
		visualiser.getLiensVue().getTabs().addAll(liensTabs);
	}

}
