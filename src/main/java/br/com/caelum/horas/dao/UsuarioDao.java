package br.com.caelum.horas.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;

import br.com.caelum.vraptor.modelo.Usuario;

@Stateless
public class UsuarioDao extends GenericDao<Usuario> implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;

	public UsuarioDao() {
	}

	@PostConstruct
	void init() {
	}
	
	public Usuario loginCheck(Usuario usuario) {
		CriteriaQuery<Usuario> query = getCriteriaQuery();
		query.where(query)
		return null;
	}

}
