package com.example.myAppoinmentApp.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record AppointmentRequestDTO(
        LocalDateTime dateTime,

        @NotBlank
        String reason
) {

}