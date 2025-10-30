package com.example.patient_service.service;

import com.example.patient_service.dto.request.PatientRequestDTO;
import com.example.patient_service.dto.response.PatientResponseDTO;
import com.example.patient_service.entity.Patient;
import com.example.patient_service.mapper.PatientMapper;
import com.example.patient_service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private static final Logger logger = LogManager.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getAllPatients() {
        logger.info("Fetching all patients from the database");
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponseDTO> responseList = patients.stream()
                .map(PatientMapper::toDTO)
                .toList();

        logger.debug("Retrieved {} patients", responseList.size());
        return responseList;
    }

    public PatientResponseDTO getPatientById(UUID id) {
        logger.info("Fetching patient with ID: {}", id);
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Patient with ID {} not found", id);
                    return new RuntimeException("A Patient with the ID number does not exist " + id);
                });

        logger.debug("Found patient: {}", patient.getName());
        return PatientMapper.toDTO(patient);
    }

    public PatientResponseDTO createPatient(PatientRequestDTO dto) {
        logger.info("Creating new patient: {}", dto.getName());
        Patient newPatient = patientRepository.save(PatientMapper.toModel(dto));
        logger.debug("Created patient with ID: {}", newPatient.getId());
        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO dto) {
        logger.info("Updating patient with ID: {}", id);
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Patient with ID {} not found for update", id);
                    return new RuntimeException("A Patient with the ID does not exist " + id);
                });

        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setAddress(dto.getAddress());

        Patient updatedPatient = patientRepository.save(patient);
        logger.debug("Updated patient: {}", updatedPatient.getName());
        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(UUID id) {
        logger.warn("Deleting patient with ID: {}", id);
        patientRepository.deleteById(id);
        logger.info("Deleted patient with ID: {}", id);
    }
}
