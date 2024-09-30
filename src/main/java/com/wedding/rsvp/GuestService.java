package com.wedding.rsvp;

import com.wedding.rsvp.dto.GuestAddDTO;
import com.wedding.rsvp.dto.GuestUpdateDTO;
import com.wedding.rsvp.dto.GuestResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Transactional
    public GuestResponseDTO addGuest(GuestAddDTO guestAddDTO) {
        Guest guest = guestAddDTO.toEntity();
        guestRepository.save(guest);
        return GuestResponseDTO.fromEntity(guest);
    }

    @Transactional
    public GuestResponseDTO updateGuest(Long id, GuestUpdateDTO guestUpdateDTO) {
        Guest existingGuest = guestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Guest not found"));

        Guest updatedGuest = guestUpdateDTO.toEntity(id);
        guestRepository.save(updatedGuest);

        return GuestResponseDTO.fromEntity(updatedGuest);
    }

    public List<GuestResponseDTO> getAllGuests() {
        return guestRepository.findAll().stream()
                .map(GuestResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteGuest(Long id) {
        if (!guestRepository.existsById(id)) {
            throw new IllegalArgumentException("Guest not found");
        }
        guestRepository.deleteById(id);
    }
}