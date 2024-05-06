package model;

import java.util.List;
import java.util.ArrayList;

/*
 * Represents of political party with a name, a short description and a list of candidates associated with it.
 * This class contains information about creating a party.
 */
public class Party {

    // Constructs a party
    private String partyName;
    private String description;
    private List<Candidate> candidates;
    private static List<Party> allParties = new ArrayList<>();


    public Party(String name) {
        this.partyName = name;
        this.candidates = new ArrayList<>();
        allParties.add(this);
    }

    // METHODS
    public String getPartyName() {
        return partyName;
    }

    public String getDescription() {
        return description;
    }

    // MODIFIES: this
    // EFFECTS: adds sentence about party platform
    public void addDescription(String description) {
        this.description = description;
    }

    // MODIFIES: this
    // EFFECTS: adds candidates to the party list
    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public static List<Party> getAllParties() {
        return allParties;
    }
}
