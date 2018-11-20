package br.com.caelum.horas.dao;

import java.util.List;

public interface Dao<T> {
	public void adiciona(T entity);
	public void remove(T entity);
	public List<T> listaTodos(T entity);
	public void atualiza(T t);
}
