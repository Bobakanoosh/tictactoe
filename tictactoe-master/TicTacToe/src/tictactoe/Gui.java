package tictactoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class Gui extends JFrame implements ActionListener {

    Image markerNoneImage;
    Image markerXImage;
    Image markerOImage;

    // Text Areas
    private JTextField statusTextField = new JTextField(20);
    private JTextField turnTextField = new JTextField(20);

    // Buttons
    private JButton buttons[];
    private Marker markers[];

    private JButton resetButton = new JButton("Reset");

    // Panels
    private JPanel topPanel = new JPanel(new FlowLayout());
    private JPanel bottomPanel = new JPanel(new GridLayout(3, 3));

    public int turnCount = 0;
    public int cases[][] = {{0, 4, 8}, {0, 3, 6}, {0, 1, 2}, {1, 4, 7}, {2, 5, 8}, {2, 4, 6}, {3, 4, 5}, {6, 7, 8}};

    public Gui(String title) {

        // Initialization
        super(title);
        setSize(320, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        buttons = new JButton[10];
        markers = new Marker[10];

        // Build the UI
        BuildUI();

    }

    private void BuildUI() {

        Color colorGray = new Color(60, 60, 60);
        Font sansFont = new Font("Sans Serif", Font.PLAIN, 75);

        // Padding for the panels
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        // Registering the images
        try {

            markerNoneImage = ImageIO.read(getClass().getResource("/resources/markerNone.png"));
            markerXImage = ImageIO.read(getClass().getResource("/resources/markerO.png"));
            markerOImage = ImageIO.read(getClass().getResource("/resources/markerX.png"));

        } catch (Exception ex) {

            System.out.println(ex);

        }

        // Creating each button
        for(int i = 0; i < 9; i++) {

            markers[i] = new Marker();
            buttons[i] = new JButton();
            buttons[i].addActionListener(this);
            buttons[i].setBorder(null);
            buttons[i].setIcon(new ImageIcon(markerNoneImage));
            buttons[i].setBackground(colorGray);
            buttons[i].setPreferredSize(new Dimension(50, 50));

            bottomPanel.add(buttons[i]);

        }

        bottomPanel.setBorder(padding);
        bottomPanel.setBackground(colorGray);

        // Top Panel Attributes
        turnTextField.setText(" Turn: O ");
        turnTextField.setEditable(false);
        turnTextField.setBorder(null);
        turnTextField.setBackground(colorGray);
        turnTextField.setForeground(Color.WHITE);

        statusTextField.setEditable(false);
        statusTextField.setBorder(null);
        statusTextField.setBackground(colorGray);
        statusTextField.setForeground(Color.WHITE);

        resetButton.addActionListener(this);
        resetButton.setBackground(colorGray);
        resetButton.setForeground(Color.WHITE);

        // Adding to Top Panel
        topPanel.add(turnTextField);
        topPanel.add(statusTextField);
        topPanel.add(resetButton);
        topPanel.setBorder(padding);
        topPanel.setMaximumSize(new Dimension(320, 100));
        topPanel.setBackground(new Color(60, 60, 60));

        // Add the panels to the frame
        add(topPanel);
        add(bottomPanel);

    }

    public static void main(String[] args) {

        // Initialize a new GUI and set it's visibility to true
        Gui gui = new Gui("Tic Tac Toe");
        gui.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if(turnCount != 9) {

            for (int i = 0; i < 9; i++) {

                if (e.getSource() == buttons[i]) {

                    turnCount++;

                    buttons[i].setEnabled(false);
                    markers[i].setEnabled(false);
                    markers[i].setLocation(i);

                    if (turnCount % 2 == 0)
                        markers[i].setType("X");
                    else
                        markers[i].setType("O");

                    break;

                }
            }
        }

        if(e.getSource() == resetButton) {

            ResetMarkers();

        }

        UpdateMarkers();

    }

    public void UpdateMarkers() {

        for(int i = 0; i < 9; i++) {

            if(markers[i].getType().equals("X")) {

                buttons[i].setIcon(new ImageIcon(markerOImage));

            } else if(markers[i].getType().equals("O")) {

                buttons[i].setIcon(new ImageIcon(markerXImage));

            } else  {

                buttons[i].setIcon(new ImageIcon(markerNoneImage));

            }

        }

        if (turnCount % 2 == 0)
            turnTextField.setText(" Turn: O ");
        else
            turnTextField.setText(" Turn: X ");

        CheckCases();

    }

    public void CheckCases() {

        for(int i = 0; i < 8; i++) {

            int countO = 0;
            int countX = 0;

            for(int j = 0; j < 3; j++) {

                if(markers[cases[i][j]].getType().equals("X")) {

                    countX++;

                } if(markers[cases[i][j]].getType().equals("O")) {

                    countO++;

                }


            }

            if(countO == 3) {

                statusTextField.setText("Winner is O!");
                DisableMarkers();
                break;

            } else if(countX == 3) {

                statusTextField.setText("Winner is X!");
                DisableMarkers();
                break;

            } else if(turnCount == 9){

                statusTextField.setText("Cats game!");
                break;

            } else {

                statusTextField.setText("");

            }

        }

    }

    public void DisableMarkers() {

        for(int i = 0; i < 9; i++) {

            markers[i].setEnabled(false);
            buttons[i].setEnabled(false);

        }

    }

    public void ResetMarkers() {

        for(int i = 0; i < 9; i++) {

            markers[i].setEnabled(true);
            markers[i].setType("NONE");
            markers[i].setLocation(0);

            buttons[i].setIcon(new ImageIcon(markerNoneImage));
            buttons[i].setEnabled(true);

        }

    }

}
