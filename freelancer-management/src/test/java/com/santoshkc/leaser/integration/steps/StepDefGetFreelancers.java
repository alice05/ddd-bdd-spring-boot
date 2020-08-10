package com.santoshkc.leaser.integration.steps;

import com.santoshkc.leaser.LeaserApplication;
import com.santoshkc.leaser.application.freelancer.DTOs.FreelancerDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@CucumberContextConfiguration
@ActiveProfiles("integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
        ,classes = {LeaserApplication.class})
public class StepDefGetFreelancers {

    private ResponseEntity<String> savedResponse;
    private List<FreelancerDto> getResponse;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String freelanceUrl = "http://localhost";
    @LocalServerPort
    private int port;

    @Given("I create a freelancer with first name {string}, last name {string}, " +
            " city {string} and zipcode {string}")
    public void shouldBeAbleTo(String fName, String lName,
                               String city, String zipCode) {
        String url = freelanceUrl + ":" + port + "/freelancer/create";
        FreelancerDto dto = new FreelancerDto();
        dto.setFirstName(fName);
        dto.setLastName(lName);
        dto.setCity(city);
        dto.setZipCode(zipCode);
        savedResponse = restTemplate.postForEntity(url, dto, String.class);
    }

    @Then("I receive status code of {int}")
    public void iReceiveStatusCodeOf(int arg0) {
        assertThat(savedResponse.getStatusCode().value()).isEqualTo(arg0);
    }

    @Given("I requested to see all freelancers.")
    public void iRequestedToSeeAllFreelancers() {
        String url = freelanceUrl + ":" + port + "/freelancer/";
        FreelancerDto[] dtos = restTemplate.getForObject(url, FreelancerDto[].class);
        assert dtos != null;
        getResponse = Arrays.asList(dtos);
    }


    @And("I should be able to see my newly created freelancer with " +
            "firstname {string}, last name {string}, city {string} " +
            "and zip code {string}")
    public void shouldBeSeenAllFreelancers(String fName, String lName,
                                           String city, String zipCode) {
        assertThat(getResponse.get(1).getFirstName()).isEqualTo(fName);
        assertThat(getResponse.get(1).getLastName()).isEqualTo(lName);
        assertThat(getResponse.get(1).getCity()).isEqualTo(city);
        assertThat(getResponse.get(1).getZipCode()).isEqualTo(zipCode);
    }
}