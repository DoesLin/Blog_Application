package fr.polytech.tours.hibernate.application.controleur.invite;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.hibernate.application.controleur.BlogControleur;
import fr.polytech.tours.hibernate.application.controleur.dao.UtilisateurDAO;
import fr.polytech.tours.hibernate.application.controleur.erreur.ErrConnexionControleur;
import fr.polytech.tours.hibernate.application.modele.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Cette classe permet de gerer toutes les personnes qui ne sont pas enregistres
 * dans le blog internet mais qui peuvent publier des messages, les modifier ou
 * supprimer.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class ConnexionControleur {

	private BlogControleur blogControleur;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="annuler"
	private Button annuler; // Value injected by FXMLLoader

	@FXML // fx:id="seConnecter"
	private Button seConnecter; // Value injected by FXMLLoader

	@FXML // fx:id="mot2Passe"
	private PasswordField mot2Passe; // Value injected by FXMLLoader

	@FXML // fx:id="email"
	private TextField email; // Value injected by FXMLLoader

	public ConnexionControleur(BlogControleur blogControleur) {
		this.blogControleur = blogControleur;
	}

	/**
	 * Fonction permettant de se connecter a l'application.
	 * 
	 * @param event
	 */
	@FXML
	void seConnecter(ActionEvent event) {

		try {
			if (blogControleur.verifierTextField(email) && blogControleur.verifierPwField(mot2Passe)
					&& blogControleur.verifierEmail(email)) {

				UtilisateurDAO utilisateurDAO = new UtilisateurDAO(blogControleur.getEm());
				Utilisateur utilisateur = utilisateurDAO.chercherParID(email.getText());

				if (utilisateur == null) {
					throw new Exception("Fault email");
				}

				if (!utilisateur.getMotDePasse().equals(mot2Passe.getText())) {
					throw new Exception("Fault mot2Passe");
				}
				blogControleur.preparerSalution(utilisateur);
				blogControleur.preparerIndexConn();

				System.out.println("seConnecter");
				blogControleur.activer("utilisateur");
			} else {
				throw new Exception("Fault entrees");
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("errConnexion");
			((ErrConnexionControleur) blogControleur.getControleur("errConnexion")).getErrMessage()
					.setText(e.getMessage());
			blogControleur.activer("errConnexion");
		}

	}

	@FXML
	void annulerConnexion(ActionEvent event) {
		System.out.println("annulerConnexion");
		blogControleur.activer("index");
	}

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
	void initialize() {
		assert annuler != null : "fx:id=\"annuler\" was not injected: check your FXML file 'Connexion.fxml'.";
		assert mot2Passe != null : "fx:id=\"mot2Passe\" was not injected: check your FXML file 'Connexion.fxml'.";
		assert seConnecter != null : "fx:id=\"seConnecter\" was not injected: check your FXML file 'Connexion.fxml'.";
		assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'Connexion.fxml'.";

	}
}
