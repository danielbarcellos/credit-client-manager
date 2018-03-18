package com.drbsoft.ccm.service;

import java.math.BigDecimal;

import com.drbsoft.ccm.persistence.entity.Client;
import com.drbsoft.ccm.persistence.entity.Venture;

public interface CreditLineService {

	Amount calculateAmount(Client client, Venture venture, BigDecimal value);

	BigDecimal calculateAmountValue(Venture venture, BigDecimal value);
}
