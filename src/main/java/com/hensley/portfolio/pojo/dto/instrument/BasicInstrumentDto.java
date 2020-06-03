package com.hensley.portfolio.pojo.dto.instrument;

import java.time.LocalDate;
import com.hensley.portfolio.pojo.dto.SuperAuditDto;

public class BasicInstrumentDto  extends SuperAuditDto  {

	private String symbol;
	private String bloombergId;
	private String robinhoodId;
	private String name;
	private String country;
	private Double dayTradeRatio;
	private LocalDate listedDate;
	
	public BasicInstrumentDto() {
		
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
	
	
}
