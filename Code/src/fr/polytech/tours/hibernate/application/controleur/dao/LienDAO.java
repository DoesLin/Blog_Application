package fr.polytech.tours.hibernate.application.controleur.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.polytech.tours.hibernate.application.modele.Lien;

/**
 * Classe contenant les methodes necessaires a l'acces des donnes de la classe
 * Lien, elle herite de la classe abstraite DAO. Tous les methodes de la classe
 * mere seront implementes et adaptes a la composition de cette classe.
 * 
 * @author MOUTAS RIBEIRO et LIN
 *
 */
public class LienDAO extends DAO<Lien> {

	public LienDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean creer(Lien entity) {
		// TODO Auto-generated method stub
		try {
			em.persist(entity);
			System.out.println("Les donnees ont ete ajoutes a la table Lien avec succes !");
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifier(Lien entity) {
		// TODO Auto-generated method stub
		try {
			em.merge(entity);
			System.out.println("Les donnees ont ete modifiees de la table Lien avec succes !");
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
	public boolean supprimer(Lien entity) {
		// TODO Auto-generated method stub
		try {
			Lien lien = em.find(Lien.class, entity.getLienID());
			em.remove(lien);
			System.out.println("Les donnees ont ete supprimees de la table Lien avec succes !");
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
			Lien lien = em.find(Lien.class, entityId);
			em.remove(lien);
			System.out.println("Les donnees ont ete supprimees de la table Lien avec son identifiant avec succes !");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Lien chercherParID(int id) {
		// TODO Auto-generated method stub
		try {
			Lien lien = em.find(Lien.class, id);
			System.out.println("Les donnees ont ete trouvees dans la table Lien  avec succes !");
			return lien;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lien> chercherTous() {
		// TODO Auto-generated method stub
		List<Lien> resultats = new ArrayList<Lien>();
		
		try {
			Query query = em.createQuery("from Lien");
			resultats = query.getResultList();
			System.out.println("Toutes les donnees ont ete trouvees dans la table Lien avec succes !");
			return resultats;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
