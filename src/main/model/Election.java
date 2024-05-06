package model;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents an election with the date and time and a list of polling loactions.
 * This class contains information about creating an election.
 */
public class Election {

    private String dateAndTime;
    private List<String> pollingPlaces;
    private List<Candidate> candidates;

    // Constructs an Election, with the data/time, polling locations, and candidates running
    public Election(String dateAndTime) {
        this.dateAndTime = dateAndTime;
        this.pollingPlaces = new ArrayList<>();
        this.candidates = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Create sentence including date and time
    public String getDateAndTime() {
        return "Election Date and Time: " + dateAndTime;
    }

    public List<String> getPollingPlaces() {
        return pollingPlaces;
    }

    // MODIFIES: this
    // EFFECTS: update election details, either time or polling place
    public void updateElectionDetails(String newDateAndTime) {
        this.dateAndTime = newDateAndTime;
    }

    // MODIFIES: this, pollingList
    // EFFECTS: update election details, either time or polling place
    public void addPollingPlace(String location) {
        pollingPlaces.add(location);
    }

    // New method to add a candidate
    public void addCandidate(Candidate candidate) {
        this.candidates.add(candidate);
    }

    // Optionally, a method to get all candidates
    public List<Candidate> getCandidates() {
        return this.candidates;
    }
}
