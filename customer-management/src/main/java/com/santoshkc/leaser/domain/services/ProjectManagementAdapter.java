package com.santoshkc.leaser.domain.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
class ProjectManagementAdapter {
    //TODO: move to infrastructure layer
    private final RestTemplate restTemplate;

    public ProjectManagementAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    boolean projectExistsFor(Long customerId) {
        String url = "http://localhost:8080/project/customer/" + customerId;
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            return responseEntity.getStatusCode() == HttpStatus.OK;
        } catch (final HttpClientErrorException e) {
            return false;
        }
    }
}
