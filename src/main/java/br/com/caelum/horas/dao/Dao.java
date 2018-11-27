package br.com.caelum.horas.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import br.com.caelum.horas.util.UtilVerificacao;

/**
 * Interface padronizante DAO
 * 
 * @author Victtor
 * @param <T> Entity que será manipulada
 */
public interface Dao<T> {
	/**
	 * Busca uma objeto através do ID no banco de dados
	 * 
	 * @param id primary key
	 * @return objeto buscado no banco de dados
	 */
	public T findById(int id);

	/**
	 * Persiste um objeto no banco de dados
	 * 
	 * @param obj Objeto a ser persistido
	 */
	public void save(T obj);

	/**
	 * Atualiza um objeto no banco de dados
	 * 
	 * @param obj Objeto a ser atualizado
	 */
	public void update(T obj);

	/**
	 * Remove um objeto no banco de dados
	 * 
	 * @param obj Objeto a ser removido
	 */
	public void remove(T obj);

	/**
	 * Remove um objeto no banco de dados pelo ID
	 * 
	 * @param id primary key
	 */
	public void remove(int id);

	/**
	 * Busca todos os Elementos da Entity T
	 * 
	 * @return Lista de elementos T
	 */
	public List<T> findAll();

	/**
	 * Busca todos os Elementos da Entity que obedeça o Where
	 * 
	 * @param params Parametros para a query
	 * @return Lista dos elementos
	 */
	public List<T> findAll(Object... params);


}
