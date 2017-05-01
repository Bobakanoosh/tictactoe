package tictactoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class Gui extends JFrame implements ActionListener {

    // Points
    Point startPoint;
    Point endPoint;

    // Images
    private Image markerNoneImage;
    private Image markerXImage;
    private Image markerOImage;
    private Image winnerImage;
    private Image catsgameImage;
    private Image resetImage;

    // Buttons
    private JButton resetButton = new JButton();
    private JButton buttons[];
    private Marker markers[];

    // Labels
    private JLabel winnerLabel = new JLabel();
    //private JLabel turnLabel = new JLabel();

    // Panels
    private JPanel topPanel = new JPanel(new FlowLayout());
    private JPanel middlePanel = new JPanel(new GridLayout(3, 3));
    private JPanel bottomPanel = new JPanel(new FlowLayout());

    private Container contentPane = getContentPane();;
    private FlowLayout layout = new FlowLayout();

    public int turnCount = 0;
    public int cases[][] = {{0, 4, 8}, {0, 3, 6}, {0, 1, 2}, {1, 4, 7}, {2, 5, 8}, {2, 4, 6}, {3, 4, 5}, {6, 7, 8}};

    public Gui(String title) {

        // Initialization
        super(title);
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane.setLayout(layout);
        contentPane.setBackground(new Color(60, 60, 60));

        buttons = new JButton[10];
        markers = new Marker[10];

        // Build the UI
        BuildUI();

    }

    private void BuildUI() {

        Color colorGray = new Color(60, 60, 60, 255);

        // Padding for the panels
        Border padding10 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border padding3 = BorderFactory.createEmptyBorder(3, 3, 3, 3);

        // Registering the images
        try {

            markerNoneImage = ImageIO.read(getClass().getResource("/resources/markerNone.png"));
            markerXImage    = ImageIO.read(getClass().getResource("/resources/markerO.png"));
            markerOImage    = ImageIO.read(getClass().getResource("/resources/markerX.png"));
            winnerImage     = ImageIO.read(getClass().getResource("/resources/winner.png"));
            catsgameImage   = ImageIO.read(getClass().getResource("/resources/catsgame.png"));
            resetImage      = ImageIO.read(getClass().getResource("/resources/reset.png"));

        } catch (Exception ex) {

            System.out.println(ex);

        }

        // Top Panel Attributes
        resetButton.addActionListener(this);
        resetButton.setBackground(colorGray);
        resetButton.setBorder(padding10);
        resetButton.setIcon(new ImageIcon(resetImage));

        //turnLabel.setIcon(new ImageIcon(turnxImage));
        //turnLabel.setBorder(padding10);

        // Adding to Top Panel
        //topPanel.add(turnLabel);
        topPanel.add(resetButton);
        topPanel.setBorder(padding10);
        topPanel.setPreferredSize(new Dimension(550, 100));
        topPanel.setBackground(new Color(60, 60, 60));

        // Creating each button
        for(int i = 0; i < 9; i++) {

            markers[i] = new Marker();
            buttons[i] = new JButton();
            buttons[i].addActionListener(this);
            buttons[i].setBorder(padding3);
            buttons[i].setIcon(new ImageIcon(markerNoneImage));
            buttons[i].setBackground(colorGray);
            middlePanel.add(buttons[i]);

        }

        // Creating the middle panel
        middlePanel.setBorder(padding10);
        middlePanel.setBackground(colorGray);
        middlePanel.setOpaque(false);

        // Creating the bottom panel
        winnerLabel.setVisible(false);
        bottomPanel.add(winnerLabel);
        bottomPanel.setBackground(new Color(60, 60, 60));
        bottomPanel.setPreferredSize(new Dimension(550, 100));

        // Add the panels to the frame
        contentPane.add(topPanel);
        contentPane.add(middlePanel);
        contentPane.add(bottomPanel);

    }

    public static void main(String[] args) {

        // Initialize a new GUI and set it's visibility to true
        Gui gui = new Gui("Tic Tac Toe");
        gui.setVisible(true);

    }

    // Managing button clicks
    public void actionPerformed(ActionEvent e) {

        // Not completely necessary, but put it in anyways.
        if(turnCount != 9) {

            // Loop each button
            for (int i = 0; i < 9; i++) {

                // If the button is the one they clicked
                if (e.getSource() == buttons[i]) {

                    turnCount++;

                    // Disable that button in the gui and disable it's object. Set it's location to that spot.
                    buttons[i].setEnabled(false);
                    markers[i].setEnabled(false);
                    markers[i].setLocation(i);

                    // Set the type of that marker based on what turn it currently is.
                    if (turnCount % 2 == 0)
                        markers[i].setType("O");
                    else
                        markers[i].setType("X");

                    // If we already found the button they pressed, break out.
                    break;

                }
            }
        }

        // If they pressed the rest button.
        if(e.getSource() == resetButton) {

            ResetMarkers();

        }

        // Update the markers after every button press.
        UpdateMarkers();

    }

    public void paint(Graphics g) {
        super.paint(g);

        if(winnerLabel.isVisible()) {

            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(10));
            g2.setColor(new Color(255, 0, 0));

            g2.draw(new Line2D.Float((float) startPoint.getX(), (float) startPoint.getY(), (float) endPoint.getX(), (float) endPoint.getY()));

            // Draw the line here. MAKE SURE TO CALL repaint() IF YOU NEED TO UPDATE AT ALL.

        }
    }

    public void UpdateMarkers() {

        // Loop the markers
        for(int i = 0; i < 9; i++) {

            // If that specific marker's type is X
            if(markers[i].getType().equals("X")) {

                buttons[i].setIcon(new ImageIcon(markerOImage));
                buttons[i].setDisabledIcon(new ImageIcon(markerOImage));

            } else if(markers[i].getType().equals("O")) {

                buttons[i].setIcon(new ImageIcon(markerXImage));
                buttons[i].setDisabledIcon(new ImageIcon(markerXImage));

            } else  {

                buttons[i].setIcon(new ImageIcon(markerNoneImage));

            }

        }

        CheckCases();

    }

    public void CheckCases() {

        // Loop all the markers
        for(int i = 0; i < 8; i++) {

            // Reset count for each marker we're checking
            int countO = 0;
            int countX = 0;

            // Loop each case
            for(int j = 0; j < 3; j++) {

                // Check if that iteration in the specific case is X, if it is, increment countx.
                if(markers[cases[i][j]].getType().equals("X")) {

                    countX++;

                    // Check if that iteration in the specific case is O, if it is, increment counto.
                } else if(markers[cases[i][j]].getType().equals("O")) {

                    countO++;

                }

            }

            // If we found a winner
            if(countO == 3 || countX == 3) {

                startPoint = buttons[cases[i][0]].getLocation();
                endPoint = buttons[cases[i][2]].getLocation();

                winnerLabel.setVisible(true);
                winnerLabel.setIcon(new ImageIcon(winnerImage));

                DisableMarkers();
                repaint();

                // Stop the loop so we don't check more than we need to
                break;

                // Or if it's a cat's game
            }  else if(turnCount == 9){

                winnerLabel.setVisible(true);
                winnerLabel.setIcon(new ImageIcon(catsgameImage));
                //turnLabel.setVisible(false);

                DisableMarkers();
                repaint();
                break;

            }

        }

    }

    public void DisableMarkers() {

        // Loop all the markers and buttons and disable them
        for(int i = 0; i < 9; i++) {

            markers[i].setEnabled(false);
            buttons[i].setEnabled(false);

        }

    }

    public void ResetMarkers() {

        // Reset turncount
        turnCount = 0;

        // Loop the markers and buttons and set them to be defaults.
        for(int i = 0; i < 9; i++) {

            markers[i].setEnabled(true);
            markers[i].setType("NONE");
            markers[i].setLocation(0);

            buttons[i].setIcon(new ImageIcon(markerNoneImage));
            buttons[i].setEnabled(true);
            buttons[i].setVisible(true);

            winnerLabel.setVisible(false);

        }

    }

}