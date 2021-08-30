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
    // to prevent hard coding the action type
    private static final String ARITHMETIC = "arith";
    private static final String UNARY = "unary";
    private static final String EQUAL = "equal";

    // check for conditions
    private static boolean pressedEqual = false;
    private static boolean pressedNumeric = false;
    private static boolean pressedArithmetic = false;
    private static boolean pressedUnaryFunction = false;
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
        updateExpressionToField(e.getActionCommand());
    }

    private void updateExpressionToField(String action){
        String expression = expressionField.getText();

        boolean isNumericOrConstant = StringUtils.isNumeric(action)
                || action.equals("pi")
                || action.equals("e");

        boolean isArithmetic = action.equals("+")
                || action.equals("-")
                || action.equals("*")
                || action.equals("/")
                || action.equals("#")
                || action.equals("x^y");

        if(isNumericOrConstant){
            boolean resetTextField = ((pressedEqual || pressedUnaryFunction)
                    && !pressedArithmetic)
                    || error;

            pressedArithmetic = false;
            pressedEqual = false;
            pressedUnaryFunction = false;
            pressedNumeric = true;

            if(resetTextField){
                error = false;
                expressionField.setText(action);
            }else{
                expressionField.setText(expression + action);
            }

        }else if(isArithmetic){
            // either performs "=" action or makes sure that a numeric has been pressed before a nonnumeric gets added to the field
            // test for !pressedArithmetic to check if we need to add the arith symbol or replace it
            if(pressedNumeric && !pressedArithmetic){
                pressedNumeric = false;
                pressedArithmetic = true;
                expressionField.setText(updateExpression(action, ARITHMETIC));
            }else{
                if(!expression.isEmpty()){
                    // replace the arithmetic symbol
                    if(pressedArithmetic){
                        String expressionSubstring = expression.substring(0,
                                expression.length() - 1);
                        expressionField.setText(expressionSubstring + action);
                    }
                }
            }
        }else{ // it is a unary expression
            if(action.equals("=")) {
                pressedEqual = true;
                expressionField.setText(updateExpression(action, EQUAL));
            }
            else {
                pressedUnaryFunction = true;
                expressionField.setText(updateExpression(action, UNARY));
            }
        }
    }

    /*
        actionType - stores in "arith", "unary", or "equal"
     */
    private String updateExpression(String action, String actionType){
        String expression = expressionField.getText();

        if(action.equals("C")){
            return "";
        }else if(action.equals("del")){
            if(expression.length() > 0) return expression.substring(0, expression.length() - 1);
            else return expression;
        } else if(actionType.equals(ARITHMETIC) || action.equals("(") || action.equals(")") || action.equals(".")){
            if(action.equals("x^y")) action = "^"; // update to correct symbol on the textfield
            return expression + action;
        }else{ // actionType = unary || equal
            Expression e = createUnaryExpression(expression, action);
            return executeCalculation(e, expression, action);
        }
    }

    private Expression createUnaryExpression(String expression, String action){
        String newExpression = null;

        switch(action){
            case "sqrt(x)":
                newExpression = "sqrt(";
                break;
            case "x^2":
                newExpression = "("; // n: I'm thinking this could be better
                break;
            case "log":
                newExpression = "log2(";
                break;
            case "exp(x)":
                newExpression = "exp(";
                break;
            case "|x|":
                newExpression = "abs(";
                break;
            case "n!":
                newExpression = expression + "!";
                break;
            case "ln":
                newExpression = "ln(";
                break;
            case "1/x":
                newExpression = "1/(";
                break;
            case "10^x":
                newExpression = "10^(";
                break;
        }

        if(newExpression == null){ // pressed equal, so no change is required
            newExpression = expression;
        }else{
            if(!action.equals("n!")){
                newExpression += expression + ")";
                if(action.equals("x^2")){
                    newExpression += "^2";
                }
            }
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
                postExpressionLabel.setText(expression + action);
            }
            else {
                postExpressionLabel.setText(e.getExpressionString()); // for unary functions
            }
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
                return "#";
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