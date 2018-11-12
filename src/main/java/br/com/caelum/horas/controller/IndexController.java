package br.com.caelum.horas.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;

@Controller
public class IndexController {
	
	@Path(value="/", priority=1)
	public void index() {}
}
