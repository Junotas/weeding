package com.wedding.rsvp.dto;

import com.wedding.rsvp.Guest;

import java.util.UUID;

public record GuestResponseDTO(
        UUID id,
        String name,
        boolean willAttend,
        String allergies,
        String specialRequests,
        boolean plusOne) {

    public static GuestResponseDTO fromEntity(Guest guest) {
        return new GuestResponseDTO(
                guest.getId(),
                guest.getName(),
                guest.isWillAttend(),
                guest.getAllergies(),
                guest.getSpecialRequests(),
                guest.isPlusOne()
        );
    }
}
