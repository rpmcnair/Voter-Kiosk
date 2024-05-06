package ui;

import model.CandidateRepository;
import model.Election;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// Represents the GUI of the main menu. This is where user can have the option to flip between menus
public class ElectionAppFrameGUI extends JFrame implements WindowListener {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Election election;
    private CandidateRepository candidateRepository;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/mydata.json";

    Color color = Color.WHITE;

    // Create an Election App Main Page
    public ElectionAppFrameGUI() {
        super("Election Application");
        initializeFrame();
        initializeComponents();

        add(cardPanel);
        addWindowListener(this);

        pack();
        setLocationRelativeTo(null);
    }

    // EFFECT: initialize the frame
    private void initializeFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setResizable(false);
    }

    // EFFECT: add panels to the main frame
    private void initializeComponents() {
        election = new Election("2023");
        candidateRepository = new CandidateRepository();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel mainPanel = createMainPanel();
        VoterMenuGUI voterMenuPanel =
                new VoterMenuGUI(election, candidateRepository, cardLayout, cardPanel, jsonReader);
        WorkerMenuGUI workerMenuPanel = new WorkerMenuGUI(candidateRepository, cardLayout, cardPanel, jsonWriter);

        cardPanel.add(mainPanel, "Main");
        cardPanel.add(voterMenuPanel, "VoterMenu");
        cardPanel.add(workerMenuPanel, "WorkerMenu");
    }

    // EFFECT: Create the main panel
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.setBackground(color);

        mainPanel.add(createTitleLabels());
        mainPanel.add(createButtonPanel());
        mainPanel.add(createImageLabel());

        return mainPanel;
    }

    // EFFECT: create the titles for the main panel
    private JPanel createTitleLabels() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.setBackground(color);

        JLabel title1 = new JLabel("Election Information Kiosk", SwingConstants.CENTER);
        title1.setAlignmentX(Component.CENTER_ALIGNMENT);
        title1.setFont(new Font("Serif", Font.BOLD, 36));

        JLabel title2 = new JLabel("Welcome to the 2023 Vancouver Municipal Elections",
                SwingConstants.CENTER);
        title2.setAlignmentX(Component.CENTER_ALIGNMENT);
        title2.setFont(new Font("Serif", Font.BOLD, 25));

        titlePanel.add(title1);
        titlePanel.add(title2);

        return titlePanel;
    }
    //mainPanel.add(Box.createVerticalStrut(30));

    // EFFECT: Create the buttons for the main page
    private JPanel createButtonPanel() {

        JPanel promptPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        promptPanel.setBackground(color);

        JLabel prompt = new JLabel("Are you a...", SwingConstants.LEFT);
        prompt.setFont(new Font("Serif", NORMAL, 20));
        promptPanel.add(prompt);

        JButton voterButton = new JButton("Voter");
        voterButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        voterButton.addActionListener(e -> cardLayout.show(cardPanel, "VoterMenu"));
        promptPanel.add(voterButton);

        JLabel question = new JLabel("or an");
        question.setFont(new Font("Serif", NORMAL, 20));
        promptPanel.add(question);

        JButton workerButton = new JButton("Election Worker");
        workerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        workerButton.addActionListener(e -> cardLayout.show(cardPanel, "WorkerMenu"));
        promptPanel.add(workerButton);


        return promptPanel;
    }

    // Effects: Insert Image on main page
    private JLabel createImageLabel() {
        // Method to create and return the image label
        ImageIcon originalIcon =
                new ImageIcon(getClass().getResource("/resources/BC-government-logo-NEW.jpg"));
        Image image = originalIcon.getImage();
        Image scaledImage = image.getScaledInstance(600, 500, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        return imageLabel;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        printEventLog();
        System.exit(0);
    }

    private void printEventLog() {
        EventLog eventLog = EventLog.getInstance();
        for (Event event : eventLog) {
            System.out.println(event.toString());
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
