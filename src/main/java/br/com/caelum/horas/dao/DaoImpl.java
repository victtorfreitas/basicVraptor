package br.com.caelum.horas.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class DaoImpl<T> implements Serializable, Dao<T> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public DaoImpl() {
	}

	@Override
	@Transactional
	public void adiciona(T t) {
		em.persist(t);
	}

	@Override
	@Transactional
	public void remove(T t) {
		em.remove(t);
	}

	@Override
	public void atualiza(T t) {
		em.merge(t);
	}

	@Override
	public List<T> listaTodos(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
