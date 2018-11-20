package br.com.caelum.horas.util;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UtilDao<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	private T objetoTipo = (T) new Object();

	public UtilDao() {
	}

	public UtilDao(EntityManager manager) {
		this.manager = manager;
	}

	private void setParameter(TypedQuery<T> query, Object... params) {
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
	}

	/**
	 * Metodo que cria uma Query (Sem parametros)
	 * 
	 * @param jpql SQL Referente a Query
	 * @return retorna um TypedQuery podendo pegar Lista ou Single apartir disso
	 */
	@SuppressWarnings("unchecked")
	private TypedQuery<T> createQuery(String jpql) {
		TypedQuery<T> query = (TypedQuery<T>) manager.createQuery(jpql, objetoTipo.getClass());
		return query;
	}

	/**
	 * Metodo que cria uma Query
	 * 
	 * @param jpql   SQL Referente a Query
	 * @param params Parametros da query
	 * @return retorna um TypedQuery podendo pegar Lista ou Single apartir disso
	 */
	private TypedQuery<T> createQuery(String jpql, Object... params) {
		TypedQuery<T> query = createQuery(jpql);
		if (UtilVerificacao.isParams(params)) {
			setParameter(query, params);
		}
		return query;
	}

	public T createQuerySingleResult(String jpql, Object... params) {
		TypedQuery<T> query = this.createQuery(jpql, params);
//		if (query.getFirstResult() != 0) {
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
//		}
	}

	public List<T> createQueryResultList(String jpql, Object... params) {
		TypedQuery<T> query = createQuery(jpql, params);
		if (query != null) {
			return query.getResultList();
		}
		return null;
	}

}
