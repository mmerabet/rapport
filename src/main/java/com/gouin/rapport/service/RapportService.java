package com.gouin.rapport.service;

import com.gouin.rapport.model.PatientWithHistory;
import com.gouin.rapport.model.Rapport;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class RapportService {

    public Rapport generatePatientReport(PatientWithHistory patientWithHistory) {
        return null;
    }
    public int calculateAge(String birthdate){
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateNow = LocalDate.now();
            LocalDate localDateBirthDate = LocalDate.parse(birthdate, dateFormat);
            return Period.between(localDateBirthDate, dateNow).getYears();
        }
}
