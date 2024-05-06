package persistence;

import model.CandidateRepository;
import model.Candidate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads candidate repository from JSON data stored in file
// Citation: JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads CandidateRep from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CandidateRepository read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCandidateRepository(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses candidate from JSON object and returns it
    private CandidateRepository parseCandidateRepository(JSONObject jsonObject) {
        CandidateRepository cr = new CandidateRepository();
        addCandidates(cr, jsonObject);
        return cr;
    }

    // MODIFIES: cr
    // EFFECTS: parses candidates from JSON object and adds them to cr
    private void addCandidates(CandidateRepository cr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("candidatesList");
        for (Object json : jsonArray) {
            JSONObject nextCandidate = (JSONObject) json;
            addCandidate(cr, nextCandidate);
        }
    }

    // MODIFIES: cr
    // EFFECTS: parses candidate from JSON object and adds it to Candidate Repository
    private void addCandidate(CandidateRepository cr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String partyAffiliation = jsonObject.getString("partyAffiliation");
        String position = jsonObject.getString("position");
        JSONArray platformJsonArray = jsonObject.getJSONArray("platform");
        List<String> platform = new ArrayList<>();
        for (int i = 0; i < platformJsonArray.length(); i++) {
            platform.add(platformJsonArray.getString(i));
        }
        boolean status = jsonObject.getBoolean("status");
        Candidate candidate = new Candidate(name, partyAffiliation, position);
        cr.addCandidate(candidate);
    }

}
