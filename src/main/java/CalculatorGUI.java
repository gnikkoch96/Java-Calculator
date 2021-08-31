/*
    Description: Creates the GUI
    Created By: Nikko Chan
*/

import javax.swing.*;
import java.awt.*;

public class CalculatorGUI extends JFrame{
    private static final String FRAME_TITLE = "Java Calculator";
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 350;

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
        numberDisplayPanel.setLayout(new GridLayout(0,1));

        postExpressionLabel = new JLabel("");
        postExpressionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        postExpressionLabel.setFont(new Font("Dialog", Font.BOLD, 18));

        expressionField = new JTextField();
        expressionField.setHorizontalAlignment(SwingConstants.RIGHT);
        expressionField.setFont(new Font("Dialog", Font.PLAIN, 20));

        numberDisplayPanel.add(postExpressionLabel);
        numberDisplayPanel.add(expressionField);

        add(numberDisplayPanel, BorderLayout.NORTH);
    }

    private void initButtons(){
        buttonManager = new ButtonManager(this);
        add(buttonManager, BorderLayout.CENTER);
    }

    public JTextField getExpressionField(){
        return expressionField;
    }

    public JLabel getPostExpressionLabel(){
        return postExpressionLabel;
    }
}
