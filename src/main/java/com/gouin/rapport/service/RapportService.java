package com.gouin.rapport.service;

import com.gouin.rapport.enums.DiabetePatientRiskLevel;
import com.gouin.rapport.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class RapportService {
    private DiabetePatientRiskLevel diabetePatientRiskLevel;

    public Rapport generatePatientReport(PatientWithHistory patientWithHistory ) {
        return calculatePatientReport(patientWithHistory.getHistory(), patientWithHistory.getPatient());
    }

    public Rapport calculatePatientReport(History history, Patient patient) {
        int patientAge = calculateAge(patient.getBirthdate());
        int triggerWords = numberTriggerDiabete(history);
        Rapport rapport;
        if (patient.getGender().equals("Feminin")) {
            rapport = calculateWomanReport(patient, patientAge, triggerWords);
        } else {
            rapport = calculateManReport(patient, patientAge, triggerWords);
        }
        return rapport;
    }

    public int calculateAge(String birthdate) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateNow = LocalDate.now();
        LocalDate localDateBirthDate = LocalDate.parse(birthdate, dateFormat);
        return Period.between(localDateBirthDate, dateNow).getYears();
    }

    public int numberTriggerDiabete(History history) {
        List<String> triggerWords = Arrays.asList("Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur", "Anormal", "Cholestérol", "Vertige", "Rechute", "Réaction", "Anticorps");
        int triggerNumber = 0;
        String allReport = history.getConsultations()
                .stream()
                .map(Consultation::getRecommendations)
                .reduce("", (partialString, element) -> partialString + element);

        for (String triggerWord : triggerWords) {
            if (allReport.toLowerCase().contains(triggerWord.toLowerCase())) {
                triggerNumber++;
            }
        }
        return triggerNumber;
    }

    public Rapport calculateWomanReport(Patient patient, int age, int triggerWord) {

        if (triggerWord <= 1 || age < 30 && triggerWord <= 3) {
            diabetePatientRiskLevel = DiabetePatientRiskLevel.NONE;
        } else if (age >= 30 && triggerWord >= 2 && triggerWord <= 5) {
            diabetePatientRiskLevel = DiabetePatientRiskLevel.BORDELINE;
        } else if (age < 30 && (triggerWord >= 3 && triggerWord <= 6) || age >= 30 && (triggerWord == 6 || triggerWord == 7)) {
            diabetePatientRiskLevel = DiabetePatientRiskLevel.IN_DANGER;
        } else if (age < 30 && triggerWord >= 7 || age >= 30 && triggerWord >= 8) {
            diabetePatientRiskLevel = DiabetePatientRiskLevel.EARLY_ONSET;
        }
        return new Rapport(patient.getFirstName(), patient.getLastName(), patient.getGender(), age, diabetePatientRiskLevel);
    }

    public Rapport calculateManReport(Patient patient, int age, int triggerWord) {

        if (triggerWord <= 1 || age < 30 && triggerWord <= 2) {
            diabetePatientRiskLevel = DiabetePatientRiskLevel.NONE;
        } else if (age >= 30 && triggerWord >= 2 && triggerWord <= 5) {
            diabetePatientRiskLevel = DiabetePatientRiskLevel.BORDELINE;
        } else if (age < 30 && (triggerWord >= 3 && triggerWord <= 4) || age >= 30 && (triggerWord == 6 || triggerWord == 7)) {
            diabetePatientRiskLevel = DiabetePatientRiskLevel.IN_DANGER;
        } else if (age < 30 && triggerWord >= 5 || age >= 30 && triggerWord >= 8) {
            diabetePatientRiskLevel = DiabetePatientRiskLevel.EARLY_ONSET;
        }
        return new Rapport(patient.getFirstName(), patient.getLastName(), patient.getGender(), age, diabetePatientRiskLevel);
    }
}
