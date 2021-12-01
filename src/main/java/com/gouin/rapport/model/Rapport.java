package com.gouin.rapport.model;

import com.gouin.rapport.enums.DiabetePatientRiskLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rapport {
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private DiabetePatientRiskLevel diabetePatientRiskLevel;
}
