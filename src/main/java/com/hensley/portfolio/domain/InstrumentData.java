package com.hensley.portfolio.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "INSTRUMENT")
public class InstrumentData extends BaseAuditEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1217745055693240198L;
	
	@Column(name = "SYMBOL", nullable = false)
	private String symbol;
	@Column(name = "BLOOMBERG_ID", nullable = false)
	private String bloombergId;
	@Column(name = "ROBINHOOD_ID", nullable = false)
	private String robinhoodId;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "COUNTRY", nullable = false)
	private String country;
	@Column(name = "DAY_TRADE_RATIO", nullable = true)
	private Double dayTradeRatio;
	@Column(name = "LISTED_DATE", nullable = true)
	private LocalDate listedDate;
	@OneToMany(mappedBy = "instrument", cascade = CascadeType.PERSIST)
	private List<PriceData> prices;
	@OneToMany(mappedBy = "instrument", cascade = CascadeType.PERSIST)
	private List<InDayPriceData> inDayPrices;
	
	public InstrumentData() {
		
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the bloombergId
	 */
	public String getBloombergId() {
		return bloombergId;
	}

	/**
	 * @param bloombergId the bloombergId to set
	 */
	public void setBloombergId(String bloombergId) {
		this.bloombergId = bloombergId;
	}

	/**
	 * @return the robinhoodId
	 */
	public String getRobinhoodId() {
		return robinhoodId;
	}

	/**
	 * @param robinhoodId the robinhoodId to set
	 */
	public void setRobinhoodId(String robinhoodId) {
		this.robinhoodId = robinhoodId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the dayTradeRatio
	 */
	public Double getDayTradeRatio() {
		return dayTradeRatio;
	}

	/**
	 * @param dayTradeRatio the dayTradeRatio to set
	 */
	public void setDayTradeRatio(Double dayTradeRatio) {
		this.dayTradeRatio = dayTradeRatio;
	}

	/**
	 * @return the listedDate
	 */
	public LocalDate getListedDate() {
		return listedDate;
	}

	/**
	 * @param listedDate the listedDate to set
	 */
	public void setListedDate(LocalDate listedDate) {
		this.listedDate = listedDate;
	}

	
	/**
	 * @return the prices
	 */
	public List<PriceData> getPrices() {
		return prices;
	}

	/**
	 * @param prices the prices to set
	 */
	public void setPrices(List<PriceData> prices) {
		this.prices = prices;
	}
	
	/**
	 * @param prices the prices to set
	 */
	public void addPrice(PriceData price) {
		this.prices.add(price);
		price.setInstrument(this);
	}

	/**
	 * @return the prices
	 */
	public List<InDayPriceData> getInDayPrices() {
		return inDayPrices;
	}

	/**
	 * @param prices the prices to set
	 */
	public void setInDayPrices(List<InDayPriceData> inDayPrices) {
		this.inDayPrices = inDayPrices;
	}
	
	/**
	 * @param prices the prices to set
	 */
	public void addInDayPrice(InDayPriceData inDayPrice) {
		this.inDayPrices.add(inDayPrice);
		inDayPrice.setInstrument(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(bloombergId, country, dayTradeRatio, inDayPrices, listedDate, name,
				prices, robinhoodId, symbol);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof InstrumentData)) {
			return false;
		}
		InstrumentData other = (InstrumentData) obj;
		return Objects.equals(bloombergId, other.bloombergId) && Objects.equals(country, other.country)
				&& Objects.equals(dayTradeRatio, other.dayTradeRatio) && Objects.equals(inDayPrices, other.inDayPrices)
				&& Objects.equals(listedDate, other.listedDate) && Objects.equals(name, other.name)
				&& Objects.equals(prices, other.prices) && Objects.equals(robinhoodId, other.robinhoodId)
				&& Objects.equals(symbol, other.symbol);
	}
	
}

