package ui;

import model.Candidate;
import model.CandidateRepository;
import model.Election;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


// Represents the GUI of the worker menu. Where user can view candidates and load saved candidates
public class VoterMenuGUI extends JPanel {
    private Election election;
    private CandidateRepository candidateRepository;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JsonReader jsonReader;

    // EFFECTS: constructs voter menu to load candidates and read from source file
    public VoterMenuGUI(Election election, CandidateRepository candidateRepository, CardLayout cardLayout,
                        JPanel cardPanel, JsonReader jsonReader) {
        this.election = election;
        this.candidateRepository = candidateRepository;
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.jsonReader = jsonReader;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(createTitleLabel());
        add(createViewCandidateButton());
        add(createLoadCandidatesButton());
        add(createBackButton());
    }

    //EFFECTS: Create the title of this UI page
    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Voter Menu");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return titleLabel;
    }

    //EFFECTS: Create the view candidate button to view added candidates
    private JButton createViewCandidateButton() {
        JButton viewCandidatesButton = new JButton("View Candidates");
        viewCandidatesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewCandidatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCandidates();
            }
        });
        return viewCandidatesButton;
    }

    //EFFECTS: Create the button to load candidates
    private JButton createLoadCandidatesButton() {
        JButton loadCandidates = new JButton("Load Candidates");
        loadCandidates.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadCandidates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCandidatesData();
            }
        });
        return loadCandidates;
    }

    //EFFECTS: Create the back button to return to main page
    private JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });
        return backButton;
    }


    // EFFECTS: return to the main page when the button is pressed
    private void goBack() {
        cardLayout.show(cardPanel, "Main");
    }


    // EFFECTS: Creates a text pane which shows the description of candidates loaded
    private void showCandidates() {
        JTextArea candidateInfoArea = new JTextArea(10, 30);
        candidateInfoArea.setEditable(false);

        if (candidateRepository.getListOfCandidate().isEmpty()) {
            candidateInfoArea.append("No candidates available.");
        } else {
            for (Candidate candidate : candidateRepository.getListOfCandidate()) {
                candidateInfoArea.append("NAME: " + candidate.getName()
                        + "\n"
                        + "PARTY: "
                        + candidate.getPartyAffiliation()
                        + "\n"
                        + "POSITION: " + candidate.getPosition()
                        + "\n" + "\n");
            }
        }
        JScrollPane scrollPane = new JScrollPane(candidateInfoArea);
        JOptionPane.showMessageDialog(this, scrollPane, "Candidates",
                    JOptionPane.INFORMATION_MESSAGE);
    }

    // EFFECTS: Loads list of candidates from jsonreader into the GUI
    private void loadCandidatesData() {
        try {
            CandidateRepository loadedCandidates = jsonReader.read();
            candidateRepository.setAllCandidates(loadedCandidates.getListOfCandidate());
            JOptionPane.showMessageDialog(this, "Candidates loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading candidates.", "Load Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
