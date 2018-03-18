package com.drbsoft.ccm.persistence.entity;

import com.drbsoft.ccm.service.CreditLineService;

/**
 * Descreve o Risco (<i>Venture</i>) do negocio como um tipo comum. Para cada tipo existe uma taxa (<i>rate</i>) especifica.
 * <ul>
 * <li>A - Nao aplica taxa de juros;</li>
 * <li>B - Aplica taxacao de 10%<li/>
 * <li>C - Aplica taxacao de 20%<li/>
 * </ul>
 * <strong>Nota:</strong>
 * Neste caso partilar, os valores de taxa sao definidos como inteiros para serem usados pelo calculador pois este faz a conversao
 * para o decimal correspondente.
 * 
 * @author daniel
 * @see CreditLineService
 */
public enum Venture {

	A(0), B(10), C(20);
	
	private int rate;
	
	private Venture(int rate) {
		this.rate = rate;
	}
	
	public int getRate() {
		return rate;
	}
}
