package br.com.caelum.horas.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

import br.com.caelum.horas.util.UtilDao;
import br.com.caelum.horas.util.UtilVerificacao;
import br.com.caelum.vraptor.modelo.Usuario;

@Stateless
public class UsuarioDao implements Serializable,Dao<Usuario> {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;

	private UtilDao<Usuario> utilDao;
	private String jpql;

	private DaoImpl<Usuario> dao;

	public UsuarioDao() {
	}

	@PostConstruct
	void init() {
		this.utilDao = new UtilDao<Usuario>(manager);
		this.dao = new DaoImpl<Usuario>();
		this.jpql = "select u from Usuario u ";
	}
	@Override
	public void adiciona(Usuario usuario) {
		dao.adiciona(usuario);
	}

	public List<Usuario> lista() {
		return utilDao.createQueryResultList(this.jpql);
	}
	
	
	public Usuario busca(String login, String senha) {
		if (UtilVerificacao.isParams(login, senha)) {
			this.jpql += "where u.login = ? and u.senha = ?";
			return utilDao.createQuerySingleResult(this.jpql, login, senha);
		}
		return null;
	}


	@Override
	public void remove(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Usuario> listaTodos(Usuario entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualiza(Usuario t) {
		// TODO Auto-generated method stub
		
	}
}
