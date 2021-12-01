package com.gouin.rapport.controller;

import com.gouin.rapport.model.PatientWithHistory;
import com.gouin.rapport.model.Rapport;
import com.gouin.rapport.service.RapportService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Data
@RequestMapping("/rapport")
@OpenAPIDefinition(info = @Info(
        title = "Rapport controller",
        version = "1.0",
        description = "Availables methods for rapport Diabete"
))

public class RapportController {

    private final RapportService rapportService;

    @CrossOrigin("*")
    @PostMapping()
    public ResponseEntity<Rapport> generatePatientReport(@RequestBody PatientWithHistory patientWithHistory){
        log.info("Controller Post generatePatientReport");
       Rapport rapport = rapportService.generatePatientReport(patientWithHistory);
        return new ResponseEntity<>(rapport, HttpStatus.CREATED);
    }
}
