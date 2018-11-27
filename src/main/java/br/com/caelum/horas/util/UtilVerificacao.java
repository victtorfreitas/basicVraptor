package br.com.caelum.horas.util;

public class UtilVerificacao {
	/**
	 * Verifica se algum dos parametros é nulo
	 * @param params 
	 * @return False Se tiver algum parametro Null <br> True Se não tiver nenhum parametro Null
	 */
	public static boolean isParams(Object... params) {
		for (Object parametro : params) {
			if(parametro == null) {
				return false;
			}
		}
		return true;
	}
}
