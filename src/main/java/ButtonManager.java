/*
    Description: Handles the creation of the Button Panel
    Created By: Nikko Chan
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ButtonManager extends JPanel implements ActionListener {
    private HashMap<Character, JButton> numberButtons;

    public ButtonManager(){
        setLayout(new GridLayout(7,5));
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
