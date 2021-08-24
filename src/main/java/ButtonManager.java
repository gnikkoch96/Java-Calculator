/*
    Description: Handles the creation of the Button Panel
    Created By: Nikko Chan
*/

import org.apache.commons.lang3.StringUtils;
import org.mariuszgromada.math.mxparser.Expression;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonManager extends JPanel implements ActionListener {
    private static boolean pressedEqual = false; // used to reset the expression field for the next input
    private static boolean pressedNumeric = false;

    private JTextField expressionField;
    private JLabel postExpressionLabel;

    public ButtonManager(CalculatorGUI calculator){
        expressionField = calculator.getExpressionField();
        postExpressionLabel = calculator.getPostExpressionLabel();
        setLayout(new GridLayout(0,5));
        addButtons();
    }

    private void addButtons(){
        for(int i = 0; i < 35; i++){
            JButton button = new JButton(addCaptionToButton(i));
            button.addActionListener(this);
            add(button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        String expression = expressionField.getText();

        // easier way to check if the button pressed was numeric
        if(StringUtils.isNumeric(action)){
            boolean resetTextField = pressedEqual && pressedNumeric;

            pressedNumeric = true;
            if(resetTextField){
                pressedEqual = false;
                expressionField.setText(action);
            }else{
                expressionField.setText(expression + action);
            }
        }else{
            if(pressedNumeric || action.equals("=")){
                pressedEqual = false;
                pressedNumeric = false;
                expressionField.setText(checkAction(action));
            }else{ // change the arithmetic/expression
                if(!expression.isEmpty()){
                    String expressionSubstring = expression.substring(0, expression.length() - 1);
                    expressionField.setText(expressionSubstring + action);
                }
            }
        }
    }

    // returns the appropriate caption for the button based on the index provided
    private String addCaptionToButton(int index){
        switch(index){
            case 0:
                return "2^nd";
            case 1:
                return "pi";
            case 2:
                return "e";
            case 3:
                return "C";
            case 4:
                return "del";
            case 5:
                return "x^2";
            case 6:
                return "1/x";
            case 7:
                return "|x|";
            case 8:
                return "exp";
            case 9:
                return "mod";
            case 10:
                return "sqrt(x)";
            case 11:
                return "(";
            case 12:
                return ")";
            case 13:
                return "n!";
            case 14:
                return "/";
            case 15:
                return "x^y";
            case 16:
                return "7";
            case 17:
                return "8";
            case 18:
                return "9";
            case 19:
                return "*";
            case 20:
                return "10^x";
            case 21:
                return "4";
            case 22:
                return "5";
            case 23:
                return "6";
            case 24:
                return "-";
            case 25:
                return "log";
            case 26:
                return "1";
            case 27:
                return "2";
            case 28:
                return "3";
            case 29:
                return "+";
            case 30:
                return "ln";
            case 31:
                return "+/-";
            case 32:
                return "0";
            case 33:
                return ".";
            case 34:
                return "=";
        }

        return null; // means there is an error
    }

    private String checkAction(String action){
        String expression = expressionField.getText();

        switch(action){
            case "C":
                return "";
            case "x^2":
            case "sqrt(x)":
                Expression e = new Expression(expression);

                if(action.equals("sqrt(x)")){
                    e.setExpressionString("sqrt(" + e.calculate() +")");
                }

                return Double.toString(e.calculate());
            case "+":
            case "-":
            case "*":
            case "/":
                return expression + action;
//            case "=":
//                //test if the expression can be evaluated first
//                Expression e = new Expression(expression);
//
//                //this should be true only if the calculation was possible
//                pressedEqual = true;
//                if(!Double.toString(e.calculate()).equals("NaN")){
//                    postExpressionLabel.setText(expression + action);
//                    return Double.toString(e.calculate());
//                }else{
//                    return "Error: Incorrect Format";
//                }
        }
        return null;
    }
}