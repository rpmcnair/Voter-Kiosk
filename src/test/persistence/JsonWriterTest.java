package persistence;

import model.Candidate;
import model.CandidateRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Citation: JsonSerializationDemo
class JsonWriterTest extends JsonTest {
    //NOTE: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            CandidateRepository cr = new CandidateRepository();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyElection() {
        try {
            CandidateRepository cr = new CandidateRepository();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyElection.json");
            writer.open();
            writer.write(cr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyElection.json");
            cr = reader.read();
            assertEquals(0, cr.getListOfCandidate().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralElection() {
        try {
            CandidateRepository cr = new CandidateRepository();
            cr.addCandidate(new Candidate("Alice", "Liberal", "Mayor"));
            cr.addCandidate(new Candidate("Bob", "NDP", "Council"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralElection.json");
            writer.open();
            writer.write(cr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralElection.json");
            cr = reader.read();
            List<Candidate> candidates = cr.getListOfCandidate();
            assertEquals(2, candidates.size());
            checkCandidate("Alice", "Liberal",  "Mayor", candidates.get(0));
            checkCandidate("Bob", "NDP", "Council", candidates.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

