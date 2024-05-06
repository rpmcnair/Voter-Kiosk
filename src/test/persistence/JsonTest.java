package persistence;

import model.Candidate;


import static org.junit.jupiter.api.Assertions.assertEquals;

// Citation: JsonSerializationDemo
public class JsonTest {
    protected void checkCandidate(String name, String party, String position, Candidate candidate) {
        assertEquals(name, candidate.getName());
        assertEquals(party, candidate.getPartyAffiliation());
        assertEquals(position, candidate.getPosition());
    }
}
