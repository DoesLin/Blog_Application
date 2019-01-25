package fr.polytech.tours.hibernate.application.controleur.erreur;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.hibernate.application.controleur.BlogControleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controlleur permettant de gerer le erreurs lies a la connexion.
 * @author Moutas Ribeiro et Lin
 *
 */
public class ErrConnexionControleur {

	private BlogControleur blogControleur;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="annuler"
	private Button annuler; // Value injected by FXMLLoader

	@FXML // fx:id="reconnecterCompte"
	private Button reconnecterCompte; // Value injected by FXMLLoader

	@FXML // fx:id="errMessage"
	private TextField errMessage; // Value injected by FXMLLoader

	/**
	 * @return the errMessage
	 */
	public TextField getErrMessage() {
		return errMessage;
	}

	/**
	 * Methode permettant l'affichage d'un message d'erreur.
	 * @param errMessage the errMessage to set
	 */
	public void setErrMessage(TextField errMessage) {
		this.errMessage = errMessage;
	}

	/**
	 * Methode representant une erreur de connexion.
	 * @param blogControleur
	 */
	public ErrConnexionControleur(BlogControleur blogControleur) {
		this.blogControleur = blogControleur;
	}

	@FXML
	void eb0404(ActionEvent event) {

	}

	@FXML
    void reconnecterCompte(ActionEvent event) {
		System.out.println("reconnecterCompte");
		blogControleur.activer("connexion");
    }

	@FXML
	void annuler(ActionEvent event) {
		System.out.println("annuler");
		blogControleur.activer("index");
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert annuler != null : "fx:id=\"annuler\" was not injected: check your FXML file 'ErrConnexion.fxml'.";
		assert reconnecterCompte != null : "fx:id=\"reconnecterCompte\" was not injected: check your FXML file 'ErrConnexion.fxml'.";
		assert errMessage != null : "fx:id=\"errMessage\" was not injected: check your FXML file 'ErrConnexion.fxml'.";

	}
}
