package br.com.caelum.horas.dao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.caelum.horas.enums.SQLenum;
import br.com.caelum.horas.util.UtilVerificacao;

@Stateless
public class GenericDao<T> implements Serializable, Dao<T> {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Class<T> classeObject;

	/**
	 * Construtor apenas para Framework
	 * 
	 * @deprecated
	 */
	public GenericDao() {
	}

	/**
	 * Construtor Padrão
	 * 
	 * @param classe Classe do Entity
	 */
	public GenericDao(Class<T> classeObject) {
		this.classeObject = classeObject;
	}

	// Metodos sobrescritos da interface

	@Override
	public T findById(int id) {
		return em.find(this.classeObject, id);
	}

	@Override
	public T findByParams(String where, Object... params) {
		return getSingleResult(queryWithWhere(where, params));
	}

	@Transactional
	@Override
	public void save(T obj) {
		em.persist(obj);
	}

	@Transactional
	@Override
	public void update(T obj) {
		em.merge(obj);
	}

	@Transactional
	@Override
	public void remove(T obj) {
		em.remove(em.merge(obj));
	}

	@Override
	public void remove(int id) {
		T instancia = findById(id);
		remove(instancia);
	}

	@Override
	public List<T> findAll() {
		return getResultList(createQueryBasic());
	}

	@Override
	public List<T> findAll(String where, Object... params) {
		return getResultList(queryWithWhere(where, params));
	}

	// Metodos PRIVADOS para tratamento das Query

	/**
	 * Cria um TypeQuery com where agregado
	 * @param where que será agregado
	 * @param params Valores que alimentarão o where
	 * @return Um typeQuery carregado com todos os valores setados
	 */
	private TypedQuery<T> queryWithWhere(String where, Object... params) {
		return createQuery(sqlBasic() + where, params);
	}

	/**
	 * Implementa os parametros na Query
	 * 
	 * @param query  Query a ser inserida
	 * @param params valores a serem inseridos
	 */
	private void setParams(TypedQuery<T> query, Object... params) {
		if (UtilVerificacao.isParams(params)) {
			for (int i = 0; i <= params.length; i++) {
				query.setParameter(i+1, params[i]);
			}
		}
	}

	/**
	 * Faz a busca no banco e retorna um unico Objeto do tipo T
	 * @param typedQuery é a query já preparada
	 * @return unico objeto buscado no banco
	 */
	private T getSingleResult(TypedQuery<T> typedQuery) {
		try {
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, e);
		}
		return null;
	}

	/**
	 * Faz a busca no banco e retorna uma lista de objetos do tipo T
	 * @param typedQuery é a query já preparada
	 * @return lista de objetos buscado no banco
	 */
	private List<T> getResultList(TypedQuery<T> typedQuery) {
		try {
			return typedQuery.getResultList();
		} catch (NoResultException e) {
			Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, e);
		}
		return null;
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
		setParams(query, params);
		return query;
	}

	/**
	 * Metodo que cria uma Query (Sem parametros)
	 * 
	 * @param jpql SQL Referente a Query
	 * @return retorna um TypedQuery podendo pegar Lista ou Single apartir disso
	 */
	private TypedQuery<T> createQuery(String jpql) {
		TypedQuery<T> query = (TypedQuery<T>) em.createQuery(jpql, classeObject);
		return query;
	}
	
	/**
	 * Cria uma query basica 
	 * @return o TypeQuery basico criado
	 */
	private TypedQuery<T> createQueryBasic() {
		return createQuery(sqlBasic());
	}
	/**
	 * Verifica qual sqlEnum equivale ao objeto classeObject <br>
	 * E com isso retorna a queryBasic que deve ser usada
	 * @return queryBasica que deve ser utilizada
	 */
	private String sqlBasic() {
		for (SQLenum sqlEnum : SQLenum.values()) {
			if (sqlEnum.getClasse().equals(classeObject)) {
				return sqlEnum.getQuery();
			}
		}
		return null;

	}

}
