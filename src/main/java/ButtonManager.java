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
    // check for conditions
    private static boolean pressedEqual = false;
    private static boolean pressedNumeric = false;
    private static boolean pressedNonNumeric = false;
    private static boolean error = false;

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
        updateExpressionToField(e);
    }

    private void updateExpressionToField(ActionEvent e){
        String action = e.getActionCommand();
        String expression = expressionField.getText();

        if(StringUtils.isNumeric(action)){
            pressedNumeric = true;
            boolean resetTextField = (pressedEqual
                    && pressedNumeric
                    && !pressedNonNumeric)
                    || error;

            pressedNonNumeric = false;
            pressedEqual = false;

            if(resetTextField){
                error = false;
                expressionField.setText(action);
            }else{
                expressionField.setText(expression + action);
            }

        }else{
            // either performs "=" action or makes sure that a numeric has been pressed before a nonnumeric gets added to the field
            if(pressedNumeric || action.equals("=") || !pressedNonNumeric){
                pressedNumeric = false;
                if(!action.equals("=")) pressedNonNumeric = true;

                expressionField.setText(updateExpression(action));
            }else{
                if(!expression.isEmpty()){
                    // replace the arithmetic symbol
                    if(pressedNonNumeric){
                        String expressionSubstring = expression.substring(0,
                                expression.length() - 1);
                        expressionField.setText(expressionSubstring + action);
                    }
                }
            }
        }
    }

    private String updateExpression(String action){
        String expression = expressionField.getText();

        switch(action){
            case "C":
                return "";
            case "+":
            case "-":
            case "*":
            case "/":
            case "mod":
                if(action.equals("mod")) action = "#";
                return expression + action;
            case "x^2":
            case "sqrt(x)":
            case "=":
                Expression e = createUnaryExpression(expression, action);
                return executeCalculation(e, expression, action);
        }
        return null;
    }

    private Expression createUnaryExpression(String expression, String action){
        String newExpression = expression; // default is expression

        switch(action){
            case "sqrt(x)":
                newExpression = "sqrt(" + expression + ")";
                break;
            case "x^2":
                newExpression = "sqr(" + expression + ")";
                break;
        }
        return new Expression(newExpression);
    }

    /*
        e - expression to be calculated (used for result)
        expression & action is preserved to update the postExpressionLabel
     */
    private String executeCalculation(Expression e, String expression, String action){
        if(!Double.toString(e.calculate()).equals("NaN")){
            if(action.equals("=")) {
                pressedEqual = true;
                postExpressionLabel.setText(expression + action);
            }
            else postExpressionLabel.setText(action + expression);
            return Double.toString(e.calculate());
        }else{
            error = true;
            return "Error: Incorrect Format";
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
}