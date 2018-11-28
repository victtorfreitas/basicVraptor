package br.com.caelum.horas.enums;

import br.com.caelum.vraptor.modelo.Usuario;

/**
 * Responsável pela Query basica ligada a Entity
 * @author Victtor
 *
 */
public enum SQLenum {
	Usuario("Select u from Usuario u ", Usuario.class);

	private final String query;
	private Class<?> classe;

	SQLenum(String query, Class<?> classe) {
		this.query = query;
		this.classe = classe;
	}

	public String getQuery() {
		return query;
	}

	public Class<?> getClasse() {
		return classe;
	}

	//IMPLEMENTAR DEPOIS
//	public String exataQuery(Class classe) {
//		if(classe.equals(this.classe)) {
//			return query;
//		}
//		return null;
//	}

}
