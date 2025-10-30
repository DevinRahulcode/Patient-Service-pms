package com.example.patient_service.controller;


import com.example.patient_service.dto.request.PatientRequestDTO;
import com.example.patient_service.dto.response.PatientResponseDTO;
import com.example.patient_service.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Tag(name = "Patient", description = "Endpoints for the do the crud operations")
@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get All Patients", description = "All the Registered Users will be shown")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){
        List<PatientResponseDTO> patients = patientService.getAllPatients();

        return ResponseEntity.ok().body(patients);
    }

    @GetMapping("/patient/{id}")
    @Operation(summary = "Get user by ID", description = "Provide an ID to look up specific user")
    public ResponseEntity<PatientResponseDTO> getById(@PathVariable UUID id){
        PatientResponseDTO patient = patientService.getPatientById(id);

        return ResponseEntity.ok(patient);

    }

    @PostMapping("/create-patient")
    @Operation(summary = "Create Patients" , description = "creating new patients ")
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @PutMapping("/update-patient/{id}")
    @Operation(summary = "updating existing patients", description = "updating existing patients using the ID")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id ,@Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);
        return  ResponseEntity.ok().body(patientResponseDTO);
    }

    @DeleteMapping("/delete-patient/{id}")
    @Operation(summary = "Deleting Patients", description = "Deleting new patients using ID")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
