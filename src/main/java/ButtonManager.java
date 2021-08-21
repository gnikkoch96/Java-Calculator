/*
    Description: Handles the creation of the Button Panel
    Created By: Nikko Chan
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ButtonManager extends JPanel implements ActionListener {
    private HashMap<Character, JButton> numberButtons;

    public ButtonManager(){
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
                return "X";
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
