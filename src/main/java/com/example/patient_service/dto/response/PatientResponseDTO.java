package com.example.patient_service.dto.response;


import lombok.Data;

@Data
public class PatientResponseDTO {

    private String id;
    private String name;
    private String email;
    private String address;
}
