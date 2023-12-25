package com.demo.assessment.response;

import java.util.List;

public class CountryListResponseBean {

	List<CountryResponseBean> countryList;

	private int totalRecord;

	public List<CountryResponseBean> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<CountryResponseBean> countryList) {
		this.countryList = countryList;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	
	

}
