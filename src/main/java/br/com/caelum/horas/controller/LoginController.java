package br.com.caelum.horas.controller;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import br.com.caelum.horas.annotations.Open;
import br.com.caelum.horas.dao.UsuarioDao;
import br.com.caelum.horas.seguranca.UsuarioLogado;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.modelo.Usuario;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class LoginController {

	private UsuarioDao dao;
	private UsuarioLogado usuarioLogado;
	private Result result;
	private Validator validator;

	public LoginController() {
	}

	@Inject
	public LoginController(UsuarioDao dao, UsuarioLogado usuarioLogado, Result result, Validator validator) {
		this.dao = dao;
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.validator = validator;
	}

	@Open
	public void form() {
	}

	@Open
	@Post
	public void login(@NotNull Usuario usuario) {
		if (!validator.hasErrors()) {
			usuario = dao.loginCheck(usuario);
			if (usuario != null) {
				usuarioLogado.fazLogin(usuario);
				result.redirectTo(IndexController.class).index();
			}
		} else {
			validator.add(new SimpleMessage("login_invalido", "Login ou senha incorretos!"));
			validator.onErrorRedirectTo(this).form();
		}
	}

	@Open
	public void deslogar() {
		usuarioLogado.deslogar();
		result.redirectTo(this).form();
	}

}
