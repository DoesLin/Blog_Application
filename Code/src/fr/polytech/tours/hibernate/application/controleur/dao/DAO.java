package fr.polytech.tours.hibernate.application.controleur.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Classe abstraite representant la struture de ce que doit etre les accesseurs
 * de donnees.
 * 
 * @author MOUTAS RIEBIRO et LIN
 *
 * @param <T>
 *            represente le nom de la classe a laquelle nous souhaitons acceder
 *            aux donnees.
 */
public abstract class DAO<T> {

	/**
	 * em : attribut du type EntityManager accessible par toutes les classes
	 * heritant de cette classe.
	 */

	@PersistenceContext
	protected EntityManager em;

	/**
	 * Constructeur a un parametre permettant d'initialiser l'attribut em de
	 * cette classe.
	 * 
	 * @param entityManager
	 */
	public DAO(EntityManager entityManager) {
		this.em = entityManager;
	}

	/**
	 * Methode permettant la creation de l'objet, en base de donnes ca
	 * correspond a la creation d'une table.
	 * 
	 * @param entity
	 *            qui est du type de la classe pour laquelle nous souhaitons
	 *            creer une table.
	 * @return boolean true si la table s'est cree, false sinon.
	 */
	public abstract boolean creer(T entity);

	/**
	 * Methode permettant la modification de l'objet, en base de donnes ca
	 * correspond a la modification d'une ligne d'une table.
	 * 
	 * @param entity
	 *            qui est du type de la classe pour laquelle nous souhaitons
	 *            modifier une table.
	 * @return boolean true si un des enregistrements de la table s'est modifie,
	 *         false sinon.
	 */
	public abstract boolean modifier(T entity);

	/**
	 * Methode permettant la suppression de l'objet, en base de donnes ca
	 * correspond a la suppresion d'une ligne d'une table.
	 * 
	 * @param entity
	 *            qui est du type de la classe pour laquelle nous souhaitons
	 *            supprimer une ligne d'une table.
	 * @return boolean true si un des enregistrements de la table a ete
	 *         supprime, false sinon.
	 */
	public abstract boolean supprimer(T entity);

	/**
	 * Methode permettant la suppression de l'objet en utilisant son identifiant
	 * dans la table.
	 * 
	 * @param entity
	 *            qui est du type de la classe pour laquelle nous souhaitons
	 *            supprimer une ligne d'une table.
	 * @return boolean true si un des enregistrements de la table a ete
	 *         supprime, false sinon.
	 */
	public abstract boolean supprimerParID(int entityId);

	/**
	 * Recherce d'un enregistrement en passant son identifiant en parametre.
	 * 
	 * @param id
	 *            , un entier representant l'identifiant de l'enregistrement.
	 * @return l'enregistrement s'il existe dans la table.
	 */
	public abstract T chercherParID(int id);

	/**
	 * Reccherche de tous les enregistrements de la table.
	 * 
	 * @return la liste des enregistrements.
	 */
	public abstract List<T> chercherTous();
}
