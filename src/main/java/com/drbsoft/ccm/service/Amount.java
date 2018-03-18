package com.drbsoft.ccm.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.drbsoft.ccm.persistence.entity.Client;

public class Amount {

	private BigDecimal requestedValue;
	
	private BigDecimal calculatedValue;
	
	private Client client;

	public Amount() {
	}

	public Amount(Client client, BigDecimal calculatedValue,
			BigDecimal requestedValue) {
		this.setClient(client);
		this.setCalculatedValue(calculatedValue);
		this.setRequestedValue(requestedValue);
	}

	public BigDecimal getRequestedValue() {
		return requestedValue;
	}

	public void setRequestedValue(BigDecimal requestedValue) {
		this.requestedValue = requestedValue;
	}

	public BigDecimal getCalculatedValue() {
		return calculatedValue;
	}

	public void setCalculatedValue(BigDecimal calculatedValue) {
		this.calculatedValue = calculatedValue;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(this.client)
			.append(this.requestedValue)
			.append(this.calculatedValue)
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Amount == false)
	      {
	        return false;
	      }
	      if (this == obj)
	      {
	         return true;
	      }
	      final Amount otherObject = (Amount) obj;

	      return new EqualsBuilder()
	      	 .append(this.client, otherObject.client)
	         .append(this.requestedValue, otherObject.requestedValue)
	         .append(this.calculatedValue, otherObject.calculatedValue)
	         .isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append(this.client)
		.append(this.requestedValue)
		.append(this.calculatedValue)
        .toString();
	}
}
