package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the CandidateRepository class.
 * This test class covers cases for adding and retrieving candidates.
 */
public class CandidateRepositoryTest {

    private CandidateRepository testCandidateRepository;
    private Candidate candidate1;
    private Candidate candidate2;


    @BeforeEach
    void setUp() {
        testCandidateRepository = new CandidateRepository();
        candidate1 = new Candidate("Jane", "Liberal", "Mayor");
        candidate2 = new Candidate("Bill", "NDP", "City Council");
    }

    // Test that you can add a candidate to the list
    @Test
    void testAddCandidate(){
        testCandidateRepository.addCandidate(candidate1);
        testCandidateRepository.addCandidate(candidate2);
        assertEquals(2, testCandidateRepository.getListOfCandidate().size());
        assertTrue(testCandidateRepository.getListOfCandidate().contains(candidate1));
        assertTrue(testCandidateRepository.getListOfCandidate().contains(candidate2));
    }

    // Test that you can find a candidate by name
    @Test
    void testFindCandidate(){
        testCandidateRepository.addCandidate(candidate1);
        testCandidateRepository.addCandidate(candidate2);
        assertTrue(testCandidateRepository.getListOfCandidate().contains
                (testCandidateRepository.findCandidateByName("Jane")));
        assertTrue(testCandidateRepository.getListOfCandidate().contains
                (testCandidateRepository.findCandidateByName("Bill")));
        assertFalse(testCandidateRepository.getListOfCandidate().contains
                (testCandidateRepository.findCandidateByName("Jake")));

    }

    // Test that you can remove a candidate by name
    @Test
    void testRemoveCandidate(){
        assertEquals(0, testCandidateRepository.getListOfCandidate().size());
        testCandidateRepository.addCandidate(candidate1);
        testCandidateRepository.addCandidate(candidate2);
        assertEquals(2, testCandidateRepository.getListOfCandidate().size());
        testCandidateRepository.removeCandidate("Jane");
        assertEquals(1, testCandidateRepository.getListOfCandidate().size());
        assertFalse(testCandidateRepository.getListOfCandidate().contains
                (testCandidateRepository.findCandidateByName("Jane")));
        assertTrue(testCandidateRepository.getListOfCandidate().contains
                (testCandidateRepository.findCandidateByName("Bill")));
        testCandidateRepository.removeCandidate("Bill");
        assertTrue(testCandidateRepository.getListOfCandidate().isEmpty());

    }

    @Test
    public void testRemoveCandidateDoesNotExist() {
        assertEquals(0, testCandidateRepository.getListOfCandidate().size());
        testCandidateRepository.addCandidate(candidate1);
        testCandidateRepository.addCandidate(candidate2);
        assertEquals(2, testCandidateRepository.getListOfCandidate().size());
        assertFalse(testCandidateRepository.removeCandidate("Nonexistent Candidate"));
        assertEquals(2, testCandidateRepository.getListOfCandidate().size());
        assertTrue(testCandidateRepository.getListOfCandidate().contains(candidate1));
        assertTrue(testCandidateRepository.getListOfCandidate().contains(candidate2));
    }

    // Test that you can set a candidate repository
    @Test
    void testSetAllCandidates(){
        CandidateRepository candidateRepository = new CandidateRepository();
        Candidate candidate1 = new Candidate("Bob", "ABC", "Mayor");
        Candidate candidate2 = new Candidate("Ken", "Liberal", "Mayor");
        List<Candidate> newCandidates = Arrays.asList(candidate1, candidate2);

        candidateRepository.setAllCandidates(newCandidates);

        assertEquals(2, candidateRepository.getListOfCandidate().size());
        assertTrue(candidateRepository.getListOfCandidate().contains(candidate1));
        assertTrue(candidateRepository.getListOfCandidate().contains(candidate2));
    }

}
