package com.santoshkc.leaser.integration.steps;

import com.santoshkc.leaser.LeaserApplication;
import com.santoshkc.leaser.application.freelancer.DTOs.FreelancerDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
        ,classes = {LeaserApplication.class})
public class StepDefsFreelanceCreation {

    private ResponseEntity<String> response;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String freelanceUrl = "http://localhost";
    @LocalServerPort
    private int port;

    @When("the user calls freelancer slash create")
    public void theUserCallsFreelancerSlashCreate() {
        String url = freelanceUrl + ":" + port + "/freelancer/create";
        FreelancerDto dto = new FreelancerDto();
        dto.setFirstName("my first name");
        dto.setLastName("last name");
        dto.setCity("kathmandu");
        dto.setZipCode("45600");
        response = restTemplate.postForEntity(url, dto, String.class);
    }

    @Then("the user receives status code of {int}")
    public void theTheUserReceivesStatusCodeOf(int arg0) {
        assertThat(response.getStatusCode().value()).isEqualTo(arg0);
    }

    @And("the user receives success message")
    public void theUserReceivesSuccessMessage() {
        assertThat(response.getBody()).isEqualTo("Success");
    }
}
