package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the Election Application.
 * This class is responsible for creating a user interface for an election. Including adding candidates
 * and marking winners, as well as viewing election details, candidates, and parties.
 * Citation: JsonSerializationDemo
 */
public class ElectionApp {
    private static final String JSON_STORE = "./data/mydata.json";
    private Election election;
    private CandidateRepository candidateRepository;
    private Party party;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private CardLayout cardLayout;
    private JPanel cardPanel;


    // EFFECTS: runs the election application, and creates an election that can be saved
    public ElectionApp() {
        input = new Scanner(System.in);
        candidateRepository = new CandidateRepository();
        election = new Election("2023");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        VoterMenuGUI voterMenuPanel =
                new VoterMenuGUI(election, candidateRepository, cardLayout, cardPanel, jsonReader);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        runElectionApp();
    }

    // MODIFIES: this
    // EFFECTS: processes a users input
    private void runElectionApp() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processMainMenu(command);
            }
        }
    }

    private void processMainMenu(String command) {
        if (command.equals("1")) {
            runVoterMenu();
        } else if (command.equals("2")) {
            runElectionWorkerMenu();
        } else if (command.equals("q")) {
            System.out.println("Exiting the program.");
            System.exit(0);
        } else {
            System.out.println("Invalid choice. Please enter 1, 2, or q to quit.");
        }
    }

    private void displayMenu() {
        System.out.println("Are you a voter or an election worker?");
        System.out.println("1 -> Voter Menu");
        System.out.println("2 -> Election Worker Menu");
        System.out.println("q -> Quit");
    }


    // MODIFIES: this
    // EFFECTS: processes a voter's user input
    private void runVoterMenu() {

        System.out.println("Voter Mode");
        System.out.println("l. Lookup Candidates");
        System.out.println("p. Lookup Party Platforms");
        System.out.println("o. Election Overview");
        System.out.println("q. Quit Voter Mode");

        while (true) {
            String command = input.next().toLowerCase();

            if (command.equals("l")) {
                lookupCandidates();
            } else if (command.equals("p")) {
                lookupPartyPlatforms();
            } else if (command.equals("o")) {
                showElectionDetails();
            } else if (command.equals("q")) {
                System.out.println("Exiting Voter Mode.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Define your election worker menu options
    private void runElectionWorkerMenu() {

        System.out.println("Election Worker Menu");
        System.out.println("\ta. Add a Candidate");
        System.out.println("\tm. Mark Candidate as Winner");
        System.out.println("\ts  save Candidate info to file");
        System.out.println("\tl  load Candidate info from file");
        System.out.println("\tq. Quit Worker Mode");

        while (true) {
            String command = input.next().toLowerCase();

            if (command.equals("a")) {
                addCandidate();
            } else if (command.equals("m")) {
                markCandidateAsWinner();
            } else if (command.equals("s")) {
                saveCandidates();
            } else if (command.equals("l")) {
                loadCandidates();
            } else if (command.equals("q")) {
                System.out.println("Exiting Worker Mode.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // METHODS

    // MODIFIES: this
    // EFFECTS: adds Candidate to list with text
    private void addCandidate() {
        System.out.print("Enter candidate's name: ");
        String name = input.next();
        System.out.print("Enter candidate's party affiliation: ");
        String party = input.next();
        System.out.print("Please specify the office for which the candidate is running: ");
        String position = input.next();

        Candidate candidate = new Candidate(name, party, position);

        System.out.print("Enter candidate's policies (comma-separated): ");
        input.nextLine();
        String policiesInput = input.nextLine();
        String[] policies = policiesInput.split(",\\s*");
        for (String policy : policies) {
            candidate.addPolicies(policy);
        }
        candidateRepository.addCandidate(candidate);

        System.out.println("Candidate added: " + candidate.getName());
    }

    // REQUIRES: Choose from list of candidate
    // MODIFIES: this, Candidate
    // EFFECTS: Marks one candidate as winner
    private void markCandidateAsWinner() {
        List<Candidate> candidates = candidateRepository.getListOfCandidate();
        System.out.println("Candidates:");

        for (int i = 0; i < candidates.size(); i++) {
            System.out.println((i + 1) + ". " + candidates.get(i).getName());
        }

        System.out.print("Enter the number of the candidate to mark as a winner (0 to cancel): ");
        int choice = input.nextInt();

        if (choice > 0 && choice <= candidates.size()) {
            Candidate selectedCandidate = candidates.get(choice - 1);
            selectedCandidate.markAsWinner();
            System.out.println(selectedCandidate.getName() + " has been marked as a winner.");
        } else if (choice == 0) {
            System.out.println("No candidate marked as a winner.");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }


    // EFFECTS: looks up candidate and responds with commands
    private void lookupCandidates() {
        List<Candidate> candidates = candidateRepository.getListOfCandidate();

        if (candidates.isEmpty()) {
            System.out.println("No candidates available.");
            return;
        }
        while (true) {
            System.out.println("Lookup Candidates:");
            System.out.println("Enter the name of the candidate you want to look up:");
            String candidateName = input.next();
            Candidate selectedCandidate = candidateRepository.findCandidateByName(candidateName);

            if (selectedCandidate != null) {
                System.out.println("Candidate Information:");
                System.out.println("Name: " + selectedCandidate.getName());
                System.out.println("Party Affiliation: " + selectedCandidate.getPartyAffiliation());
                System.out.println("Position: " + selectedCandidate.getPosition());
            } else {
                System.out.println("Candidate not found.");
            }
            if (input.next().equals("q")) {
                break;
            }
        }
    }

    // EFFECTS: looks up party info
    private void lookupPartyPlatforms() {
        List<Party> parties = party.getAllParties();
        if (parties.isEmpty()) {
            System.out.println("No parties available.");
        } else {
            for (Party party : parties) {
                List<Candidate> candidatesInParties = party.getCandidates();

                if (candidatesInParties.isEmpty()) {
                    System.out.println("No candidates in " + party.getPartyName() + " party.");
                } else {
                    System.out.println("Party: " + party.getPartyName());
                    System.out.println("Description: " + party.getDescription());

                }
            }
        }
    }


    // EFFECTS: shows overview of election
    private void showElectionDetails() {
        System.out.println("Election Overview");
        System.out.println("Date and Time: " + election.getDateAndTime());
        System.out.println("Polling Places: " + election.getPollingPlaces());

        List<Candidate> candidates = candidateRepository.getListOfCandidate();
        System.out.println("Candidates:");

        for (Candidate candidate : candidates) {
            System.out.println("Name: " + candidate.getName());
            System.out.println("Party Affiliation: " + candidate.getPartyAffiliation());
            System.out.println("Office: " + candidate.getPosition());
            System.out.println("Main Policies: " + candidate.getPlatform());
            System.out.println("Winner: " + candidate.getStatus());
        }
        System.out.println("Returning to the main menu.");
    }

    // EFFECTS: saves the Candidates to file
    private void saveCandidates() {
        try {
            jsonWriter.open();
            jsonWriter.write(candidateRepository);
            jsonWriter.close();
            System.out.println("Saved " + candidateRepository.getListOfCandidate() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Candidates from file
    private void loadCandidates() {
        try {
            candidateRepository = jsonReader.read();
            System.out.println("Loaded " + candidateRepository.getListOfCandidate() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    // Constructs an Election by initialize Election, Candidates, and Parties.
    private void init() {
        election = new Election("November 10th, 9-9pm");
        candidateRepository = new CandidateRepository();

        candidateInit();
    }

    private void candidateInit() {
        Candidate candidate1 = new Candidate("Jill", "NDP", "Mayor");
        Candidate candidate2 = new Candidate("Henry", "Liberal", "Mayor");
        Candidate candidate3 = new Candidate("Rob", "NDP", "City Council");

        candidate1.addPolicies("HealthCare");
        candidate1.addPolicies("Environmental Policies");
        candidate2.addPolicies("Economic Reform");
        candidate3.addPolicies("HealthCare");

        election.addPollingPlace("City Hall");
        election.addPollingPlace("Kerrisdale Rec Centre");
        election.addPollingPlace("Kitsilano Elementary");

        candidateRepository.addCandidate(candidate1);
        candidateRepository.addCandidate(candidate2);
        candidateRepository.addCandidate(candidate3);

        Party ndp = new Party("NDP");
        ndp.addCandidate(candidate1);
        ndp.addCandidate(candidate3);
        ndp.addDescription("Left-Winged policies, Promotes Socialist Reform");
        Party liberal = new Party("Liberal");
        liberal.addCandidate(candidate2);
        liberal.addDescription("Centre policies, Focus on Economic Reform");
    }
}

