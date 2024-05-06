package persistence;

import model.CandidateRepository;
import model.Candidate;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Citation: JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CandidateRepository cr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {

        }
    }

    @Test
    void testReaderEmptyElection() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyElection.json");
        try {
            CandidateRepository cr = reader.read();
            assertTrue(cr.getListOfCandidate().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralElection() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralElection.json");
        try {
            CandidateRepository cr = reader.read();
            List<Candidate> candidates = cr.getListOfCandidate();
            assertEquals(2, candidates.size());
            checkCandidate("Bill", "Liberal", "Mayor", candidates.get(0));
            checkCandidate("Jake", "NDP", "Council", candidates.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
