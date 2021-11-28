package com.gouin.rapport.model;

import lombok.Data;

import java.util.List;
@Data
public class History {
    private String id;
    private List<Consultation> consultations;
}
