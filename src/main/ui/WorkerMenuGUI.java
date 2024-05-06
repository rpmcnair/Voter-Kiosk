package ui;

import model.Candidate;
import model.CandidateRepository;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Represents the GUI of the worker menu. Where user can add candidates and save
public class WorkerMenuGUI extends JPanel {
    private CandidateRepository candidateRepository;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JsonWriter jsonWriter;

    private JTextField nameField;
    private JTextField partyField;
    private JTextField positionField;

    // EFFECTS: constructs a worker menu to add candidates to writing source file
    public WorkerMenuGUI(CandidateRepository candidateRepository, CardLayout cardLayout, JPanel cardPanel,
                         JsonWriter jsonWriter) {
        this.candidateRepository = candidateRepository;
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.jsonWriter = jsonWriter;

        nameField = new JTextField(10);
        partyField = new JTextField(10);
        positionField = new JTextField(10);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(createTitleLabel());
        add(createTextField("Name: ", nameField));
        add(createTextField("Party: ", partyField));
        add(createTextField("Position: ", positionField));
        add(createAddCandidateButton());
        add(createRemoveCandidateButton());
        add(createSaveCandidatesButton());
        add(createBackButton());
    }

    // EFFECTS: Creates Title Labels
    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Worker Menu");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return titleLabel;
    }

    // EFFECTS: Creates panel for worker menu
    private JPanel createTextField(String label, JTextField textField) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(label));
        panel.add(textField);
        return panel;
    }

    // MODIFIES: candidateRepository
    // EFFECTS: Creates "Add Candidate" button, creates a new candidate when clicked
    //          and adds to the Candidate Repository
    private JButton createAddCandidateButton() {
        JButton addCandidateButton = new JButton("Add Candidates");
        addCandidateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addCandidateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String party = partyField.getText();
                String position = positionField.getText();

                Candidate newCandidate = new Candidate(name, party, position);

                candidateRepository.addCandidate(newCandidate);
                JOptionPane.showMessageDialog(WorkerMenuGUI.this, "Candidate added!");
            }
        });
        return addCandidateButton;
    }

    // MODIFIES: candidateRepository
    //EFFECTS: Creates "Remove Candidate" Button, removes a candidate from the repository when clicked
    private JButton createRemoveCandidateButton() {
        JButton removeCandidateButton = new JButton("Remove Candidates");
        removeCandidateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeCandidateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String candidateName = JOptionPane.showInputDialog(WorkerMenuGUI.this,
                        "Enter the name of the candidate to remove:");

                if (candidateName != null && !candidateName.isEmpty()) {
                    boolean removed = candidateRepository.removeCandidate(candidateName);
                    if (removed) {
                        JOptionPane.showMessageDialog(WorkerMenuGUI.this,
                                "Candidate removed successfully!");
                    } else {
                        JOptionPane.showMessageDialog(WorkerMenuGUI.this,
                                "Candidate not found!", "Removal Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        return removeCandidateButton;
    }

    // EFFECTS: Creates "Save Candidate" button to save candidates
    private JButton createSaveCandidatesButton() {
        JButton saveCandidates = new JButton("Save Candidates");
        saveCandidates.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveCandidates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCandidatesData();
            }
        });
        return saveCandidates;
    }

    // EFFECTS: Creates the back button to return to main page
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

    // EFFECTS: go back to the main panel
    private void goBack() {
        cardLayout.show(cardPanel, "Main");
    }


    // Effects: Save Candidate data to JSON Writer
    private void saveCandidatesData() {
        try {
            jsonWriter.write(candidateRepository);
            JOptionPane.showMessageDialog(this, "Candidates saved successfully!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to save candidates. File not found.",
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
