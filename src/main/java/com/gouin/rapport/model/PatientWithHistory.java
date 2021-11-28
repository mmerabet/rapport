package com.gouin.rapport.model;

import lombok.Data;

@Data
public class PatientWithHistory {
    private Patient patient;
    private History history;
}
