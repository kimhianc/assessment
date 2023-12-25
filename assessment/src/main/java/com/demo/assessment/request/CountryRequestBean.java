package com.demo.assessment.request;

public class CountryRequestBean {

	private Integer countryId;

	private String countryCode;

	private String countryName;

	private int isoCode;

	//start index of the pagination 
	private int startIdx;

	//pagination record size 
	private int amtIdx;

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public int getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(int isoCode) {
		this.isoCode = isoCode;
	}

	public int getStartIdx() {
		return startIdx;
	}

	public void setStartIdx(int startIdx) {
		this.startIdx = startIdx;
	}

	public int getAmtIdx() {
		return amtIdx;
	}

	public void setAmtIdx(int amtIdx) {
		this.amtIdx = amtIdx;
	}

}
