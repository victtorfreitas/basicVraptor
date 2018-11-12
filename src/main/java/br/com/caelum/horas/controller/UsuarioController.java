package br.com.caelum.horas.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.horas.dao.UsuarioDao;
import br.com.caelum.horas.segurança.UsuarioLogado;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.modelo.Usuario;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class UsuarioController {
	private Result result;

	private UsuarioDao dao;
	private Validator validator;
	private UsuarioLogado usuarioLogado;

	public UsuarioController() {
	}

	@Inject
	public UsuarioController(Result result, UsuarioDao dao, Validator validator, UsuarioLogado usuarioLogado) {
		this.result = result;
		this.dao = dao;
		this.validator = validator;
		this.usuarioLogado = usuarioLogado;
	}

	public void form() {

	}

	@IncludeParameters
	@Post
	public void adiciona(@Valid Usuario usuario) {
		validator.onErrorRedirectTo(this).form();
		dao.adicionar(usuario);
		result.redirectTo(this).lista();
	}

	public void lista() {
		result.include("usuarios", dao.lista());
	}

}
