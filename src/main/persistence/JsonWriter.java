package persistence;

import model.CandidateRepository;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of Candidates to file
// Citation: JsonSerializationDemo
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Candidates to file
    public void write(CandidateRepository cr) throws FileNotFoundException {
        JSONObject json = cr.toJson();
        open();
        saveToFile(json.toString(TAB));
        close();
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }
}
