package view;

import controller.Controller;
import view.menus.MenuBar;
import view.menus.PopupMenu;
import view.panels.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame{
    private Controller controllerRef;
    private MainPanel panel;
    private view.menus.PopupMenu popupMenu;

    public MainFrame( Controller controllerRef ) {
        this.controllerRef = controllerRef;
        setupFrame();
    }


    public void setupFrame() {
        JFrame frame = new JFrame("MyHappyPlants");

        panel = new MainPanel(controllerRef);
        popupMenu = new PopupMenu();

        frame.add(panel, BorderLayout.NORTH);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setJMenuBar(new MenuBar());
        frame.add(popupMenu);
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                maybeShowPopup(e);
            }
            public void mouseReleased(MouseEvent e){
                maybeShowPopup(e);
            }
            private void maybeShowPopup(MouseEvent e){
                if(e.isPopupTrigger()){
                    popupMenu.show(e.getComponent(),e.getX(),e.getY());
                }
            }
        });


        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setCardLayout(String cardLayout) {
        panel.setCardLayout(cardLayout);
    }

    public PlantList getPlantList() {
        return panel.getPlantList();
    }




}
