package com.drbsoft.ccm.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	public Client() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(this.id)
			.append(this.name)
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Client == false)
	      {
	        return false;
	      }
	      if (this == obj)
	      {
	         return true;
	      }
	      final Client otherObject = (Client) obj;

	      return new EqualsBuilder()
	         .append(this.id, otherObject.id)
	         .append(this.name, otherObject.name)
	         .isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
        .append("id", this.id)
        .append("name", this.name)
        .toString();
	}
}