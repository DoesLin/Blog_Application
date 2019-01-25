package fr.polytech.tours.hibernate.application.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.hibernate.application.controleur.dao.MessageDAO;
import fr.polytech.tours.hibernate.application.modele.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import fr.polytech.tours.hibernate.application.controleur.invite.ResultatCherNonConn;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlleur de l'acceuil de l'application.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class IndexControleur {

	private BlogControleur blogControleur;
	private MessageDAO messDAO;

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

	public IndexControleur(BlogControleur blogControleur) {
		super();
		this.setBlogControleur(blogControleur);
		messDAO = new MessageDAO(blogControleur.getEm());
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

	public void setAffiche(TableView<Message> affiche) {
		this.affiche = affiche;
	}

	@FXML
	void allerIndex(ActionEvent event) {
		blogControleur.preparerIndexNonConn();
		System.out.println("allerIndex");
		blogControleur.activer("index");
	}

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

	@FXML
	void allerResu(ActionEvent event) {
		blogControleur.preparerResNonConn(textItem.getText());
		System.out.println("allerResu");
		blogControleur.activer("resultatCherNonConn");
	}

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
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

		ObservableList<Message> items = FXCollections.observableArrayList(messDAO.chercherTous());

		affiche.setItems(items);

		titr.setCellValueFactory(new PropertyValueFactory<Message, String>("Titre"));
		text.setCellValueFactory(new PropertyValueFactory<Message, String>("Texte"));
		util.setCellValueFactory(new PropertyValueFactory<Message, String>("Utilisateur"));
		motCles.setCellValueFactory(new PropertyValueFactory<Message, String>("ListeMotCle"));
		date.setCellValueFactory(new PropertyValueFactory<Message, String>("Date"));

	}
}
