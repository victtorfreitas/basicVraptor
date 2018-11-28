package br.com.caelum.horas.dao;

import java.util.List;

/**
 * Interface padronizante DAO
 * 
 * @author Victtor
 * @param <T> Entity que será manipulada
 */
public interface Dao<T>{
	/**
	 * Busca uma objeto através do ID no banco de dados
	 * 
	 * @param id primary key
	 * @return objeto buscado no banco de dados
	 */
	public T findById(int id);
	
	/**
	 * Busca por parametros feitos na where
	 * @param where preparado anteriomente
	 * @param params valores para o where 
	 * @return Objeto do tipo T
	 */
	public T findByParams(String where, Object... params);

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
	 * @param where Necessario o where utilizado
	 * @param params Parametros para a query
	 * @return Lista dos elementos
	 */
	public List<T> findAll(String where, Object... params);


}
