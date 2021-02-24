package com.drools.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ApiBaseController {
	protected ObjectMapper mapper;
	
	protected HttpHeaders headers;

	public ApiBaseController() {
		mapper = new ObjectMapper();
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

}
