package com.demo.assessment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.assessment.entity.CountryEntity;
import com.demo.assessment.request.CountryRequestBean;
import com.demo.assessment.response.CountryListResponseBean;
import com.demo.assessment.response.CountryResponseBean;
import com.demo.assessment.service.CountryService;

@Controller
@RequestMapping("/country")
public class CountryController {

	private static final Logger log = LoggerFactory.getLogger(CountryController.class);

	@Autowired
	private CountryService countryService;

	@PostMapping("/add")
	@ResponseBody
	public CountryResponseBean addCountry(@RequestBody CountryRequestBean countryRequestBean) {

		CountryEntity countryEntity = countryService.save(countryRequestBean);
		CountryResponseBean countryResponseBean = new CountryResponseBean();

		countryResponseBean.setCountryId(countryEntity.getCountryId());
		countryResponseBean.setCountryCode(countryEntity.getCountryCode());
		countryResponseBean.setCountryName(countryEntity.getCountryName());
		countryResponseBean.setIsoCode(countryEntity.getIsoCode());
		countryResponseBean.setStatus(countryEntity.getStatus());

		return countryResponseBean;
	}

	@PostMapping("/get")
	@ResponseBody
	public CountryResponseBean getCountry(@RequestBody CountryRequestBean countryRequestBean) {

		CountryEntity countryEntity = countryService.get(countryRequestBean.getCountryId());
		CountryResponseBean countryResponseBean = new CountryResponseBean();

		countryResponseBean.setCountryId(countryEntity.getCountryId());
		countryResponseBean.setCountryCode(countryEntity.getCountryCode());
		countryResponseBean.setCountryName(countryEntity.getCountryName());
		countryResponseBean.setIsoCode(countryEntity.getIsoCode());
		countryResponseBean.setStatus(countryEntity.getStatus());

		return countryResponseBean;
	}

	@PostMapping("/edit")
	@ResponseBody
	public CountryResponseBean editCountry(@RequestBody CountryRequestBean countryRequestBean) {

		CountryEntity countryEntity = countryService.update(countryRequestBean);
		CountryResponseBean countryResponseBean = new CountryResponseBean();

		countryResponseBean.setCountryCode(countryEntity.getCountryCode());
		countryResponseBean.setCountryName(countryEntity.getCountryName());
		countryResponseBean.setIsoCode(countryEntity.getIsoCode());
		countryResponseBean.setStatus(countryEntity.getStatus());

		return countryResponseBean;
	}

	@PostMapping("/delete")
	@ResponseBody
	public ResponseEntity deleteCountry(@RequestBody CountryRequestBean countryRequestBean) {
		countryService.delete(countryRequestBean.getCountryId());
		return ResponseEntity.ok().build();
	}

	@PostMapping("/list")
	@ResponseBody
	public CountryListResponseBean listCountry(@RequestBody CountryRequestBean countryRequestBean) {

		CountryListResponseBean countryListResponseBean = new CountryListResponseBean();
		countryListResponseBean.setCountryList(countryService.list(countryRequestBean));
		countryListResponseBean.setTotalRecord(countryService.geCountryCount());

		return countryListResponseBean;
	}

}
