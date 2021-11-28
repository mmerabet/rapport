package com.gouin.rapport.model;

import lombok.Data;

@Data
public class Patient {
    private int id;
    private String firstName;
    private String lastName;
    private String birthdate;
    private String gender;
    private String address;
    private String phone;
}
