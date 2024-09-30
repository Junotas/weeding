package com.wedding.rsvp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GuestRepositoryTest {

    @Autowired
    private GuestRepository guestRepository;

    @Test
    void testSaveAndFindGuest() {
        // Arrange
        Guest guest = new Guest("John Doe", true, "Peanuts", "None", false);

        // Act
        guestRepository.save(guest);
        List<Guest> guests = guestRepository.findAll();

        // Assert
        assertFalse(guests.isEmpty(), "Guests should be found in the repository");
        assertEquals("John Doe", guests.get(0).getName());
    }

    @Test
    void testDeleteGuest() {
        // Arrange
        Guest guest = new Guest("Jane Doe", true, "Shellfish", "None", false);
        guestRepository.save(guest);
        UUID guestId = guest.getId();

        // Act
        guestRepository.deleteById(guestId);
        Optional<Guest> deletedGuest = guestRepository.findById(guestId);

        // Assert
        assertTrue(deletedGuest.isEmpty(), "Guest should be deleted");
    }

    @Test
    void testFindGuestById() {
        // Arrange
        Guest guest = new Guest("Alice Doe", true, "Nuts", "None", true);
        guestRepository.save(guest);
        UUID guestId = guest.getId();

        // Act
        Optional<Guest> foundGuest = guestRepository.findById(guestId);

        // Assert
        assertTrue(foundGuest.isPresent(), "Guest should be found in the repository");
        assertEquals(guestId, foundGuest.get().getId());
    }
}
