package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

/**
 * Tests for the Candidate class.
 * This test class covers cases for marking candidates as winners and making platforms.
 */
public class CandidateTest {

    private Candidate testCandidate;
    private Candidate testCandidate2;


    @BeforeEach
    void setUp() {
        testCandidate = new Candidate("Jane", "Liberal", "Mayor");
        testCandidate2 = new Candidate("Bill", "NDP", "City Council");
    }

    // Test that you can make constructors
    @Test
    void testConstructor() {
        assertEquals("Jane", testCandidate.getName());
        assertEquals("Liberal", testCandidate.getPartyAffiliation());
        assertEquals("Mayor", testCandidate.getPosition());
        assertEquals("Bill", testCandidate2.getName());
        assertEquals("NDP", testCandidate2.getPartyAffiliation());
        assertEquals("City Council", testCandidate2.getPosition());
    }

    // Test that their policy platform is empty
    @Test
    public void testCandidatePlatform() {
        assertTrue(testCandidate.getPlatform().isEmpty());
    }

    // Test that you can add policy platform is empty
    @Test
    public void testAddCandidatePlatform() {
        testCandidate.addPolicies("Improve Health Care");
        testCandidate.addPolicies("Housing");
        List<String> candidatePlatform = testCandidate.getPlatform();
        assertEquals(2, candidatePlatform.size());
        assertEquals("Improve Health Care", candidatePlatform.get(0));
        assertEquals("Housing", candidatePlatform.get(1));
    }

    // Test that candidate is not a winner by default
    @Test
    public void testCandidateNotWinner() {
        assertFalse(testCandidate.isWinner());
    }

    // Check that win status is true
    @Test
    public void testCandidateAsWinner() {
        testCandidate.markAsWinner();
        assertTrue(testCandidate.isWinner());
    }

    //Check that status is accurate
    @Test
    public void testStatus(){
        assertFalse(testCandidate.getStatus());
        testCandidate.markAsWinner();
        assertTrue(testCandidate.getStatus());
    }

    // Test set status
    @Test
    public void testSetStatus(){
        assertFalse(testCandidate.getStatus());
        testCandidate.setStatus(true);
        assertTrue(testCandidate.getStatus());
    }

    // Test set status
    @Test
    public void testSetPlatform(){
        testCandidate.setPlatform(Collections.singletonList("HealthCare"));
        assertEquals(Collections.singletonList("HealthCare"), testCandidate.getPlatform());
    }

}