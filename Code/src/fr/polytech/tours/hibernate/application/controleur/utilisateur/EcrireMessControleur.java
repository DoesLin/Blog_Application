package fr.polytech.tours.hibernate.application.controleur.utilisateur;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import fr.polytech.tours.hibernate.application.controleur.BlogControleur;
import fr.polytech.tours.hibernate.application.controleur.dao.MessageDAO;
import fr.polytech.tours.hibernate.application.controleur.dao.MotCleDAO;
import fr.polytech.tours.hibernate.application.controleur.erreur.ErrCreerMessageControleur;
import fr.polytech.tours.hibernate.application.controleur.gestion.GestionUtilControleur;
import fr.polytech.tours.hibernate.application.modele.Image;
import fr.polytech.tours.hibernate.application.modele.Lien;
import fr.polytech.tours.hibernate.application.modele.Message;
import fr.polytech.tours.hibernate.application.modele.MotCle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Controlleur lors de l'ecriture d'un message par un utilisateur sur le blog
 * internet.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class EcrireMessControleur {

	private BlogControleur blogControleur;
	private List<Image> listeImages = new ArrayList<Image>();
	private List<Lien> listeLiens = new ArrayList<Lien>();;
	private final FileChooser fichiersSelecteur = new FileChooser();
	private List<Tab> imagesTabs = new ArrayList<Tab>();
	private List<Tab> liensTabs = new ArrayList<Tab>();

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="seDeconnecter"
	private MenuItem seDeconnecter; // Value injected by FXMLLoader

	@FXML // fx:id="annuler"
	private Button annuler; // Value injected by FXMLLoader

	@FXML // fx:id="titre"
	private TextField titre; // Value injected by FXMLLoader

	@FXML // fx:id="ajouterImag"
	private Button ajouterImag; // Value injected by FXMLLoader

	@FXML // fx:id="ajouterLien"
	private Button ajouterLien; // Value injected by FXMLLoader

	@FXML // fx:id="effacerLiens"
	private Button effacerLiens; // Value injected by FXMLLoader

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

	@FXML // fx:id="creerMess"
	private Button creerMess; // Value injected by FXMLLoader

	@FXML // fx:id="compte"
	private MenuButton compte; // Value injected by FXMLLoader

	@FXML // fx:id="imagesVue"
	private TabPane imagesVue; // Value injected by FXMLLoader

	@FXML // fx:id="liensVue"
	private TabPane liensVue; // Value injected by FXMLLoader

	@FXML // fx:id="nomLien"
	private TextField nomLien; // Value injected by FXMLLoader

	@FXML // fx:id="texteLien"
	private TextArea texteLien; // Value injected by FXMLLoader

	public BlogControleur getBlogControleur() {
		return blogControleur;
	}

	public void setBlogControleur(BlogControleur blogControleur) {
		this.blogControleur = blogControleur;
	}

	public EcrireMessControleur(BlogControleur blogControleur) {
		super();
		this.setBlogControleur(blogControleur);
	}

	public TextField getTexteCompte() {
		return texteCompte;
	}

	/**
	 * Methode permettant la deconnexion d'un utilisateur.
	 * 
	 * @param event
	 */
	@FXML
	void seDeconnecter(ActionEvent event) {
		blogControleur.setUtilisateurCourant(null);
		blogControleur.preparerIndexNonConn();

		System.out.println("seDeconnecter");
		blogControleur.activer("index");
	}

	/**
	 * Methode permetant la gestion de compte utilisateur.
	 * 
	 * @param event
	 */
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

	/**
	 * Lors de la creation d'un message des mots cles doivent etre rentres pour
	 * un message, mais ils ne doivent pas etre dupliques, cette methode permet
	 * de verifier que les mots cles sont valides.
	 * 
	 * @param msg
	 * @throws Exception
	 */
	private void preparerMotCles(Message msg) throws Exception {
		List<MotCle> listeMC = new ArrayList<MotCle>();
		MotCleDAO mcDAO = new MotCleDAO(blogControleur.getEm());
		String strMotCles = motCles.getText();
		String[] res = strMotCles.split(";");
		List<String> list = new ArrayList<String>();

		// detecter dup
		for (String motCle : res) {
			MotCle curMC = new MotCle(motCle);
			listeMC.add(curMC);
			list.add(motCle);
		}
		
		if (listeMC.size() > 5) {
			throw new Exception("Nombre de mot-cles ne peut pas etre superieur que 5");
		}

		Set<String> set = new HashSet<String>(list);
		if (set.size() < list.size()) {
			throw new Exception("Il y a des duplicatas dans les mot-cles !");
		}

		List<MotCle> resMC = mcDAO.chercherTous();
		if (resMC == null) {
			throw new Exception("Faute mot-cles");
		}
		boolean exist = false;
		for (int j = 0; j < listeMC.size(); j++) {
			for (int i = 0; i < resMC.size() && !exist; i++) {
				if (listeMC.get(j).getMotCle().equals(resMC.get(i).getMotCle())) {
					exist = true;
					System.out.println("Maj un mot-cle existant");
					listeMC.set(j, resMC.get(i));
					listeMC.get(j).getListeMessages().add(msg);
					if (mcDAO.modifier(listeMC.get(j)) == false) {
						throw new Exception("Faute message");
					}
				} else {
					exist = false;
				}
			}
			if (!exist) {
				System.out.println("Ajoutant un nouveau mot-cle");
				if (mcDAO.creer(listeMC.get(j)) == false) {
					throw new Exception("Faute message");
				}
			}
		}

		msg.setListeMotCle(listeMC);
	}

	/**
	 * Methode permettanta la creation d'un message a publier.
	 * 
	 * @param event
	 */
	@FXML
	void creerMess(ActionEvent event) {

		try {

			blogControleur.beginTran();

			Message msg = new Message();
			if (!blogControleur.verifierTextField(titre) || !blogControleur.verifierTextArea(texte)
					|| !blogControleur.verifierTextField(motCles)) {
				throw new Exception("Il faut remplir les champs !");
			}
			msg.setUtilisateur(blogControleur.getUtilisateurCourant());
			msg.setTitre(titre.getText());
			msg.setTexte(texte.getText());

			preparerMotCles(msg);

			
			if (listeImages.size() > 5) {
				throw new Exception("Nombre des images ne peut pas etre superieur que 5");
			}
			for (Image image : listeImages) {
				image.setMessage(msg);
			}
			msg.setListeImages(listeImages);

			if (listeLiens.size() > 5) {
				throw new Exception("Nombre des liens ne peut pas etre superieur que 5");
			}
			for (Lien lien : listeLiens) {
				lien.setMessage(msg);
			}
			msg.setListeLiens(listeLiens);

			MessageDAO messDAO = new MessageDAO(blogControleur.getEm());

			if (messDAO.creer(msg) == false) {
				throw new Exception("Faute message");
			}
			blogControleur.commitTran();

			ObservableList<Message> items = FXCollections
					.observableArrayList(messDAO.chercherParUtilisateur(blogControleur.getUtilisateurCourant()));
			((MesMessagesControleur) blogControleur.getControleur("mesMess")).getAffiche().setItems(items);

			System.out.println("voirMessages");
			blogControleur.activer("mesMess");
		} catch (Exception e) {
			blogControleur.rollbackTran();
			System.out.println(e);
			System.out.println("errCreerMessage");
			((ErrCreerMessageControleur) blogControleur.getControleur("errCreerMessage")).getErrMessage()
					.setText(e.getMessage());
			blogControleur.activer("errCreerMessage");
		}
	}

	/**
	 * Methode permettant d'nnuler la creation d'un message.
	 * 
	 * @param event
	 */
	@FXML
	void annuler(ActionEvent event) {
		// MessageDAO messDAO = new MessageDAO(blogControleur.getEm());
		// ObservableList<Message> items = FXCollections
		// .observableArrayList(messDAO.chercherParUtilisateur(blogControleur.getUtilisateurCourant()));
		// ((MesMessagesControleur)
		// blogControleur.getControleur("mesMess")).getAffiche().setItems(items);

		System.out.println("voirMessages");
		blogControleur.activer("mesMess");
	}

	/**
	 * Methode permettant de gerer l'ajout d'une image au message publie.
	 * 
	 * @param event
	 */
	@FXML
	void ajouterImag(ActionEvent event) {

		imagesTabs.clear();
		imagesVue.getTabs().clear();

		fichiersSelecteur.setTitle("View Pictures");
		fichiersSelecteur.setInitialDirectory(new File(System.getProperty("user.home")));
		fichiersSelecteur.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

		List<File> listFichiers = fichiersSelecteur.showOpenMultipleDialog(blogControleur.getPrimaryStage());

		if (listFichiers != null) {
			for (int i = 0; i < listFichiers.size(); i++) {
				Tab tab = new Tab();
				tab.setText("Image " + String.valueOf(i + 1));
				tab.setContent(new TextField(listFichiers.get(i).getName()));
				imagesTabs.add(tab);

				Image image = new Image();
				image.setParcourFichier(listFichiers.get(i).getAbsolutePath());
				listeImages.add(image);
			}
		}
		imagesVue.getTabs().addAll(imagesTabs);

	}

	/**
	 * Methode permettant l'ajout de liens a un message lors de sa creation.
	 * 
	 * @param event
	 */
	@FXML
	void ajouterLien(ActionEvent event) {
		if (!blogControleur.verifierTextField(nomLien) || !blogControleur.verifierTextArea(texteLien)) {
			System.out.println("Il faut remplir les champs !");
			return;
		}

		Tab tab = new Tab();
		tab.setText(nomLien.getText());
		tab.setContent(new TextField(texteLien.getText()));
		liensTabs.add(tab);

		Lien lien = new Lien();
		lien.setNomLien(nomLien.getText());
		lien.setTexteVisualiser(texteLien.getText());
		listeLiens.add(lien);

		liensVue.getTabs().add(tab);

	}

	/**
	 * Methode permettant de effacer les liens.
	 * 
	 * @param event
	 */
	@FXML
	void effacerLiens(ActionEvent event) {
		listeLiens.clear();
		liensTabs.clear();
		liensVue.getTabs().clear();
	}

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
	void initialize() {
		assert effacerLiens != null : "fx:id=\"effacerLiens\" was not injected: check your FXML file 'EcrireMess.fxml'.";
		assert seDeconnecter != null : "fx:id=\"seDeconnecter\" was not injected: check your FXML file 'EcrireMess.fxml'.";
		assert ajouterLien != null : "fx:id=\"ajouterLien\" was not injected: check your FXML file 'EcrireMess.fxml'.";
		assert titre != null : "fx:id=\"titre\" was not injected: check your FXML file 'EcrireMess.fxml'.";
		assert ajouterImag != null : "fx:id=\"ajouterImag\" was not injected: check your FXML file 'EcrireMess.fxml'.";
		assert texteCompte != null : "fx:id=\"texteCompte\" was not injected: check your FXML file 'EcrireMess.fxml'.";
		assert index != null : "fx:id=\"index\" was not injected: check your FXML file 'EcrireMess.fxml'.";
		assert nomLien != null : "fx:id=\"nomLien\" was not injected: check your FXML file 'EcrireMess.fxml'.";
		assert texte != null : "fx:id=\"texte\" was not injected: check your FXML file 'EcrireMess.fxml'.";
		assert creerMess != null : "fx:id=\"creerMess\" was not injected: check your FXML file 'EcrireMess.fxml'.";
		assert annuler != null : "fx:id=\"annuler\" was not injected: check your FXML file 'EcrireMess.fxml'.";
		assert liensVue != null : "fx:id=\"liensVue\" was not injected: check your FXML file 'EcrireMess.fxml'.";
		assert texteLien != null : "fx:id=\"texteLien\" was not injected: check your FXML file 'EcrireMess.fxml'.";
		assert imagesVue != null : "fx:id=\"imagesVue\" was not injected: check your FXML file 'EcrireMess.fxml'.";
		assert gererCompte != null : "fx:id=\"gererCompte\" was not injected: check your FXML file 'EcrireMess.fxml'.";
		assert motCles != null : "fx:id=\"motCles\" was not injected: check your FXML file 'EcrireMess.fxml'.";
		assert compte != null : "fx:id=\"compte\" was not injected: check your FXML file 'EcrireMess.fxml'.";

	}
}
