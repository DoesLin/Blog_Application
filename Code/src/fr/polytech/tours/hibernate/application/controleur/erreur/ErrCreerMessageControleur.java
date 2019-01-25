package fr.polytech.tours.hibernate.application.controleur.erreur;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.hibernate.application.controleur.BlogControleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controlleur permettant de gerer le erreurs lies a la creation d'un message.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class ErrCreerMessageControleur {

	private BlogControleur blogControleur;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="annuler"
	private Button annuler; // Value injected by FXMLLoader

	@FXML // fx:id="recreerMessage"
	private Button recreerMessage; // Value injected by FXMLLoader

	@FXML // fx:id="errMessage"
	private TextField errMessage; // Value injected by FXMLLoader

	public ErrCreerMessageControleur(BlogControleur blogControleur) {
		this.blogControleur = blogControleur;
	}

	public TextField getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(TextField errMessage) {
		this.errMessage = errMessage;
	}

	@FXML
	void eb0404(ActionEvent event) {

	}

	@FXML
	void recreerMessage(ActionEvent event) {
		System.out.println("recreerMessage");
		blogControleur.activer("ecrireMess");
	}

	@FXML
	void annuler(ActionEvent event) {
		System.out.println("annuler");
		blogControleur.activer("mesMess");
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert annuler != null : "fx:id=\"annuler\" was not injected: check your FXML file 'ErrCreerMessage.fxml'.";
        assert recreerMessage != null : "fx:id=\"recreerMessage\" was not injected: check your FXML file 'ErrCreerMessage.fxml'.";
        assert errMessage != null : "fx:id=\"errMessage\" was not injected: check your FXML file 'ErrCreerMessage.fxml'.";

	}
}
