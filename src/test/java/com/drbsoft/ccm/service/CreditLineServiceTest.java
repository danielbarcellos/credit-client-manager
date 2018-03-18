package com.drbsoft.ccm.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import com.drbsoft.ccm.persistence.entity.Venture;
/**
 * Tem por objetivo testar a classe {@link CreditLineService}.
 * 
 * @author daniel
 *
 */
public class CreditLineServiceTest {

	/**
	 * Deve calcular a linha de credito quando o risco for igual a A, B, C e o valor solicitado
	 * for zero. Neste caso, nao aplica juros porque tecnicamente nao houve solicitacao
	 * de emprestimo, logo, o valor nao ser modificado
	 */
	@Test
	public void shouldCalculateAmountWhenValueIsZeroAndWithAllRisks() {
		CreditLineService service = new CreditLineServiceImpl();
		BigDecimal valueA = service.calculateAmountValue(Venture.A, BigDecimal.ZERO);
		assertEquals(BigDecimal.ZERO, valueA);

		BigDecimal valueB = service.calculateAmountValue(Venture.B, BigDecimal.ZERO);
		assertEquals(BigDecimal.ZERO, valueB);
		
		BigDecimal valueC = service.calculateAmountValue(Venture.C, BigDecimal.ZERO);
		assertEquals(BigDecimal.ZERO, valueC);
	}

	/**
	 * Deve calcular a linha de credito quando o risco for igual a A, B, C e o valor solicitado
	 * for zero. Neste caso, nao aplica juros porque tecnicamente nao houve solicitacao
	 * de emprestimo, logo, o valor nao ser modificado
	 */
	@Test
	public void shouldCalculateAmountWhenValueIsEmptyAndWithAllRisks() {
		CreditLineService service = new CreditLineServiceImpl();
		BigDecimal valueA = service.calculateAmountValue(Venture.A, null);
		assertEquals(BigDecimal.ZERO, valueA);
		
		BigDecimal valueB = service.calculateAmountValue(Venture.B, null);
		assertEquals(BigDecimal.ZERO, valueB);
		
		BigDecimal valueC = service.calculateAmountValue(Venture.C, null);
		assertEquals(BigDecimal.ZERO, valueC);
	}

	/**
	 * Quando o risco for do tipo A nao deve ser aplicado taxa de juros.
	 */
	@Test
	public void shouldCalculateAmountWhenRiskIsA() {
		CreditLineService service = new CreditLineServiceImpl();
		BigDecimal actual = service.calculateAmountValue(Venture.A, BigDecimal.valueOf(1234.67));
		assertEquals(BigDecimal.valueOf(1234.67), actual);
	}

	/**
	 * Quando o risco for do tipo B, aplica-se 10% de juros sobre o valor solicitado.
	 */
	@Test
	public void shouldCalculateAmountWhenRiskIsB() {
		CreditLineService service = new CreditLineServiceImpl();
		BigDecimal actual = service.calculateAmountValue(Venture.B, BigDecimal.valueOf(1234.67));
		
		BigDecimal expected = BigDecimal.valueOf(1358.13);
		assertEquals(expected, actual);
	}

	/**
	 * Quando o risco for do tipo C, aplica-se 20% de juros sobre o valor solicitado.
	 */
	@Test
	public void shouldCalculateAmountWhenRiskIsC() {
		CreditLineService service = new CreditLineServiceImpl();
		BigDecimal actual = service.calculateAmountValue(Venture.C, BigDecimal.valueOf(1234.67));

		// Preciso fazer isso para forcar o valor com 2 casas decimais. 
		// Porque trabalhar com BigDecimal pode ser enganador se voce nao se atentar para as casas decimais.
		BigDecimal expected = BigDecimal.valueOf(1481.60).setScale(2, RoundingMode.FLOOR);
		assertEquals(expected, actual);
	}
}