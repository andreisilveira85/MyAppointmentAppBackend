package com.example.myAppoinmentApp.controllers;

import com.example.myAppoinmentApp.domain.appointment.Appointment;
import com.example.myAppoinmentApp.dto.AppointmentRequestDTO;
import com.example.myAppoinmentApp.dto.AppointmentResponseDTO;
import com.example.myAppoinmentApp.repositories.AppointmentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointments() {
        List<AppointmentResponseDTO> appointments = appointmentRepository.findAll()
                .stream()
                .map(AppointmentResponseDTO::new)
                .toList();
        return ResponseEntity.ok(appointments);
    }

    @PostMapping
    public ResponseEntity<?> postAppointment(@RequestBody @Valid AppointmentRequestDTO body) {
        Appointment newAppointment = new Appointment(body);

        appointmentRepository.save(newAppointment);
        return ResponseEntity.ok(new AppointmentResponseDTO(newAppointment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable String id) {
        return appointmentRepository.findById(id)
                .map(appointment -> {
                    appointmentRepository.delete(appointment);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
