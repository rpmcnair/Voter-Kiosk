package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Election class.
 * This test class covers cases for adding polling locations and updating election details.
 */
class ElectionTest {

    private Election testElection;
    private Candidate testCandidate;
    private Candidate testCandidate1;

    @BeforeEach
    void setUp() {
        testElection = new Election("November 10th, 8am-8pm");
        testCandidate = new Candidate("Bill", "NDP", "Mayor");
        testCandidate1 = new Candidate("Henry", "Conservative", "Mayor");
    }

    // Test polling place is empty
    @Test
    public void testPollingPlace() {
        assertTrue(testElection.getPollingPlaces().isEmpty());
    }

    // Test you can add a polling place
    @Test
    void testAddPollingPlace() {
        testElection.addPollingPlace("City Hall");
        List<String> pollingPlaces = testElection.getPollingPlaces();
        assertEquals("Election Date and Time: November 10th, 8am-8pm", testElection.getDateAndTime());
        assertTrue(pollingPlaces.contains("City Hall"));
    }

    // Test you can update the data and time of elections
    @Test
    void testUpdateElectionDetails(){
        testElection.updateElectionDetails("November 10th, 7am-9pm");
        assertEquals("Election Date and Time: November 10th, 7am-9pm", testElection.getDateAndTime());
    }

    // Test that you can add candidates
    @Test
    void testAddCandidates(){
        testElection.addCandidate(testCandidate);
        testElection.addCandidate(testCandidate1);
        assertTrue(testElection.getCandidates().contains(testCandidate));
        assertTrue(testElection.getCandidates().contains(testCandidate1));
    }
}