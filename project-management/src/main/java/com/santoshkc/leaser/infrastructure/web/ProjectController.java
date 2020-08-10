package com.santoshkc.leaser.infrastructure.web;

import com.santoshkc.leaser.application.DTOs.ProjectDto;
import com.santoshkc.leaser.application.ProjectManagementApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectManagementApplication application;

    public ProjectController(ProjectManagementApplication application) {
        this.application = application;
    }

    @PostMapping("/create")
    public ResponseEntity<ProjectDto> create(@Valid @RequestBody ProjectDto dto) {
        ProjectDto responseBody = application.save(dto);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<String> projectExistsForCustomer(@PathVariable Long customerId) {
        boolean exists = application.projectExistsFor(customerId);
        if(exists) {
            return new ResponseEntity<>("Found", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }
}
