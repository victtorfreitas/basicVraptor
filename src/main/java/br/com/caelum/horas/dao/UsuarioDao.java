package br.com.caelum.horas.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.vraptor.modelo.Usuario;

@RequestScoped
public class UsuarioDao {

	private EntityManager manager;
	private UtilDao<Usuario> utilDao;
	
	public UsuarioDao() {
	}

	@Inject
	public UsuarioDao(EntityManager manager) {
		this.manager = manager;
		this.utilDao = new UtilDao<Usuario>(manager);
	}

	public void adicionar(Usuario usuario) {
		manager.getTransaction().begin();
		manager.persist(usuario);
		manager.getTransaction().commit();
	}

	public List<Usuario> lista() {
		String jpql = "select u from Usuario u ";
		return utilDao.createQuery(jpql).getResultList();
	}


	public Usuario buscar(String login, String senha) {
		String jpql = "select u from Usuario u where u.login = ? and u.senha = ?";
		return utilDao.createQuery(jpql,login,senha).getSingleResult();
	}
}
