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
    private JTextArea statusTextArea = new JTextArea();

    // Buttons
    private JButton buttons[];
    private Marker markers[];
    private JButton button1 = new JButton("1");

    // Panels
    private JPanel topPanel = new JPanel(new FlowLayout());
    private JPanel bottomPanel = new JPanel(new GridLayout(3, 3));

    public int turnCount = 0;

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

        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        try {

            markerNoneImage = ImageIO.read(getClass().getResource("/resources/markerNone.png"));
            markerXImage = ImageIO.read(getClass().getResource("/resources/markerO.png"));
            markerOImage = ImageIO.read(getClass().getResource("/resources/markerX.png"));

        } catch (Exception ex) {

            System.out.println(ex);

        }

        for(int i = 0; i < 9; i++) {

            markers[i] = new Marker();
            buttons[i] = new JButton();
            buttons[i].addActionListener(this);
            buttons[i].setBorder(null);
            buttons[i].setIcon(new ImageIcon(markerNoneImage));
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setPreferredSize(new Dimension(50, 50));

            bottomPanel.add(buttons[i]);

        }

        topPanel.setBorder(padding);
        topPanel.setMaximumSize(new Dimension(320, 100));

        bottomPanel.setBorder(padding);
        topPanel.setMaximumSize(new Dimension(320, 400));

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

        UpdateMarkers();

    }

    public void UpdateMarkers() {

        for(int i = 0; i < 9; i++) {

            if(markers[i].getType().equals("X")) {

                buttons[i].setIcon(new ImageIcon(markerXImage));

            } else if(markers[i].getType().equals("O")) {

                buttons[i].setIcon(new ImageIcon(markerOImage));

            } else  {

                buttons[i].setIcon(new ImageIcon(markerNoneImage));

            }

        }

        CheckCases();

    }

    public void CheckCases() {

        if(true) {

            //ResetMarkers();

        } else {



        }

    }

    public void ResetMarkers() {

        for(int i = 0; i < 9; i++) {

            markers[i].setEnabled(true);
            markers[i].setType("NONE");
            markers[i].setLocation(0);

            buttons[i].setIcon(new ImageIcon(markerOImage));
            buttons[i].setEnabled(true);

        }

    }

}
