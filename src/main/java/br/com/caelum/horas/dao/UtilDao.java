package br.com.caelum.horas.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UtilDao<T> {
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
	 * @param jpql
	 *            SQL Referente a Query
	 * @return retorna um TypedQuery podendo pegar Lista ou Single apartir disso
	 */
	@SuppressWarnings("unchecked")
	public TypedQuery<T> createQuery(String jpql) {
		TypedQuery<T> query = (TypedQuery<T>) manager.createQuery(jpql, objetoTipo.getClass());
		return query;
	}

	/**
	 * Metodo que cria uma Query
	 * 
	 * @param jpql
	 *            SQL Referente a Query
	 * @param params
	 *            Parametros da query
	 * @return retorna um TypedQuery podendo pegar Lista ou Single apartir disso
	 */
	public TypedQuery<T> createQuery(String jpql, Object... params) {
		TypedQuery<T> query = createQuery(jpql);
		setParameter(query, params);
		return query;
	}
}
