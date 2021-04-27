package view.panels;

import controller.Controller;
import controller.Utility;
import model.Plant;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static controller.Utility.getNextWateringCountdown;

/**
 * The PlantPanel class handles the visual elements of plants, this class is made to be multiplied into multiple instances in a list for each plant.
 * @author Viktor Johansson
 */

public class PlantPanel extends JPanel {
    private Controller controllerRef;
    private Plant plant;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private Thread loadingThread = new Thread(new Loading());
    private int x = 320;

    /**
     * Constructs labels for JPanel that tells about watering status.
     * @param plant
     */
    public PlantPanel(Controller controllerRef, Plant plant) {
        this.controllerRef = controllerRef;
        this.plant = plant;
        setLayout(new BorderLayout());
        JLabel lblWaterStatus = new JLabel( Utility.centerText("Watering Status", 0));
        lblWaterStatus.setFont(new Font("Times New Roman", Font.HANGING_BASELINE + Font.BOLD, 17));

        JLabel lblWaterCountdown = new JLabel( Utility.centerText("Previous: " + plant.getLastTimeWatered(), 0)
                + "                     Next: " + getNextWateringCountdown(plant) + " hours left");
        lblWaterCountdown.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        lblWaterCountdown.setFont(new Font("Times New Roman", Font.HANGING_BASELINE + Font.BOLD, 17));

        add(lblWaterStatus, BorderLayout.CENTER);
        add(lblWaterCountdown, BorderLayout.SOUTH);
    }

    /**
     * @return returns the loading thread so that it can get accessed from other classes.
     */
    public Thread getLoadingThread() {
        return loadingThread;
    }

    /**
     * Adds a PropertyChangeListener class into PropertyChangeSupport.
     * @param listener
     */
    public void addListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Draws the loadingbar and plant image.
     * @param g used for drawing.
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.drawRoundRect(120, 45, 320, 20, 15, 15);
        graphics2D.setColor(new Color(16, 219 - x / 5, 219));

        if (x > 1)
            graphics2D.drawRoundRect(120, 45, x, 20, 15, 15);
        ImageIcon icon = plant.getImageIcon();
        if (icon == null) {
            icon = new ImageIcon("./images/plant.jpg");
        }
        graphics2D.drawImage(icon.getImage(), 05, 05, 105, 100, null);

    }


    class Loading implements Runnable {
        @Override
        public void run() {
            while (true) {
                int daysLeft = plant.getHoursBetweenWatering();
                if (x > ((daysLeft) * (plant.getHoursBetweenWatering() == 0 ? 20 : 5/*plant.getDaysWithoutWater()*/)))
                    x--;

                repaint();
                propertyChangeSupport.firePropertyChange("update", null, null);
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
