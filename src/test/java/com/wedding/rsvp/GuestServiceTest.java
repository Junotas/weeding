package com.wedding.rsvp;

import com.wedding.rsvp.dto.GuestAddDTO;
import com.wedding.rsvp.dto.GuestUpdateDTO;
import com.wedding.rsvp.dto.GuestResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GuestServiceTest {

    private GuestService guestService;
    private GuestRepository guestRepository;

    @BeforeEach
    void setUp() {
        guestRepository = Mockito.mock(GuestRepository.class);
        guestService = new GuestService(guestRepository);
    }

    @Test
    void testGetAllGuests_emptyList() {
        // Arrange
        when(guestRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<GuestResponseDTO> result = guestService.getAllGuests();

        // Assert
        assertTrue(result.isEmpty(), "Guest list should be empty initially");
        verify(guestRepository, times(1)).findAll();
    }

    @Test
    void testAddGuest() {
        // Arrange
        Guest guest = new Guest("John Doe", true, "Peanuts", "None", false);
        guest.setId(UUID.randomUUID()); // Set the ID to simulate database behavior
        when(guestRepository.save(any(Guest.class))).thenAnswer(invocation -> {
            Guest savedGuest = invocation.getArgument(0);
            savedGuest.setId(UUID.randomUUID()); // Simulate ID generation by the database
            return savedGuest;
        });

        GuestAddDTO dto = new GuestAddDTO("John Doe", true, "Peanuts", "None", false);

        // Act
        GuestResponseDTO result = guestService.addGuest(dto);

        // Assert
        assertNotNull(result.id(), "Created guest should have an ID");
        assertEquals("John Doe", result.name());
        verify(guestRepository, times(1)).save(any(Guest.class));
    }


    @Test
    void testUpdateGuest() {
        // Arrange
        UUID guestId = UUID.randomUUID();
        Guest existingGuest = new Guest("John Doe", true, "Peanuts", "None", false);
        existingGuest.setId(guestId);
        when(guestRepository.findById(guestId)).thenReturn(Optional.of(existingGuest));
        when(guestRepository.save(any(Guest.class))).thenReturn(existingGuest);

        GuestUpdateDTO updateDTO = new GuestUpdateDTO("John Doe Updated", true, "Peanuts", "Vegan", true);

        // Act
        GuestResponseDTO result = guestService.updateGuest(guestId, updateDTO);

        // Assert
        assertEquals("John Doe Updated", result.name());
        assertEquals("Vegan", result.specialRequests());
        verify(guestRepository, times(1)).save(any(Guest.class));
    }

    @Test
    void testDeleteGuest() {
        // Arrange
        UUID guestId = UUID.randomUUID();
        when(guestRepository.existsById(guestId)).thenReturn(true);

        // Act
        guestService.deleteGuest(guestId);

        // Assert
        verify(guestRepository, times(1)).deleteById(guestId);
    }

    @Test
    void testUpdateGuest_guestNotFound() {
        // Arrange
        UUID guestId = UUID.randomUUID();
        GuestUpdateDTO updateDTO = new GuestUpdateDTO("Jane Doe", false, "Nuts", "None", false);
        when(guestRepository.findById(guestId)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> guestService.updateGuest(guestId, updateDTO),
                "Expected updateGuest() to throw, but it didn't"
        );

        assertEquals("Guest not found", exception.getMessage());
    }

    @Test
    void testDeleteGuest_guestNotFound() {
        // Arrange
        UUID guestId = UUID.randomUUID();
        when(guestRepository.existsById(guestId)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> guestService.deleteGuest(guestId),
                "Expected deleteGuest() to throw, but it didn't"
        );

        assertEquals("Guest not found", exception.getMessage());
    }
}
