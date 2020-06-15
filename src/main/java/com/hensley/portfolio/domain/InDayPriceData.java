package com.hensley.portfolio.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hensley.portfolio.enums.PriceTypeEnum;

@Entity
@Table(name = "INDAY_PRICE")
public class InDayPriceData extends BaseAuditEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7143658107447044060L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP", nullable = false)
	private Calendar timestamp;
	@Column(name = "OPEN_PRICE", nullable = false)
	private Double openPrice;
	@Column(name = "CLOSE_PRICE", nullable = false)
	private Double closePrice;
	@Column(name = "HIGH_PRICE", nullable = false)
	private Double highPrice;
	@Column(name = "LOW_PRICE", nullable = false)
	private Double lowPrice;
	@Column(name = "VOLUME", nullable = false)
	private Double volume;
	@Column(name = "TYPE", nullable = false)	
	private PriceTypeEnum type;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSTRUMENT_OID", referencedColumnName = "OID", nullable = false)
	private InstrumentData instrument;
	
	public InDayPriceData() {
		
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

	/**
	 * @param type the type to set
	 */
	public void setType(PriceTypeEnum type) {
		this.type = type;
	}

	/**
	 * @return the instrument
	 */
	public String getInstrumentSymbol() {
		return instrument.getSymbol();
	}

	/**
	 * @param instrument the instrument to set
	 */
	public void setInstrument(InstrumentData instrument) {
		this.instrument = instrument;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(closePrice, highPrice, instrument, lowPrice, openPrice, timestamp, type, volume);
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
		if (!(obj instanceof InDayPriceData)) {
			return false;
		}
		InDayPriceData other = (InDayPriceData) obj;
		return Objects.equals(closePrice, other.closePrice) && Objects.equals(highPrice, other.highPrice)
				&& Objects.equals(instrument, other.instrument) && Objects.equals(lowPrice, other.lowPrice)
				&& Objects.equals(openPrice, other.openPrice) && Objects.equals(timestamp, other.timestamp)
				&& type == other.type && Objects.equals(volume, other.volume);
	}


}