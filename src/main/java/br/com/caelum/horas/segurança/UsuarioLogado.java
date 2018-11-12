package br.com.caelum.horas.segurança;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.caelum.vraptor.modelo.Usuario;

@Named
@SessionScoped
public class UsuarioLogado implements Serializable{
	
	private Usuario usuario;
	
	private static final long serialVersionUID = 1L;
	

	public void fazLogin(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void deslogar() {
		this.usuario = null;
	}
	
	public boolean isLogado() {
		return this.usuario != null;
	}
}
