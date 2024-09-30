package com.wedding.rsvp.dto;

import com.wedding.rsvp.Guest;
import jakarta.validation.constraints.NotBlank;

public record GuestAddDTO(
        @NotBlank(message = "Name is mandatory") String name,
        boolean willAttend,
        String allergies,
        String specialRequests,
        boolean plusOne) {

    public static GuestAddDTO fromEntity(Guest guest) {
        return new GuestAddDTO(
                guest.getName(),
                guest.isWillAttend(),
                guest.getAllergies(),
                guest.getSpecialRequests(),
                guest.isPlusOne()
        );
    }

    public Guest toEntity() {
        return new Guest(name, willAttend, allergies, specialRequests, plusOne);
    }
}
