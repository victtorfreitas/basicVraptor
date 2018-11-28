package br.com.caelum.horas.util;
/**
 * Responsavel por criação de Where
 * @author Victtor
 *
 */
public class Where {
	private String query = "where ";

	/**
	 * Cria um where JPQL que tem o objetivo de verificação 
	 * @param fieldName Nomes das colunas no banco <br>
	 * OBS: Deve conter o prefixo
	 * @return String JPQL where
	 */
	public String createEquals(String... fieldName) {
		for (int i = 0; i < fieldName.length; i++) {
			query += fieldName[i] + "=?" + (i + 1);
			if (fieldName.length != (i + 1)) {
				query += " and ";
			}
		}
		return query;
	}

	@Override
	public String toString() {
		return this.query;
	}
}
