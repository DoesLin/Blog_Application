/**
 * 
 */
package fr.polytech.tours.hibernate.application.controleur.invite;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.hibernate.application.controleur.BlogControleur;
import fr.polytech.tours.hibernate.application.modele.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Classe permettant de chercher des resultats d'une recherche en utilisant, la
 * classe MotCle et Message pour trier la recherche, et la classe Message permet
 * aussi l'afficher les messages correspondants a cette recherche.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class ResultatCherNonConnControleur {
	/**
	 * Les attributs de la classe recherche.
	 */
	private BlogControleur blogControleur;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="date"
	private TableColumn<Message, String> date; // Value injected by FXMLLoader

	@FXML // fx:id="affiche"
	private TableView<Message> affiche; // Value injected by FXMLLoader

	@FXML // fx:id="seConn"
	private Button seConn; // Value injected by FXMLLoader

	@FXML // fx:id="util"
	private TableColumn<Message, String> util; // Value injected by FXMLLoader

	@FXML // fx:id="titr"
	private TableColumn<Message, String> titr; // Value injected by FXMLLoader

	@FXML // fx:id="textItem"
	private TextField textItem; // Value injected by FXMLLoader

	@FXML // fx:id="index"
	private Button index; // Value injected by FXMLLoader

	@FXML // fx:id="motCles"
	private TableColumn<Message, String> motCles; // Value injected by
													// FXMLLoader

	@FXML // fx:id="text"
	private TableColumn<Message, String> text; // Value injected by FXMLLoader

	@FXML // fx:id="chercher"
	private Button chercher; // Value injected by FXMLLoader

	@FXML // fx:id="sInsc"
	private Button sInsc; // Value injected by FXMLLoader

	public ResultatCherNonConnControleur(BlogControleur blogControleur) {
		super();
		this.setBlogControleur(blogControleur);
	}

	public BlogControleur getBlogControleur() {
		return blogControleur;
	}

	public void setBlogControleur(BlogControleur blogControleur) {
		this.blogControleur = blogControleur;
	}

	public TableView<Message> getAffiche() {
		return affiche;
	}

	/**
	 * Activation du bouton index.
	 * 
	 * @param event
	 */
	@FXML
	void allerIndex(ActionEvent event) {
		blogControleur.preparerIndexNonConn();
		System.out.println("allerIndex");
		blogControleur.activer("index");
	}

	/**
	 * Activation du bouton connexion.
	 * 
	 * @param event
	 */
	@FXML
	void allerConn(ActionEvent event) {
		System.out.println("allerConn");
		blogControleur.activer("connexion");
	}

	@FXML
	void allerInsc(ActionEvent event) {
		System.out.println("allerInsc");
		blogControleur.activer("inscription");
	}

	/**
	 * Activation du bouton resultatCherNonConn.
	 * 
	 * @param event
	 */
	@FXML
	void allerResu(ActionEvent event) {
		blogControleur.preparerResNonConn(textItem.getText());
		System.out.println("allerResu");
		blogControleur.activer("resultatCherNonConn");
	}

	/**
	 * This method is called by the FXMLLoader when initialization is complete
	 */
	@FXML
	void initialize() {
		assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'Index.fxml'.";
		assert affiche != null : "fx:id=\"affiche\" was not injected: check your FXML file 'Index.fxml'.";
		assert seConn != null : "fx:id=\"seConn\" was not injected: check your FXML file 'Index.fxml'.";
		assert util != null : "fx:id=\"util\" was not injected: check your FXML file 'Index.fxml'.";
		assert titr != null : "fx:id=\"titr\" was not injected: check your FXML file 'Index.fxml'.";
		assert textItem != null : "fx:id=\"textItem\" was not injected: check your FXML file 'Index.fxml'.";
		assert index != null : "fx:id=\"index\" was not injected: check your FXML file 'Index.fxml'.";
		assert motCles != null : "fx:id=\"motCles\" was not injected: check your FXML file 'Index.fxml'.";
		assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'Index.fxml'.";
		assert chercher != null : "fx:id=\"chercher\" was not injected: check your FXML file 'Index.fxml'.";
		assert sInsc != null : "fx:id=\"sInsc\" was not injected: check your FXML file 'Index.fxml'.";

		titr.setCellValueFactory(new PropertyValueFactory<Message, String>("Titre"));
		text.setCellValueFactory(new PropertyValueFactory<Message, String>("Texte"));
		util.setCellValueFactory(new PropertyValueFactory<Message, String>("Utilisateur"));
		motCles.setCellValueFactory(new PropertyValueFactory<Message, String>("ListeMotCle"));
		date.setCellValueFactory(new PropertyValueFactory<Message, String>("Date"));
	}
}
