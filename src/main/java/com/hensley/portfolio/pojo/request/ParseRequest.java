package com.hensley.portfolio.pojo.request;

import com.hensley.portfolio.enums.ParseTargetEnum;

public class ParseRequest {

	private ParseTargetEnum target;
	private String fightId;
	private String boutId;
	private String fighterId;
	
	public ParseRequest() {
		
	}
	
	public ParseRequest(ParseTargetEnum target, String fightId, String boutId, String fighterId) {
		this.target = target;
		this.fightId = fightId;
		this.boutId = boutId;
		this.fighterId = fighterId;
	}

	/**
	 * @return the target
	 */
	public ParseTargetEnum getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(ParseTargetEnum target) {
		this.target = target;
	}

	/**
	 * @return the fightId
	 */
	public String getFightId() {
		return fightId;
	}

	/**
	 * @param fightId the fightId to set
	 */
	public void setFightId(String fightId) {
		this.fightId = fightId;
	}

	/**
	 * @return the boutId
	 */
	public String getBoutId() {
		return boutId;
	}

	/**
	 * @param boutId the boutId to set
	 */
	public void setBoutId(String boutId) {
		this.boutId = boutId;
	}

	/**
	 * @return the fighterId
	 */
	public String getFighterId() {
		return fighterId;
	}

	/**
	 * @param fighterId the fighterId to set
	 */
	public void setFighterId(String fighterId) {
		this.fighterId = fighterId;
	}
	
}