package view.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import controller.Controller;


public class ConfirmationDialog implements ActionListener {

    private JButton yesBtn, noBtn;
    private Controller controller;
    private JDialog dialog;
    private JLabel message;
    private DialogType type;
    private Cursor cursor;

    public ConfirmationDialog(Controller controller){
        this.controller = controller;
         MessageDialogPanel();
    }
    public void MessageDialogPanel() {
        dialog = new JDialog();
        dialog.setSize(450, 150);
        dialog.setBackground(Color.white);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation(dim.width / 2 - 225, dim.height / 2 - 125);
        dialog.setLayout(new GridBagLayout());
        dialog.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        dialog.setUndecorated(true);
        dialog.getRootPane().setOpaque(false);
        dialog.getContentPane().setBackground(Color.white);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                dialog.setVisible(false);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,5,0);

        message = new JLabel ("");
        message.setFont(new Font("Calibri", Font.PLAIN, 17));
        message.setVisible(true);
        message.setBackground(Color.white);
        gbc.gridy = 0;
        gbc.gridwidth = 200;
        gbc.anchor = GridBagConstraints.PAGE_START;
        dialog.add(message, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        dialog.add(new JLabel(), gbc);

        gbc.fill = GridBagConstraints.WEST;

        JPanel buttons = new JPanel(new BorderLayout());
        buttons.setPreferredSize(new Dimension(150, 30));
        buttons.setBackground(Color.WHITE);
        yesBtn = new JButton("YES");
        yesBtn.setVisible(true);
        yesBtn.setFont(new Font("Arial", Font.BOLD, 12));
        yesBtn.setBackground(Color.darkGray);
        yesBtn.setForeground(Color.WHITE);
        yesBtn.addActionListener(this);
        yesBtn.setFocusPainted(false);
        yesBtn.setFocusable(false);
        yesBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttons.add(yesBtn, BorderLayout.WEST);


        noBtn = new JButton("NO");
        noBtn.setVisible(true);

        noBtn.setFont(new Font("Arial", Font.BOLD, 12));
        noBtn.setBackground(new Color(173, 193, 124));
        noBtn.setForeground(Color.DARK_GRAY);
        noBtn.addActionListener(this);
        noBtn.setFocusPainted(false);
        noBtn.setFocusable(false);
        noBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        try {
            // These coordinates are screen coordinates
            int xCoord = 850;
            int yCoord = 450;

            // Move the cursor
            Robot robot = new Robot();
            robot.mouseMove(xCoord, yCoord);

        } catch (AWTException e) {
        }
        gbc.anchor = GridBagConstraints.WEST;
        buttons.add(noBtn, BorderLayout.EAST);
        gbc.insets = new Insets(15,125,5,0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.ipadx = 50;
        gbc.weightx = 1;
        dialog.add(buttons, gbc);

    }

    public void showConfirmationDialog(DialogType dialogType) {
        dialog.setVisible(true);
        if (dialogType == DialogType.REMOVE_CONFIRMATION_DIALOG) {
            noBtn.setText("KEEP");
            yesBtn.setText("REMOVE");
        } else if (dialogType == DialogType.WATER_CONFIRMATION_DIALOG) {
            noBtn.setText("Cancel");
            yesBtn.setText("Confirm");
        }
        noBtn.getRootPane().setDefaultButton(noBtn);
        type = dialogType;
    }

    public void setConfirmationMessage(String messageTxt) {
        message.setText(messageTxt);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yesBtn) {
            if (type == DialogType.REMOVE_CONFIRMATION_DIALOG) {
                controller.buttonPushed("remove plant");
            } else if (type == DialogType.WATER_CONFIRMATION_DIALOG) {
                controller.buttonPushed("water plant");
            }
        } else if (e.getSource() == noBtn) {
            controller.buttonPushed("plantList");
        }
        dialog.setVisible(false);
    }
}