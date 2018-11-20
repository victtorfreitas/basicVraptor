package br.com.caelum.horas.util;

public class UtilVerificacao {
	public static boolean isParams(Object... params) {
		for (Object parametro : params) {
			if(parametro == null) {
				return false;
			}
		}
		return true;
	}
}
