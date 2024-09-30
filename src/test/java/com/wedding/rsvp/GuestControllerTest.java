package com.wedding.rsvp;

import com.wedding.rsvp.dto.GuestAddDTO;
import com.wedding.rsvp.dto.GuestResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GuestController.class)
class GuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestService guestService;

    @Test
    void testGetAllGuests_emptyList() throws Exception {
        when(guestService.getAllGuests()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/guests")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void testAddGuest() throws Exception {
        GuestResponseDTO response = new GuestResponseDTO(
                UUID.randomUUID(), "John Doe", true, "Peanuts", "None", false);
        when(guestService.addGuest(any(GuestAddDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/guests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"willAttend\":true,\"allergies\":\"Peanuts\",\"specialRequests\":\"None\",\"plusOne\":false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.willAttend").value(true))
                .andExpect(jsonPath("$.allergies").value("Peanuts"));
    }

    @Test
    void testDeleteGuest() throws Exception {
        mockMvc.perform(delete("/api/guests/{id}", UUID.randomUUID()))
                .andExpect(status().isNoContent());
    }
}
