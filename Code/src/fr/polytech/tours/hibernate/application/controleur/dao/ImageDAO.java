package fr.polytech.tours.hibernate.application.controleur.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.polytech.tours.hibernate.application.modele.Image;

/**
 * Classe contenant les methodes necessaires a l'acces des donnes de la classe
 * Image, elle herite de la classe abstraite DAO. Tous les methodes de la classe
 * mere seront implementes et adaptes a la composition de cette classe.
 * 
 * @author MOUTAS RIBEIRO et LIN
 *
 */
public class ImageDAO extends DAO<Image> {
	/**
	 * Constrcuteur qui fait appel au contructeur de la classe mere, ici DAO,
	 * pour l'initialisation de l'attribut em du type EntityManager.
	 * 
	 * @param entityManager
	 */
	public ImageDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean creer(Image entity) {
		// TODO Auto-generated method stub
		try {
			em.persist(entity);
			System.out.println("Les donnees ont ete ajoutes a la table Image avec succes !");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifier(Image entity) {
		// TODO Auto-generated method stub
		try {
			em.merge(entity);
			System.out.println("Les donnees ont ete modifiees de la table Image avec succes !");
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
	public boolean supprimer(Image entity) {
		// TODO Auto-generated method stub
		try {
			Image image = em.find(Image.class, entity.getImageID());
			em.remove(image);
			System.out.println("Les donnees ont ete supprimees de la table Image avec succes !");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean supprimerParID(int entityId) {
		// TODO Auto-generated method stub
		try {
			Image image = em.find(Image.class, entityId);
			em.remove(image);
			System.out.println("Les donnees ont ete supprimees de la table Image avec son identifiant avec succes !");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Image chercherParID(int id) {
		// TODO Auto-generated method stub
		try {
			Image image = em.find(Image.class, id);
			System.out.println("Les donnees ont ete trouvees dans la table Image  avec succes !");
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> chercherTous() {
		// TODO Auto-generated method stub
		List<Image> resultats = new ArrayList<Image>();

		try {
			Query query = em.createQuery("from Image");
			resultats = query.getResultList();
			System.out.println("Toutes les donnees ont ete trouvees dans la table Image avec succes !");
			return resultats;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
