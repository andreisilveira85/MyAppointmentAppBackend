package com.example.myAppoinmentApp.repositories;

import com.example.myAppoinmentApp.domain.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

}
