package fr.polytech.tours.hibernate.application.controleur.invite;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.hibernate.application.controleur.BlogControleur;
import fr.polytech.tours.hibernate.application.controleur.dao.UtilisateurDAO;
import fr.polytech.tours.hibernate.application.modele.Utilisateur;
import fr.polytech.tours.hibernate.application.controleur.erreur.ErrCreationControleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Cette classe permet de gerer toutes les personnes qui ne sont pas enregistres
 * dans le blog internet mais qui peuvent consulter les messages publies par
 * d'autres utilisateurs, que eux sont enrigistres pour pouvoir publier des
 * messages.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class InscriptionControleur {

	private BlogControleur blogControleur;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="creerComp"
	private Button creerComp; // Value injected by FXMLLoader

	@FXML // fx:id="reMot2Passe"
	private PasswordField reMot2Passe; // Value injected by FXMLLoader

	@FXML // fx:id="annuler"
	private Button annuler; // Value injected by FXMLLoader

	@FXML // fx:id="mot2Passe"
	private PasswordField mot2Passe; // Value injected by FXMLLoader

	@FXML // fx:id="prenom"
	private TextField prenom; // Value injected by FXMLLoader

	@FXML // fx:id="nom"
	private TextField nom; // Value injected by FXMLLoader

	@FXML // fx:id="email"
	private TextField email; // Value injected by FXMLLoader

	@FXML // fx:id="adresse"
	private TextField adresse; // Value injected by FXMLLoader

	public InscriptionControleur(BlogControleur blogControleur) {
		this.blogControleur = blogControleur;
	}

	/**
	 * Fonction permettant de s'enregistrer dans le site internet et devenir un
	 * utilisateur.
	 * 
	 * @param event
	 */
	@FXML
	void creerCompte(ActionEvent event) {
		try {
			if (blogControleur.verifierTextField(prenom) && blogControleur.verifierTextField(nom)
					&& blogControleur.verifierTextField(email) && blogControleur.verifierPwField(mot2Passe)
					&& blogControleur.verifierPwField(reMot2Passe) && blogControleur.verifierEmail(email)
					&& blogControleur.verifierPwPareil(mot2Passe, reMot2Passe)) {
				Utilisateur utilisateur = new Utilisateur(nom.getText(), prenom.getText(), adresse.getText(),
						email.getText(), mot2Passe.getText());
				UtilisateurDAO utilisateurDAO = new UtilisateurDAO(blogControleur.getEm());

				blogControleur.beginTran();
				if (utilisateurDAO.creer(utilisateur) == false) {
					blogControleur.rollbackTran();
					throw new Exception("Duplicata utilisateur");
				}
				blogControleur.commitTran();

				blogControleur.preparerSalution(utilisateur);
				blogControleur.preparerIndexConn();

				System.out.println("creerCompte");
				blogControleur.activer("utilisateur");
			} else {
				if (!blogControleur.verifierTextField(prenom)) {
					throw new Exception("Prenom est obligatoire");
				}
				if (!blogControleur.verifierTextField(nom)) {
					throw new Exception("Nom est obligatoire");
				}
				if (!blogControleur.verifierTextField(email) || !blogControleur.verifierEmail(email)) {
					throw new Exception("Forme de votre mail n'est pas correcte");
				}
				if (!blogControleur.verifierTextField(mot2Passe) || !blogControleur.verifierTextField(reMot2Passe)) {
					throw new Exception("Mot de passe est obligatoire");
				}
				if (!blogControleur.verifierPwPareil(mot2Passe, reMot2Passe)) {
					throw new Exception("Mots de passe ne sont pas correspondents");
				}
				throw new Exception("Faute inattendue");
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("errCreerCompte");
			((ErrCreationControleur) blogControleur.getControleur("errCreation")).getErrMessage()
					.setText(e.getMessage());
			blogControleur.activer("errCreation");
		}

	}

	/**
	 * Fonction permettant d'annuler le compte utilisateur.
	 * 
	 * @param event
	 */
	@FXML
	void annulerCompte(ActionEvent event) {
		System.out.println("annulerCompte");
		blogControleur.activer("index");
	}

	/**
	 * This method is called by the FXMLLoader when initialization is complete
	 */
	@FXML
	void initialize() {
		assert creerComp != null : "fx:id=\"creerComp\" was not injected: check your FXML file 'Inscription.fxml'.";
		assert reMot2Passe != null : "fx:id=\"reMot2Passe\" was not injected: check your FXML file 'Inscription.fxml'.";
		assert annuler != null : "fx:id=\"annuler\" was not injected: check your FXML file 'Inscription.fxml'.";
		assert mot2Passe != null : "fx:id=\"mot2Passe\" was not injected: check your FXML file 'Inscription.fxml'.";
		assert prenom != null : "fx:id=\"prenom\" was not injected: check your FXML file 'Inscription.fxml'.";
		assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'Inscription.fxml'.";
		assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'Inscription.fxml'.";
		assert adresse != null : "fx:id=\"adresse\" was not injected: check your FXML file 'Inscription.fxml'.";
	}
}
