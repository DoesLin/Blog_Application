package fr.polytech.tours.hibernate.application.controleur.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.polytech.tours.hibernate.application.modele.Message;
import fr.polytech.tours.hibernate.application.modele.Utilisateur;

/**
 * Classe contenant les methodes necessaires a l'acces des donnes de la classe
 * Mesage, elle herite de la classe abstraite DAO. Tous les methodes de la classe
 * mere seront implementes et adaptes a la composition de cette classe.
 * 
 * @author MOUTAS RIBEIRO et LIN
 *
 */
public class MessageDAO extends DAO<Message> {

	public MessageDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public boolean creer(Message entity) {
		try {
			em.persist(entity);
			System.out.println("Les donnees ont ete ajoutes a la table Message avec succes !");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifier(Message entity) {
		try {
			em.merge(entity);
			System.out.println("Les donnees ont ete modifiees de la table Message avec succes !");
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
	public boolean supprimer(Message entity) {
		try {
			Message mg = em.find(Message.class, entity.getMessageID());
			em.remove(mg);
			System.out.println("Les donnees ont ete supprimees de la table Message avec succes !");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean supprimerParID(int entityId) {
		try {
			Message mg = em.find(Message.class, entityId);
			em.remove(mg);
			System.out.println("Les donnees ont ete supprimees de la table Message avec son identifiant avec succes !");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Message chercherParID(int id) {
		try {
			Message mg = em.find(Message.class, id);
			System.out.println("Les donnees ont ete trouvees dans la table Message  avec succes !");
			return mg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> chercherTous() {
		List<Message> resultats = new ArrayList<Message>();

		try {
			Query query = em.createQuery("from Message");
			resultats = query.getResultList();
			System.out.println("Toutes les donnees ont ete trouvees dans la table Message avec succes !");
			return resultats;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Message> chercherParMot(String motCher) {

		motCher = "%" + motCher + "%";
		List<Message> resultats = new ArrayList<Message>();

		try {
			
			String queryString = "SELECT msg FROM Message msg WHERE msg.MessageID IN ( "
					+ "SELECT msg2.MessageID FROM Message msg2, MotCle mc "
					+ "WHERE msg2.Texte like ?1 OR msg2.Titre like ?1 OR msg2.Date like ?1 OR mc.MotCle like ?1)";
			Query query = em.createQuery(queryString);
			query = query.setParameter(1, motCher);
			resultats = query.getResultList();
			System.out.println("Toutes les donnees ont ete trouvees dans la table Message avec succes !");
			return resultats;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Message> chercherParUtilisateur(Utilisateur utilisateur) {

		List<Message> resultats = new ArrayList<Message>();

		try {
			
			String queryString = "FROM Message msg WHERE msg.Utilisateur = ?1";
			Query query = em.createQuery(queryString);
			query = query.setParameter(1, utilisateur);
			resultats = query.getResultList();
			System.out.println("Toutes les donnees ont ete trouvees dans la table Message avec succes !");
			return resultats;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
