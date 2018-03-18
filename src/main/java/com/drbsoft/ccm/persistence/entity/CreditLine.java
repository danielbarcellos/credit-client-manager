package com.drbsoft.ccm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class CreditLine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private BigDecimal value;
	
	private String venture;
	
	@Transient
	private String rate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getVenture() {
		return venture;
	}

	public void setVenture(String venture) {
		this.venture = venture;
	}
	
	public String getRate() {
		return venture != null ? Venture.valueOf(venture).getRate() + "%" : "0%";
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(this.id)
			.append(this.venture)
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CreditLine == false)
	      {
	        return false;
	      }
	      if (this == obj)
	      {
	         return true;
	      }
	      final CreditLine otherObject = (CreditLine) obj;

	      return new EqualsBuilder()
	         .append(this.id, otherObject.id)
	         .append(this.venture, otherObject.venture)
	         .isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
        .append("id", this.id)
        .append("venture", this.venture)
        .toString();
	}
}
