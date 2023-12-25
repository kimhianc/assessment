package com.demo.assessment.util;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingUtil {

	private static Logger log = LoggerFactory.getLogger(LoggingUtil.class);

	ObjectMapper mapper = new ObjectMapper();

	public void displayReq(HttpServletRequest request, Object body) {
		StringBuilder reqMessage = new StringBuilder();
		Map<String, String> parameters = getParameters(request);

		reqMessage.append("REQUEST ");
		reqMessage.append("method = [").append(request.getMethod()).append("]");
		reqMessage.append(" path = [").append(request.getRequestURI()).append("] ");

		if (!parameters.isEmpty()) {
			reqMessage.append(" parameters = [").append(parameters).append("] ");
		}
		try {
			if (!Objects.isNull(body)) {
				reqMessage.append(" body = [").append(mapper.writeValueAsString(body)).append("]");
			}
		} catch (JsonProcessingException e) {
			reqMessage.append(" body = [").append(body).append("]");
			e.printStackTrace();
		}

		log.info("log Request: {}", reqMessage);
	}

	public void displayResp(HttpServletRequest request, HttpServletResponse response, Object body) {
		StringBuilder respMessage = new StringBuilder();
		Map<String, String> headers = getHeaders(response);
		respMessage.append("RESPONSE ");
		respMessage.append(" method = [").append(request.getMethod()).append("]");
		try {
			respMessage.append(" responseBody = [").append(mapper.writeValueAsString(body)).append("]");
		} catch (JsonProcessingException e) {
			respMessage.append(" responseBody = [").append(body).append("]");
			e.printStackTrace();
		}

		log.info("logResponse: {}", respMessage);
	}

	private Map<String, String> getHeaders(HttpServletResponse response) {
		Map<String, String> headers = new HashMap<>();
		Collection<String> headerMap = response.getHeaderNames();
		for (String str : headerMap) {
			headers.put(str, response.getHeader(str));
		}
		return headers;
	}

	private Map<String, String> getParameters(HttpServletRequest request) {
		Map<String, String> parameters = new HashMap<>();
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String paramName = params.nextElement();
			String paramValue = request.getParameter(paramName);
			parameters.put(paramName, paramValue);
		}
		return parameters;
	}

}
