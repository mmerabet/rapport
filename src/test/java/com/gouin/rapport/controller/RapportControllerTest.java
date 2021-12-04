package com.gouin.rapport.controller;

import com.gouin.rapport.model.PatientWithHistory;
import com.gouin.rapport.model.Rapport;
import com.gouin.rapport.service.RapportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RapportController.class)
class RapportControllerTest {

       @Autowired
        private MockMvc mockMvc;

    @MockBean
    private RapportService rapportService;

    @Test
    void generatePatientReport() throws Exception {
            when(rapportService.generatePatientReport(any(PatientWithHistory.class))).thenReturn(any(Rapport.class));

            this.mockMvc.perform(post("/patient/add")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "        \"firstName\": \"Marie\",\n" +
                                    "        \"lastName\": \"Durand\",\n" +
                                    "        \"birthdate\": \"19/10/1995\",\n" +
                                    "        \"gender\": \"female\",\n" +
                                    "        \"address\": \"24 rue des andive\",\n" +
                                    "        \"phone\": \"0155264572\"\n" +
                                    "    }")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());

            verify(rapportService, times(1)).generatePatientReport(any(PatientWithHistory.class));
        }
    };
}
