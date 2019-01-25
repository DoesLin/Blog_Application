package fr.polytech.tours.hibernate.application.controleur.utilisateur;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.hibernate.application.controleur.BlogControleur;
import fr.polytech.tours.hibernate.application.controleur.dao.MessageDAO;
import fr.polytech.tours.hibernate.application.controleur.gestion.GestionUtilControleur;
import fr.polytech.tours.hibernate.application.modele.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 * Controlleur permettant de gerer les actions lies a l'utilisateur.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class UtilisateurControleur {

	private BlogControleur blogControleur;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="date"
	private TableColumn<Message, String> date; // Value injected by FXMLLoader

	@FXML // fx:id="seDeconnecter"
	private MenuItem seDeconnecter; // Value injected by FXMLLoader

	@FXML // fx:id="affiche"
	private TableView<Message> affiche; // Value injected by FXMLLoader

	@FXML // fx:id="visualiser"
	private Button visualiser; // Value injected by FXMLLoader

	@FXML // fx:id="titr"
	private TableColumn<Message, String> titr; // Value injected by FXMLLoader

	@FXML // fx:id="motCles"
	private TableColumn<Message, String> motCles; // Value injected by FXMLLoader

	@FXML // fx:id="text"
	private TableColumn<Message, String> text; // Value injected by FXMLLoader

	@FXML // fx:id="util"
	private TableColumn<Message, String> util; // Value injected by FXMLLoader

	@FXML // fx:id="textItem"
	private TextField textItem; // Value injected by FXMLLoader

	@FXML // fx:id="texteCompte"
	private TextField texteCompte; // Value injected by FXMLLoader

	@FXML // fx:id="index"
	private Button index; // Value injected by FXMLLoader

	@FXML // fx:id="chercher"
	private Button chercher; // Value injected by FXMLLoader

	@FXML // fx:id="gererCompte"
	private MenuItem gererCompte; // Value injected by FXMLLoader

	@FXML // fx:id="voirMessages"
	private Button voirMessages; // Value injected by FXMLLoader

	@FXML // fx:id="compte"
	private MenuButton compte; // Value injected by FXMLLoader

	public UtilisateurControleur(BlogControleur blogControleur) {
		this.blogControleur = blogControleur;
	}

	public TextField getTexteCompte() {
		return texteCompte;
	}

	public TableView<Message> getAffiche() {
		return affiche;
	}

	public void setAffiche(TableView<Message> affiche) {
		this.affiche = affiche;
	}

	@FXML
	void seDeconnecter(ActionEvent event) {
		blogControleur.setUtilisateurCourant(null);
		blogControleur.preparerIndexNonConn();

		System.out.println("seDeconnecter");
		blogControleur.activer("index");
	}

	@FXML
	void gererCompte(ActionEvent event) {
		GestionUtilControleur gestUtil = (GestionUtilControleur) blogControleur.getControleur("gestUtil");
		gestUtil.getNom().setText(blogControleur.getUtilisateurCourant().getNom());
		gestUtil.getPrenom().setText(blogControleur.getUtilisateurCourant().getPrenom());
		gestUtil.getAdresse().setText(blogControleur.getUtilisateurCourant().getAdresse());

		System.out.println("gererCompte");
		blogControleur.activer("gestUtil");
	}

	@FXML
	void allerIndex(ActionEvent event) {
		blogControleur.preparerIndexConn();

		System.out.println("allerIndex");
		blogControleur.activer("utilisateur");
	}

	@FXML
	void allerResu(ActionEvent event) {
		blogControleur.preparerResConn(textItem.getText());

		System.out.println("allerResu");
		blogControleur.activer("resultatCher");
	}
	
	@FXML
	void visualiser(ActionEvent event) {
		Message msg = affiche.getSelectionModel().getSelectedItem();
		if (msg == null) {
			System.out.println("Aucun choix");
			return;
		}

		VisualiserMessControleur visualiser = ((VisualiserMessControleur) blogControleur.getControleur("visualiserMess"));

		blogControleur.afficherImages(msg, visualiser);
		blogControleur.afficherLiens(msg, visualiser);
		
		System.out.println("visualiserMessage");
		blogControleur.activer("visualiserMess");
	}

	@FXML
	void voirMessages(ActionEvent event) {
		MessageDAO messDAO = new MessageDAO(blogControleur.getEm());
		ObservableList<Message> items = FXCollections
				.observableArrayList(messDAO.chercherParUtilisateur(blogControleur.getUtilisateurCourant()));
		((MesMessagesControleur) blogControleur.getControleur("mesMess")).getAffiche().setItems(items);

		System.out.println("voirMessages");
		blogControleur.activer("mesMess");
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert seDeconnecter != null : "fx:id=\"seDeconnecter\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert affiche != null : "fx:id=\"affiche\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert visualiser != null : "fx:id=\"visualiser\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert titr != null : "fx:id=\"titr\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert textItem != null : "fx:id=\"textItem\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert texteCompte != null : "fx:id=\"texteCompte\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert index != null : "fx:id=\"index\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert chercher != null : "fx:id=\"chercher\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert util != null : "fx:id=\"util\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert gererCompte != null : "fx:id=\"gererCompte\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert motCles != null : "fx:id=\"motCles\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert voirMessages != null : "fx:id=\"voirMessages\" was not injected: check your FXML file 'Utilisateur.fxml'.";
		assert compte != null : "fx:id=\"compte\" was not injected: check your FXML file 'Utilisateur.fxml'.";

		titr.setCellValueFactory(new PropertyValueFactory<Message, String>("Titre"));
		text.setCellValueFactory(new PropertyValueFactory<Message, String>("Texte"));
		util.setCellValueFactory(new PropertyValueFactory<Message, String>("Utilisateur"));
		motCles.setCellValueFactory(new PropertyValueFactory<Message, String>("ListeMotCle"));
		date.setCellValueFactory(new PropertyValueFactory<Message, String>("Date"));
	}
}
