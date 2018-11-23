package br.com.caelum.horas.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.vraptor.modelo.Usuario;

@Stateless
public class UsuarioDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;

	private String jpql;

	private DaoImpl<Usuario> dao;

	public UsuarioDao() {
	}

	@PostConstruct
	void init() {
		this.dao = new DaoImpl<Usuario>(manager);
		this.jpql ="select u from Usuario u ";
	}

	public void adiciona(Usuario usuario) {
		dao.adiciona(usuario);
	}

	public List<Usuario> lista() {
		return dao.listaTodos(jpql);
	}

	public Usuario busca(String login, String senha) {
		String query = jpql + "where u.login = ?1 and u.senha = ?2";
		return dao.lista(query, login, senha);
	}

	public void remove(Usuario usuario) {
		dao.remove(usuario);
	}

	public void remove(int id) {
		// TODO Auto-generated method stub
		
	}

}
