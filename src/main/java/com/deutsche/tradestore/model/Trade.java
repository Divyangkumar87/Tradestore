package com.deutsche.tradestore.model;

import java.time.LocalDate;

public class Trade{

	private String tradeId;
	private Integer version;
	private String counterPartyId;
	private String bookId;
	private LocalDate maturityDate;
	private LocalDate createdDate;
	private String expired;
	
	public Trade(String tradeId, Integer version, String counterPartyId, String bookId, LocalDate maturityDate,
			LocalDate createdDate, String expired) {
		super();
		this.tradeId = tradeId;
		this.version = version;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.createdDate = createdDate;
		this.expired = expired;
	}
	
	public String getTradeId() {
		return tradeId;
	}
	public Integer getVersion() {
		return version;
	}
	public String getCounterPartyId() {
		return counterPartyId;
	}
	public String getBookId() {
		return bookId;
	}
	public LocalDate getMaturityDate() {
		return maturityDate;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public String isExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

	// Equals and hashcode methods has not been used , but implemented for future use.
	// Used tradeid and version to uniquely identify any trade -- assumption
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tradeId == null) ? 0 : tradeId.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (tradeId == null) {
			if (other.tradeId != null)
				return false;
		} else if (!tradeId.equals(other.tradeId))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", version=" + version + ", expired=" + expired + "]";
	}

}
