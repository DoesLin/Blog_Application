package fr.polytech.tours.hibernate.application.controleur.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.polytech.tours.hibernate.application.modele.Utilisateur;

/**
 * Classe contenant les methodes necessaires a l'acces des donnes de la classe
 * Utilisateur, elle herite de la classe abstraite DAO. Tous les methodes de la
 * classe mere seront implementes et adaptes a la composition de cette classe.
 * 
 * @author MOUTAS RIBEIRO et LIN
 *
 */
public class UtilisateurDAO extends DAO<Utilisateur> {

	public UtilisateurDAO(EntityManager em) {
		super(em);
	}

	@Override
	public boolean creer(Utilisateur entity) {
		try {
			em.persist(entity);
			System.out.println("Les donnees ont ete ajoutes a la table Utilisateur avec succes !");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifier(Utilisateur entity) {
		try {
			em.merge(entity);
			System.out.println("Les donnees ont ete modifiees de la table Utilisateur avec succes !");
			if (em.contains(entity)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean supprimer(Utilisateur entity) {
		try {
			Utilisateur u = em.find(Utilisateur.class, entity.getUtilisateurID());
			em.remove(u);
			System.out.println("Les donnees ont ete supprimees de la table Utilisateur avec succes !");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean supprimerParID(int entityId) {
		return false;
	}

	public boolean supprimerParID(String entityId) {
		try {
			Utilisateur u = em.find(Utilisateur.class, entityId);
			em.remove(u);
			System.out.println(
					"Les donnees ont ete supprimees de la table Utilisateur avec son identifiant avec succes !");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Utilisateur chercherParID(int id) {
		return null;
	}

	public Utilisateur chercherParID(String entityId) {
		try {
			Utilisateur u = em.find(Utilisateur.class, entityId);
			System.out.println("Les donnees ont ete trouvees dans la table Utilisateur avec succes !");
			return u;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> chercherTous() {
		List<Utilisateur> resultats = new ArrayList<Utilisateur>();
		try {
			Query query = em.createQuery("from Utilisateur");
			resultats = query.getResultList();
			System.out.println("Toutes les donnees ont ete trouvees dans la table Utilisateur avec succes !");
			return resultats;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultats;
	}
}
