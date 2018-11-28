package br.com.caelum.horas.dao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import br.com.caelum.horas.util.Where;
import br.com.caelum.vraptor.modelo.Usuario;

@Stateless
public class UsuarioDao extends GenericDao<Usuario> implements Serializable {

	private static final long serialVersionUID = 1L;


	public UsuarioDao() {
		super(Usuario.class);
	}

	public Usuario loginCheck(Usuario usuario) {
		String where = new Where().createEquals("u.login","u.senha");
		return findByParams(where, usuario.getLogin(), usuario.getSenha());
	}

}
