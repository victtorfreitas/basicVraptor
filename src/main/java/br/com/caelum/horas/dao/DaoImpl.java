package br.com.caelum.horas.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import br.com.caelum.horas.util.UtilDao;

public class DaoImpl<T> implements Serializable, Dao<T> {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	private UtilDao<T> utilDao;

	public DaoImpl() {
	}

	public DaoImpl(EntityManager manager) {
		this.em = manager;
		this.utilDao = new UtilDao<T>(this.em);
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
	public List<T> listaTodos(String jpql) {
		return utilDao.createQueryResultList(jpql);
	}
	
	public T lista(String jpql, Object... params) {
		return utilDao.createQuerySingleResult(jpql, params);
	}

}
