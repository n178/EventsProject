package tn.esprit.eventsproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Tache;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EventServicesImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private LogisticsRepository logisticsRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddParticipant() {
        // Arrange
        Participant participant = new Participant(/* initialize participant */);

        // Mock the behavior of participantRepository
        when(participantRepository.save(participant)).thenReturn(participant);

        // Act
        Participant result = eventServices.addParticipant(participant);

        // Assert
        assertEquals(participant, result);

        // Verify that save method was called once
        verify(participantRepository, times(1)).save(participant);
    }

    @Test
    public void testAddAffectEvenParticipant() {
        // Arrange
        Event event = new Event(/* initialize event */);
        int participantId = 1;
        Participant participant = new Participant(/* initialize participant */);

        // Mock the behavior of participantRepository
        when(participantRepository.findById(participantId)).thenReturn(Optional.of(participant));
        when(eventRepository.save(event)).thenReturn(event);

        // Act
        Event result = eventServices.addAffectEvenParticipant(event, participantId);

        // Assert
        assertEquals(event, result);

        // Verify that findById and save methods were called once each
        verify(participantRepository, times(1)).findById(participantId);
        verify(eventRepository, times(1)).save(event);
    }

    // Add more test cases for other methods as needed
}
