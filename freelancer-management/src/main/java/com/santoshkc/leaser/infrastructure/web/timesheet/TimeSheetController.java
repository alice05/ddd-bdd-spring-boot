package com.santoshkc.leaser.infrastructure.web.timesheet;

import com.santoshkc.leaser.application.timesheet.TimeSheetApplication;
import com.santoshkc.leaser.application.timesheet.TimeSheetDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/timesheet")
public class TimeSheetController {
    private final TimeSheetApplication application;

    public TimeSheetController(TimeSheetApplication application) {
        this.application = application;
    }

    @GetMapping("/ger")
    public String gt() {
        return "ger";
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody TimeSheetDto dto) {
        application.save(dto);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
