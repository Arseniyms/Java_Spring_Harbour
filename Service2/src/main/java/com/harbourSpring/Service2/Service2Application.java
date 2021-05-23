package com.harbourSpring.Service2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Service2Application {

	public static void main(String[] args) {
		SpringApplication.run(Service2Application.class, args);

		String url = "http://localhost:8082/service2/postReport/ships.json";


		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>("Service 3", headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request ,String.class);
		System.out.println(responseEntity.getBody());
	}

}
