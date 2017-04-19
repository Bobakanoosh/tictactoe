package tictactoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class Gui extends JFrame implements ActionListener {

    // Images
    private Image markerNoneImage;
    private Image markerXImage;
    private Image markerOImage;
    private Image winnerImage;
    private Image catsgameImage;
    private Image turnoImage;
    private Image turnxImage;
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
        setSize(550, 800);
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
            turnoImage      = ImageIO.read(getClass().getResource("/resources/turno.png"));
            turnxImage      = ImageIO.read(getClass().getResource("/resources/turnx.png"));
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

        middlePanel.setBorder(padding10);
        middlePanel.setBackground(colorGray);

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

    public void actionPerformed(ActionEvent e) {

        if(turnCount != 9) {

            for (int i = 0; i < 9; i++) {

                if (e.getSource() == buttons[i]) {

                    turnCount++;

                    buttons[i].setEnabled(false);
                    markers[i].setEnabled(false);
                    markers[i].setLocation(i);

                    if (turnCount % 2 == 0)
                        markers[i].setType("O");
                    else
                        markers[i].setType("X");

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

        //if (turnCount % 2 == 0)
        //    turnLabel.setIcon(new ImageIcon(turnxImage));
        //else
        //    turnLabel.setIcon(new ImageIcon(turnoImage));

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

                winnerLabel.setVisible(true);
                winnerLabel.setIcon(new ImageIcon(winnerImage));
                //turnLabel.setVisible(false);

                DisableMarkers();
                break;

            } else if(countX == 3) {

                winnerLabel.setVisible(true);
                winnerLabel.setIcon(new ImageIcon(winnerImage));
                //turnLabel.setVisible(false);

                DisableMarkers();
                break;

            } else if(turnCount == 9){

                winnerLabel.setVisible(true);
                winnerLabel.setIcon(new ImageIcon(catsgameImage));
                //turnLabel.setVisible(false);

                DisableMarkers();
                break;

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

        turnCount = 0;

        for(int i = 0; i < 9; i++) {

            markers[i].setEnabled(true);
            markers[i].setType("NONE");
            markers[i].setLocation(0);

            buttons[i].setIcon(new ImageIcon(markerNoneImage));
            buttons[i].setEnabled(true);
            buttons[i].setVisible(true);

            winnerLabel.setVisible(false);
            //turnLabel.setIcon(new ImageIcon(turnxImage));

        }

    }

}