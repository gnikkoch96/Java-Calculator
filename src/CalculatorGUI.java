/*
    Description: Creates the GUI
    Created By: Nikko Chan
*/

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CalculatorGUI extends JFrame implements ActionListener {
    private static final String FRAME_TITLE = "Java Calculator";
    private static final int FRAME_WIDTH = 335;
    private static final int FRAME_HEIGHT = 535;

    private static ArithmeticManager arithmeticM;
    private JPanel numberDisplayPanel, buttonPanel;
    private HashMap<Character, JButton> numberButtons, arithmeticButtons;

    public CalculatorGUI(){
        super(FRAME_TITLE);

        // JFrame Properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setLocationRelativeTo(null);
        setResizable(false);

        addComponents();
    }

    private void addComponents(){
        initDisplayFields();
        initButtons();
    }

    private void initDisplayFields(){

    }

    private void initButtons(){
        numberButtons = new HashMap<>();
        arithmeticButtons = new HashMap<>();

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());

        for(int i = 0; i < 10; i++){
            JButton button = new JButton(Integer.toString(i));
            button.addActionListener(this);
            numberButtons.put((char) i, button);
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){

        }
    }
}
