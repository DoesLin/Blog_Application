package fr.polytech.tours.hibernate.application.controleur.gestion;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.tours.hibernate.application.controleur.BlogControleur;
import fr.polytech.tours.hibernate.application.controleur.dao.UtilisateurDAO;
import fr.polytech.tours.hibernate.application.controleur.erreur.ErrModificationControleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Classe permettant de faire la gestion des personnes utilisant le blog
 * internet.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class GestionUtilControleur {

	private BlogControleur blogControleur;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="modifierCompte"
	private Button modifierCompte; // Value injected by FXMLLoader

	@FXML // fx:id="reMot2Passe"
	private PasswordField reMot2Passe; // Value injected by FXMLLoader

	@FXML // fx:id="annuler"
	private Button annuler; // Value injected by FXMLLoader

	@FXML // fx:id="mot2Passe"
	private PasswordField mot2Passe; // Value injected by FXMLLoader

	@FXML // fx:id="adresse"
	private TextField adresse; // Value injected by FXMLLoader

	@FXML // fx:id="prenom"
	private TextField prenom; // Value injected by FXMLLoader

	@FXML // fx:id="nom"
	private TextField nom; // Value injected by FXMLLoader

	public GestionUtilControleur(BlogControleur blogControleur) {
		super();
		this.setBlogControleur(blogControleur);
	}

	public BlogControleur getBlogControleur() {
		return blogControleur;
	}

	public void setBlogControleur(BlogControleur blogControleur) {
		this.blogControleur = blogControleur;
	}

	/**
	 * Accesseur de Prenom, pour avoir acces au prenom de l'utilisateur.
	 * 
	 * @return the prenom
	 */
	public TextField getPrenom() {
		return prenom;
	}

	/**
	 * 
	 * @param prenom
	 *            the prenom to set
	 */
	public void setPrenom(TextField prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the nom
	 */
	public TextField getNom() {
		return nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(TextField nom) {
		this.nom = nom;
	}

	public TextField getAdresse() {
		return adresse;
	}

	/**
	 * Methode permettant la modification du compte utilisateur dans le blog.
	 * 
	 * @param event
	 */
	@FXML
	void modifierCompte(ActionEvent event) {

		try {
			if (blogControleur.verifierTextField(prenom) && blogControleur.verifierTextField(nom)
					&& blogControleur.verifierTextField(adresse) && blogControleur.verifierPwField(mot2Passe)
					&& blogControleur.verifierPwField(reMot2Passe)
					&& blogControleur.verifierPwPareil(mot2Passe, reMot2Passe)) {

				blogControleur.getUtilisateurCourant().setPrenom(prenom.getText());
				blogControleur.getUtilisateurCourant().setAdresse(adresse.getText());
				blogControleur.getUtilisateurCourant().setNom(nom.getText());
				blogControleur.getUtilisateurCourant().setMotDePasse(mot2Passe.getText());

				UtilisateurDAO utiDAO = new UtilisateurDAO(blogControleur.getEm());
				if (utiDAO.modifier(blogControleur.getUtilisateurCourant()) == false) {
					throw new Exception("Faute utilisateur");
				}

				System.out.println("modifierCompte");
				blogControleur.activer("utilisateur");
			} else {
				if (!blogControleur.verifierTextField(prenom)) {
					throw new Exception("Prenom est obligatoire");
				}
				if (!blogControleur.verifierTextField(nom)) {
					throw new Exception("Nom est obligatoire");
				}
				if (!blogControleur.verifierTextField(adresse)) {
					throw new Exception("Adresse est obligatoire");
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
			System.out.println("errModifierCompte");
			((ErrModificationControleur) blogControleur.getControleur("errModification")).getErrMessage()
					.setText(e.getMessage());
			blogControleur.activer("errModification");
		}

	}

	@FXML
	void annuler(ActionEvent event) {
		System.out.println("annuler");
		blogControleur.activer("utilisateur");
	}

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
	void initialize() {
		assert modifierCompte != null : "fx:id=\"modifierCompte\" was not injected: check your FXML file 'GestionUtil.fxml'.";
		assert reMot2Passe != null : "fx:id=\"reMot2Passe\" was not injected: check your FXML file 'GestionUtil.fxml'.";
		assert annuler != null : "fx:id=\"annuler\" was not injected: check your FXML file 'GestionUtil.fxml'.";
		assert mot2Passe != null : "fx:id=\"mot2Passe\" was not injected: check your FXML file 'GestionUtil.fxml'.";
		assert adresse != null : "fx:id=\"adresse\" was not injected: check your FXML file 'GestionUtil.fxml'.";
		assert prenom != null : "fx:id=\"prenom\" was not injected: check your FXML file 'GestionUtil.fxml'.";
		assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'GestionUtil.fxml'.";

	}
}
