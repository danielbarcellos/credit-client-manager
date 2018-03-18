package com.drbsoft.ccm.service;

import java.math.BigDecimal;

import com.drbsoft.ccm.persistence.entity.Client;
import com.drbsoft.ccm.persistence.entity.Venture;
/**
 * Principal interface que abstrai as operacoes de calculo de juros sobre cada valor solicitado.
 * 
 * Para cada tipo existe uma taxa (<i>rate</i>) especifica.
 * <ul>
 * <li>A - Nao aplica taxa de juros;</li>
 * <li>B - Aplica taxacao de 10%</li>
 * <li>C - Aplica taxacao de 20%</li>
 * </ul>
 * 
 * @author daniel
 * @see Venture
 * @see Client
 *
 */
public interface CreditLineService {

	/**
	 * Calcula o montante <i>amount</i> final que cada {@link Client} tem ja com o juros
	 * calculados conforme regra.
	 * 
	 * @param client - O client relativo.
	 * @param venture - O tipo de Risco. Veja mais em {@link Venture}
	 * @param value - O valor solicitado de emprestimo.
	 * @return - O objeto contendo valor calculado mais os dados do cliente Veja mais em {@link Amount}.
	 */
	Amount calculateAmount(Client client, Venture venture, BigDecimal value);

	/**
	 * Calcula o valor do montante final que cada {@link Client} tem ja com o juros
	 * calculados conforme regra.
	 * 
	 * @param client - O client relativo.
	 * @param venture - O tipo de Risco. Veja mais em {@link Venture}
	 * @param value - O valor solicitado de emprestimo.
	 * @return - O objeto contendo valor calculado mais os dados do cliente Veja mais em {@link Amount}.
	 */
	BigDecimal calculateAmountValue(Venture venture, BigDecimal value);
}
