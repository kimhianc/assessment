package com.demo.assessment.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/thirdParty")
public class ThirdPartyApiController {

	private static final Logger log = LoggerFactory.getLogger(CountryController.class);

	@PostMapping("/list")
	@ResponseBody
	public ResponseEntity<String> addCountry(@RequestBody Object requestObjectBean) {

		Map<String, String> uriVariables = new HashMap<>();

		String url = "http://localhost:8080/country/list";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper objectMapper = new ObjectMapper();

		HttpEntity<String> entity = null;
		try {
			entity = new HttpEntity<String>(objectMapper.writeValueAsString(requestObjectBean), headers);
		} catch (JsonProcessingException e) {
			log.error("Error convert to json Object " + e.getMessage());
		}

		if (entity == null) {
			return ResponseEntity.badRequest().build();
		}
		ResponseEntity<String> respEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class,
				uriVariables);

		return respEntity;
	}

}
