/*
    Description: Creates the GUI
    Created By: Nikko Chan
*/

import javax.swing.*;
import java.awt.*;

public class CalculatorGUI extends JFrame{
    private static final String FRAME_TITLE = "Java Calculator";
    private static final int FRAME_WIDTH = 335;
    private static final int FRAME_HEIGHT = 535;

    private JPanel numberDisplayPanel;
    private ButtonManager buttonManager;

    private JTextField expressionField;
    private JLabel postExpressionLabel; // stores the equation that was calculated as reference

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
        numberDisplayPanel = new JPanel();
        numberDisplayPanel.setLayout(new BorderLayout());

        postExpressionLabel = new JLabel("  ");
        postExpressionLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        expressionField = new JTextField();
        expressionField.setHorizontalAlignment(SwingConstants.RIGHT);

        numberDisplayPanel.add(postExpressionLabel, BorderLayout.NORTH);
        numberDisplayPanel.add(expressionField, BorderLayout.CENTER);

        add(numberDisplayPanel, BorderLayout.NORTH);

    }

    private void initButtons(){
        buttonManager = new ButtonManager();
        add(buttonManager);
    }
}
