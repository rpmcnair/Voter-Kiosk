package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.ArrayList;

/*
 * Represents a political candidate with a name, party affiliation, policies, and election status.
 * This class contains information about creating candidates running for office in an election.
 */
public class Candidate implements Writable {
    private String name;
    private String partyAffiliation;
    private String position;
    private List<String> platform;
    private boolean status;

    // Constructs a candidate with name, party affiliation, and position
    public Candidate(String name, String partyAffiliation, String position) {
        this.name = name;
        this.partyAffiliation = partyAffiliation;
        this.position = position;
        this.platform = new ArrayList<>();
        this.status = false;
    }

    // METHODS
    public String getName() {
        return name;
    }

    public String getPartyAffiliation() {
        return partyAffiliation;
    }

    public String getPosition() {
        return position;
    }

    public List<String> getPlatform() {
        return platform;
    }

    public Boolean getStatus() {
        return status;
    }

    // Modifies: this
    // EFFECTS: add policy to platform
    public void addPolicies(String policy) {
        platform.add(policy);
    }

    // Modifies: this
    // EFFECTS: mark candidate as winner
    public void markAsWinner() {
        status = true;
    }

    // Modifies: this
    // EFFECTS: check status won
    public boolean isWinner() {
        return status;
    }

    public void setPlatform(List<String> platform) {
        this.platform = platform;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("partyAffiliation", partyAffiliation);
        json.put("position", position);
        json.put("platform", platform);
        json.put("status", status);
        return json;
    }
}
