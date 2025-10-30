package com.example.patient_service.mapper;

import com.example.patient_service.dto.request.PatientRequestDTO;
import com.example.patient_service.dto.response.PatientResponseDTO;
import com.example.patient_service.entity.Patient;

public class PatientMapper {

    public static PatientResponseDTO toDTO(Patient patient){

        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();

        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setAddress(patient.getAddress());

        return patientResponseDTO;
    }

    public static Patient toModel(PatientRequestDTO patientRequestDTO){

        Patient patient = new Patient();

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());

        return patient;
    }
}
