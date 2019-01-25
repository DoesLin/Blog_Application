package fr.polytech.tours.hibernate.application.controleur.utilisateur;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.hibernate.application.controleur.BlogControleur;
import fr.polytech.tours.hibernate.application.controleur.gestion.GestionUtilControleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controlleur permettant de gerer l'affichage des messages par recherche selon
 * le mot cle entre ou par date.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class VisualiserMessControleur {

	private BlogControleur blogControleur;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="date"
	private TextField date; // Value injected by FXMLLoader

	@FXML // fx:id="seDeconnecter"
	private MenuItem seDeconnecter; // Value injected by FXMLLoader

	@FXML // fx:id="utilisateur"
	private TextField utilisateur; // Value injected by FXMLLoader

	@FXML // fx:id="titre"
	private TextField titre; // Value injected by FXMLLoader

	@FXML // fx:id="texteCompte"
	private TextField texteCompte; // Value injected by FXMLLoader

	@FXML // fx:id="gererCompte"
	private MenuItem gererCompte; // Value injected by FXMLLoader

	@FXML // fx:id="index"
	private Button index; // Value injected by FXMLLoader

	@FXML // fx:id="motCles"
	private TextField motCles; // Value injected by FXMLLoader

	@FXML // fx:id="texte"
	private TextArea texte; // Value injected by FXMLLoader

	@FXML // fx:id="compte"
	private MenuButton compte; // Value injected by FXMLLoader

	@FXML // fx:id="imagesVue"
	private TabPane imagesVue; // Value injected by FXMLLoader

	@FXML // fx:id="liensVue"
	private TabPane liensVue; // Value injected by FXMLLoader

	public BlogControleur getBlogControleur() {
		return blogControleur;
	}

	public void setBlogControleur(BlogControleur blogControleur) {
		this.blogControleur = blogControleur;
	}

	public VisualiserMessControleur(BlogControleur blogControleur) {
		super();
		this.setBlogControleur(blogControleur);
	}

	public TextField getTexteCompte() {
		return texteCompte;
	}

	public TextField getDate() {
		return date;
	}

	public TextField getUtilisateur() {
		return utilisateur;
	}

	public TextField getTitre() {
		return titre;
	}

	public TextField getMotCles() {
		return motCles;
	}

	public TextArea getTexte() {
		return texte;
	}

	public TabPane getImagesVue() {
		return imagesVue;
	}

	public TabPane getLiensVue() {
		return liensVue;
	}

	public void setDate(TextField date) {
		this.date = date;
	}

	public void setUtilisateur(TextField utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setTitre(TextField titre) {
		this.titre = titre;
	}

	public void setMotCles(TextField motCles) {
		this.motCles = motCles;
	}

	public void setTexte(TextArea texte) {
		this.texte = texte;
	}

	public void setImagesVue(TabPane imagesVue) {
		this.imagesVue = imagesVue;
	}

	public void setLiensVue(TabPane liensVue) {
		this.liensVue = liensVue;
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

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
	void initialize() {
		assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'VisualiserMess.fxml'.";
		assert seDeconnecter != null : "fx:id=\"seDeconnecter\" was not injected: check your FXML file 'VisualiserMess.fxml'.";
		assert utilisateur != null : "fx:id=\"utilisateur\" was not injected: check your FXML file 'VisualiserMess.fxml'.";
		assert titre != null : "fx:id=\"titre\" was not injected: check your FXML file 'VisualiserMess.fxml'.";
		assert texteCompte != null : "fx:id=\"texteCompte\" was not injected: check your FXML file 'VisualiserMess.fxml'.";
		assert gererCompte != null : "fx:id=\"gererCompte\" was not injected: check your FXML file 'VisualiserMess.fxml'.";
		assert index != null : "fx:id=\"index\" was not injected: check your FXML file 'VisualiserMess.fxml'.";
		assert motCles != null : "fx:id=\"motCles\" was not injected: check your FXML file 'VisualiserMess.fxml'.";
		assert texte != null : "fx:id=\"texte\" was not injected: check your FXML file 'VisualiserMess.fxml'.";
		assert compte != null : "fx:id=\"compte\" was not injected: check your FXML file 'VisualiserMess.fxml'.";
		assert imagesVue != null : "fx:id=\"imagesVue\" was not injected: check your FXML file 'VisualiserMess.fxml'.";
		assert liensVue != null : "fx:id=\"liensVue\" was not injected: check your FXML file 'VisualiserMess.fxml'.";

	}
}
