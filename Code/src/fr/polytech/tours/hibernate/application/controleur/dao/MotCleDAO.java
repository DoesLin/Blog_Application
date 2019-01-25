package fr.polytech.tours.hibernate.application.controleur.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.polytech.tours.hibernate.application.modele.MotCle;

/**
 * Classe contenant les methodes necessaires a l'acces des donnes de la classe
 * MotCle, elle herite de la classe abstraite DAO. Tous les methodes de la classe
 * mere seront implementes et adaptes a la composition de cette classe.
 * 
 * @author MOUTAS RIBEIRO et LIN
 *
 */
public class MotCleDAO extends DAO<MotCle> {

	public MotCleDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean creer(MotCle entity) {
		// TODO Auto-generated method stub
		
		try {
			em.persist(entity);
			System.out.println("Les donnees ont ete ajoutes a la table MotsCles avec succes !");
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifier(MotCle entity) {
		// TODO Auto-generated method stub
		try {
			em.merge(entity);
			System.out.println("Les donnees ont ete modifiees de la table MotsCles avec succes !");
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
	public boolean supprimer(MotCle entity) {
		// TODO Auto-generated method stub
		try {
			MotCle mc = em.find(MotCle.class, entity.getMotCleID());
			em.remove(mc);
			System.out.println("Les donnees ont ete supprimees de la table MotsCles avec succes !");
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
			MotCle mc = em.find(MotCle.class, entityId);
			em.remove(mc);
			System.out.println("Les donnees ont ete supprimees de la table MotsCles avec son identifiant avec succes !");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public MotCle chercherParID(int id) {
		// TODO Auto-generated method stub
		try {
			MotCle mc = em.find(MotCle.class, id);
			System.out.println("Les donnees ont ete trouvees dans la table MotsCles avec succes !");
			return mc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MotCle> chercherTous() {
		// TODO Auto-generated method stub
		List<MotCle> resultats = new ArrayList<MotCle>();
		
		try {
			Query query = em.createQuery("from MotCle");
			resultats = query.getResultList();
			System.out.println("Toutes les donnees ont ete trouvees dans la table MotsCles avec succes !");
			return resultats;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
