package com.wedding.rsvp;

import com.wedding.rsvp.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GuestRepository extends JpaRepository<Guest, UUID> {
}
