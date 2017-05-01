package tictactoe;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiMainMenu extends JFrame implements ActionListener {

    private JButton modePlayerButton = new JButton("Player");
    private JButton modeAiButton = new JButton("AI");
    private JButton startButton = new JButton("Start");

    private JPanel topPanel = new JPanel(new FlowLayout());
    private JPanel bottomPanel = new JPanel(new FlowLayout());

    private Container contentPane = getContentPane();;
    private GridLayout layout = new GridLayout(1, 2);

    public GuiMainMenu(String title) {

        super(title);
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BuildUI();

    }

    private void BuildUI() {

        Color colorGray = new Color(60, 60, 60, 255);

        Border padding10 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border padding3 = BorderFactory.createEmptyBorder(3, 3, 3, 3);

        topPanel.add(modePlayerButton);
        topPanel.add(modeAiButton);

        bottomPanel.add(startButton);

        contentPane.add(topPanel);
        contentPane.add(bottomPanel);

    }

    public static void main(String[] args) {

        GuiMainMenu gui = new GuiMainMenu("Tic Tac Toe");
        gui.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
