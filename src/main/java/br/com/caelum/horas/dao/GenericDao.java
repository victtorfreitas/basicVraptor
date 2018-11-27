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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;

import br.com.caelum.horas.util.UtilVerificacao;

@Stateless
public class GenericDao<T> implements Serializable, Dao<T> {

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	private Class<T> classeObject;
	
	private CriteriaBuilder criteriaBuilder;

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
	 * @param manager deve já estar instanciado
	 * @param classe  Classe do Entity
	 */
	public GenericDao(EntityManager manager, Class<T> classeObject) {
		this.em = manager;
		this.classeObject = classeObject;
		this.criteriaBuilder = em.getCriteriaBuilder();
	}

	// Metodos sobrescritos da interface

	@Override
	public T findById(int id) {
		return em.find(this.classeObject, id);
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
		return getResultList(getTypeQuery(getCriteriaQuery()));
	}

	@Override
	public List<T> findAll(Object... params) {
		TypedQuery<T> typedQuery = getTypeQuery(getCriteriaQuery());
		setParams(typedQuery, params);
		return getResultList(typedQuery);
	}

	// Metodos para tratamento das Query

	/**
	 * Implementa os parametros na Query
	 * 
	 * @param query  Query a ser inserida
	 * @param params valores a serem inseridos
	 */
	private void setParams(TypedQuery<T> query, Object... params) {
		if (UtilVerificacao.isParams(params)) {
			for (int i = 1; i <= params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
	}

	/**
	 * Trata o possível erro da Query não achar nenhum resultado<br>
	 * E retorna uma lista buscada no banco
	 * 
	 * @param query
	 * @return Lista da pesquisa no BD
	 */
	private List<T> getResultList(TypedQuery<T> query) {
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, e);
		}
		return null;
	}

	/**
	 * Cria uma query select * from da classe selecionada
	 * 
	 * @param em     EntityManager instanciado
	 * @param classe Que deve criar a query
	 * @return TypeQuery do Tipo passado
	 */
	private TypedQuery<T> getTypeQuery(CriteriaQuery<T> criteriaQuery) {
		return em.createQuery(criteriaQuery);
	}

	public CriteriaQuery<T> getCriteriaQuery() {
		CriteriaQuery<T> query = this.criteriaBuilder.createQuery(this.classeObject);
		CriteriaQuery<T> criteriaQuery = query.select(query.from(this.classeObject));
		return criteriaQuery;
	}

	private void getCriteriaParamsSeted(Object... params) {
		CriteriaQuery<T> criteriaQuery = getCriteriaQuery();
		for(int i =1 ; i<= params.length; i++) {
			CriteriaQuery<T> where = criteriaQuery.where(criteriaBuilder.equal( (Expression<?>) params[i], criteriaBuilder.parameter(this.classeObject)));
		}
		
	}

}
