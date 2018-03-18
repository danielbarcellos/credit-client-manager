package com.drbsoft.ccm.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.drbsoft.ccm.persistence.entity.Client;
import com.drbsoft.ccm.persistence.entity.Venture;

@Service
public class CreditLineServiceImpl implements CreditLineService {
	
	@Override
	public Amount calculateAmount(Client client, Venture venture, BigDecimal requestedValue) {
		BigDecimal calculatedValue = doCalculation(venture, requestedValue);
		
		return new Amount(client, calculatedValue, requestedValue);
	}

	@Override
	public BigDecimal calculateAmountValue(Venture venture, BigDecimal requestedValue) {
		return doCalculation(venture, requestedValue);
	}

	private BigDecimal doCalculation(Venture venture, BigDecimal requestedValue) {
		if (venture == null || requestedValue == null || (requestedValue != null && requestedValue.compareTo(BigDecimal.ZERO) <= 0)) {
			return BigDecimal.ZERO;
		}

		return requestedValue.add(requestedValue.multiply(BigDecimal.valueOf(venture.getRate() / 100.0))).setScale(2, RoundingMode.DOWN);
	}

}