package com.hensley.portfolio.pojo.dto.price;

import java.sql.Timestamp;
import java.util.Calendar;

import com.hensley.portfolio.enums.PriceTypeEnum;
import com.hensley.portfolio.pojo.dto.SuperAuditDto;

public class BasicPriceDto extends SuperAuditDto {
	
	private Calendar timestamp;
	private Double openPrice;
	private Double closePrice;
	private Double highPrice;
	private Double lowPrice;
	private Double volume;
	private PriceTypeEnum type;
	
	public BasicPriceDto() {
		
	}

	/**
	 * @return the timestamp
	 */
	public Calendar getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the openPrice
	 */
	public Double getOpenPrice() {
		return openPrice;
	}

	/**
	 * @param openPrice the openPrice to set
	 */
	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}

	/**
	 * @return the closePrice
	 */
	public Double getClosePrice() {
		return closePrice;
	}

	/**
	 * @param closePrice the closePrice to set
	 */
	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}

	/**
	 * @return the highPrice
	 */
	public Double getHighPrice() {
		return highPrice;
	}

	/**
	 * @param highPrice the highPrice to set
	 */
	public void setHighPrice(Double highPrice) {
		this.highPrice = highPrice;
	}

	/**
	 * @return the lowPrice
	 */
	public Double getLowPrice() {
		return lowPrice;
	}

	/**
	 * @param lowPrice the lowPrice to set
	 */
	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}

	/**
	 * @return the volume
	 */
	public Double getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(Double volume) {
		this.volume = volume;
	}

	/**
	 * @return the type
	 */
	public PriceTypeEnum getType() {
		return type;
	}
	
	public Integer getTypeInt() {
		if (PriceTypeEnum.INTRA_DAY.equals(this.type)) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * @param type the type to set
	 */
	public void setType(PriceTypeEnum type) {
		this.type = type;
	}
	
	
}
