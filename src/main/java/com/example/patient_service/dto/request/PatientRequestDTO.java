package com.example.patient_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class PatientRequestDTO {


    @NotNull
    @Size(max = 150, message = "The name should not exists the character size 100")
    private String name;

    @NotNull
    @Email(message = "Please Add a valid email address")
    private String email;

    @NotNull
    private String address;

}
