package fr.polytech.tours.hibernate.application;

import java.io.IOException;
import java.util.HashMap;

import fr.polytech.tours.hibernate.application.controleur.BlogControleur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;

/**
 * Classe permettant le lancement de l'application.
 * 
 * @author MOUTAS RIBEIRO et LIN
 *
 */
public class BlogApplication extends Application {

	/**
	 * Attributs de la classe
	 */
	private BlogControleur controleur;
	private Scene indexScene;
	private HashMap<String, Pane> sceneMap = new HashMap<>();

	/**
	 * Methode main permettant de faire appel a la fonction de lancement de
	 * l'application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		launch(args);

	}

	/**
	 * Methode permettant d'initialiser tous les composants de l'application.
	 * 
	 * @param primaryStage
	 * @throws IOException
	 */
	private void initScene(Stage primaryStage) throws IOException {

		controleur = new BlogControleur(primaryStage);

		String cheminVue = "./vue/";

		FXMLLoader indexLoader = new FXMLLoader(getClass().getResource(cheminVue + "Index.fxml"));
		indexLoader.setController(controleur.getControleur("index"));
		Pane indexPane = indexLoader.load();
		indexScene = new Scene((Parent) indexPane);
		sceneMap.put("index", indexPane);

		FXMLLoader loader;
		Pane pane;

		// invite
		cheminVue = "./vue/invite/";

		loader = new FXMLLoader(getClass().getResource(cheminVue + "Inscription.fxml"));
		loader.setController(controleur.getControleur("inscription"));
		pane = loader.load();
		sceneMap.put("inscription", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "Connexion.fxml"));
		loader.setController(controleur.getControleur("connexion"));
		pane = loader.load();
		sceneMap.put("connexion", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "ResultatCherNonConn.fxml"));
		loader.setController(controleur.getControleur("resultatCherNonConn"));
		pane = loader.load();
		sceneMap.put("resultatCherNonConn", pane);

		// erreur
		cheminVue = "./vue/erreur/";

		loader = new FXMLLoader(getClass().getResource(cheminVue + "ErrCreation.fxml"));
		loader.setController(controleur.getControleur("errCreation"));
		pane = loader.load();
		sceneMap.put("errCreation", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "ErrConnexion.fxml"));
		loader.setController(controleur.getControleur("errConnexion"));
		pane = loader.load();
		sceneMap.put("errConnexion", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "ErrModification.fxml"));
		loader.setController(controleur.getControleur("errModification"));
		pane = loader.load();
		sceneMap.put("errModification", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "ErrModiMessage.fxml"));
		loader.setController(controleur.getControleur("errModiMessage"));
		pane = loader.load();
		sceneMap.put("errModiMessage", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "ErrCreerMessage.fxml"));
		loader.setController(controleur.getControleur("errCreerMessage"));
		pane = loader.load();
		sceneMap.put("errCreerMessage", pane);

		// utilisateur
		cheminVue = "./vue/utilisateur/";

		loader = new FXMLLoader(getClass().getResource(cheminVue + "VisualiserMess.fxml"));
		loader.setController(controleur.getControleur("visualiserMess"));
		pane = loader.load();
		sceneMap.put("visualiserMess", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "Utilisateur.fxml"));
		loader.setController(controleur.getControleur("utilisateur"));
		pane = loader.load();
		sceneMap.put("utilisateur", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "MesMessages.fxml"));
		loader.setController(controleur.getControleur("mesMess"));
		pane = loader.load();
		sceneMap.put("mesMess", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "ResultatCher.fxml"));
		loader.setController(controleur.getControleur("resultatCher"));
		pane = loader.load();
		sceneMap.put("resultatCher", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "EcrireMess.fxml"));
		loader.setController(controleur.getControleur("ecrireMess"));
		pane = loader.load();
		sceneMap.put("ecrireMess", pane);

		loader = new FXMLLoader(getClass().getResource(cheminVue + "ModifierMess.fxml"));
		loader.setController(controleur.getControleur("modifierMess"));
		pane = loader.load();
		sceneMap.put("modifierMess", pane);

		// gestion
		cheminVue = "./vue/gestion/";

		loader = new FXMLLoader(getClass().getResource(cheminVue + "GestionUtil.fxml"));
		loader.setController(controleur.getControleur("gestUtil"));
		pane = loader.load();
		sceneMap.put("gestUtil", pane);

		controleur.setMain(indexScene);
		controleur.setSceneMap(sceneMap);

		// Test
		// controleur.activer("modifierMess");
		// controleur.activer("utilisateur");

	}

	/**
	 * Fonction de lancement de l'application, avec la mise en place du
	 * controlleur, la lecture du fichier fxml et affichage de l'application.
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			System.out.println("Start");

			initScene(primaryStage);

			primaryStage.setTitle("Blog Appliction");
			primaryStage.setScene(indexScene);

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Fermeture de l'application ainsi que des dependances dont elle avait
	 * besoin.
	 * 
	 * @see javafx.application.Application#stop()
	 */
	@Override
	public void stop() {
		System.out.println("End");
	}
}
