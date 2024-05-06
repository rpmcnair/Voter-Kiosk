package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Party class.
 * This test class covers cases for adding candidates and descriptions.
 */
public class PartyTest {

    private Party party;
    private String description;
    private List<Candidate> candidates;

    @BeforeEach
    void setUp() {
        candidates = new ArrayList<>();
        party = new Party("Liberal");
    }

    // Test you can get party name
    @Test
    void testParty() {
        assertEquals("Liberal", party.getPartyName());
    }

    // Test that you can add a description for parties
    @Test
    void testAddDescription() {
        party.addDescription("This party is centre, their main policies are economic reform");
        assertEquals("This party is centre, their main policies are economic reform",
                party.getDescription());
    }

    // Test that you can add candidate to party
    @Test
    void testAddCandidate() {
        Candidate candidate = new Candidate("Jill", "Liberal", "Mayor");
        party.addCandidate(candidate);
        assertTrue(party.getCandidates().contains(candidate));
    }

    // Test you can add and get parties
    @Test
    void testAddAndGetAllParties() {
        Party party1 = new Party("Liberal");
        Party party2 = new Party("NDP");
        assertTrue(Party.getAllParties().contains(party1));
        assertTrue(Party.getAllParties().contains(party2));
    }

}