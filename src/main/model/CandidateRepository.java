package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.ArrayList;

/*
 * Represents a list of political candidates with a name, party affiliation, policies, and election status.
 * This class contains information about creating a list of candidates running for office in an election.
 */
public class CandidateRepository implements Writable {

    private List<Candidate> candidates;

    // Constructs a candidate repository with list of all candidates running
    public CandidateRepository() {
        this.candidates = new ArrayList<>();
    }

    // METHODS
    public List<Candidate> getListOfCandidate() {
        return candidates;
    }

    // Modifies: this
    // EFFECTS: adds a candidate to the list
    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
        EventLog.getInstance().logEvent(new Event("Candidate Added."));
    }

    // EFFECTS: search for candidate by name
    public Candidate findCandidateByName(String candidateName) {
        for (Candidate candidate : candidates) {
            if (candidate.getName().equals(candidateName)) {
                return candidate;
            }
        }
        return null;
    }

    public void setAllCandidates(List<Candidate> newCandidates) {
        this.candidates = newCandidates;
    }

    // Modifies: this
    // EFFECTS: removes a candidate from the list by name
    public boolean removeCandidate(String name) {
        for (int i = 0; i < candidates.size(); i++) {
            if (candidates.get(i).getName().equals(name)) {
                candidates.remove(i);
                EventLog.getInstance().logEvent(new Event("Candidate Removed."));
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("candidatesList", thingiesToJson());
        return json;
    }

    // EFFECTS: returns things in this repository as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Candidate t : candidates) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
