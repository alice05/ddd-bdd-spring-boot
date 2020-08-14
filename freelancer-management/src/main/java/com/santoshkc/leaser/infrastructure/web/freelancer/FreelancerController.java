package com.santoshkc.leaser.infrastructure.web.freelancer;

import com.santoshkc.leaser.application.freelancer.DTOs.FreelancerDto;
import com.santoshkc.leaser.application.freelancer.FreelancerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/freelancer")
public class FreelancerController {

    private final FreelancerApplication application;

    @Autowired
    public FreelancerController(FreelancerApplication application) {
        this.application = application;
    }

    @GetMapping("/test")
    public String g() {
        return "Hello world";
    }

    @GetMapping("/")
    public Collection<FreelancerDto> getAll() {
        return application.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody FreelancerDto dto) {
        application.create(dto);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
