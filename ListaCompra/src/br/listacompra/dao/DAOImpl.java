package br.listacompra.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public abstract class DAOImpl<T, I extends Serializable>
		implements DAO<T, I> {

	@PersistenceContext(unitName = "listaCompras")
	EntityManager em;

	public T save(T entity) {

		T saved = null;
		saved = em.merge(entity);

		return saved;
	}

	public void remove(T entity) {
		em.remove(entity);
	}

	public T getById(Class<T> classe, I pk) {

		try {
			return em.find(classe, pk);
		} catch (NoResultException e) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(Class<T> classe) {
		return em.createQuery("select o from " + classe.getSimpleName() + " o")
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAllNames(Class<T> classe) {
		return em.createQuery("select o.nome from " + classe.getSimpleName() + " o")
				.getResultList();
	}
}
