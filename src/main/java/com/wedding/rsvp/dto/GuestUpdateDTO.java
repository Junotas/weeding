package com.wedding.rsvp.dto;

import com.wedding.rsvp.Guest;
import jakarta.validation.constraints.NotBlank;

public record GuestUpdateDTO(
        @NotBlank(message = "Name is mandatory") String name,
        boolean willAttend,
        String allergies,
        String specialRequests,
        boolean plusOne) {

    public static GuestUpdateDTO fromEntity(Guest guest) {
        return new GuestUpdateDTO(
                guest.getName(),
                guest.isWillAttend(),
                guest.getAllergies(),
                guest.getSpecialRequests(),
                guest.isPlusOne()
        );
    }

    public Guest toEntity(Long id) {
        Guest guest = new Guest(name, willAttend, allergies, specialRequests, plusOne);
        guest.setId(id);
        return guest;
    }
}
