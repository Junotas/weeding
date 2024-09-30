package com.wedding.rsvp;

import com.wedding.rsvp.dto.GuestAddDTO;
import com.wedding.rsvp.dto.GuestUpdateDTO;
import com.wedding.rsvp.dto.GuestResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    public ResponseEntity<GuestResponseDTO> addGuest(@Valid @RequestBody GuestAddDTO guestAddDTO) {
        GuestResponseDTO response = guestService.addGuest(guestAddDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestResponseDTO> updateGuest(
            @PathVariable Long id,
            @Valid @RequestBody GuestUpdateDTO guestUpdateDTO) {
        GuestResponseDTO response = guestService.updateGuest(id, guestUpdateDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<GuestResponseDTO>> getAllGuests() {
        List<GuestResponseDTO> guests = guestService.getAllGuests();
        return ResponseEntity.ok(guests);
    }
}
