package com.wedding.rsvp;

import com.wedding.rsvp.dto.GuestAddDTO;
import com.wedding.rsvp.dto.GuestUpdateDTO;
import com.wedding.rsvp.dto.GuestResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Transactional
    public GuestResponseDTO addGuest(GuestAddDTO guestAddDTO) {
        Guest guest = new Guest(
                guestAddDTO.name(),
                guestAddDTO.willAttend(),
                guestAddDTO.allergies(),
                guestAddDTO.specialRequests(),
                guestAddDTO.plusOne()
        );
        guestRepository.save(guest);
        return GuestResponseDTO.fromEntity(guest);
    }

    @Transactional
    public GuestResponseDTO updateGuest(UUID id, GuestUpdateDTO guestUpdateDTO) {
        Guest existingGuest = guestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Guest not found"));

        existingGuest.setName(guestUpdateDTO.name());
        existingGuest.setWillAttend(guestUpdateDTO.willAttend());
        existingGuest.setAllergies(guestUpdateDTO.allergies());
        existingGuest.setSpecialRequests(guestUpdateDTO.specialRequests());
        existingGuest.setPlusOne(guestUpdateDTO.plusOne());

        guestRepository.save(existingGuest);
        return GuestResponseDTO.fromEntity(existingGuest);
    }

    public List<GuestResponseDTO> getAllGuests() {
        return guestRepository.findAll().stream()
                .map(GuestResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteGuest(UUID id) {
        if (!guestRepository.existsById(id)) {
            throw new IllegalArgumentException("Guest not found");
        }
        guestRepository.deleteById(id);
    }
}
