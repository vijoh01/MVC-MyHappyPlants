package view.panels.plant;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class EditPanel extends JPanel {
    private Controller controller;
    private JLabel editLabel;

    public EditPanel(Controller controller){
        this.controller = controller;
        createPanelEdit();
    }

    public void createPanelEdit(){
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(50, 340, 50, 340));

        editLabel = new JLabel("lmao");



    }
}
