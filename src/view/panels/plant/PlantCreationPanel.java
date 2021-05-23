package view.panels.plant;

import controller.Controller;
import controller.Utility;
import view.dialog.ConfirmationDialog;
import view.dialog.DialogType;
import view.dialog.MessageDialog;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * The class is to be used as a graphical user interface for editing your plant.
 * @author Fanny Rosdahl Rosenglim
 */

public class PlantCreationPanel extends JPanel implements ActionListener {
    private Controller controller;
    private JButton backButton;
    private JButton saveButton;
    private JTextField nicknameTF;
    private JTextField waterTF;
    boolean creationMode = true;

    public PlantCreationPanel(Controller controller) {
        this.controller = controller;
        createPanelEdit();
    }
    /** This model is for creating the edit panel
     * @author Fanny Rosdahl Rosenglim **/
    public void createPanelEdit() {
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(0, 300, 0, 300));

        setLayout(new BorderLayout());
        JPanel editPanel = new JPanel(new GridBagLayout());

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(0, 20, 40, 8);
        Border border = BorderFactory.createLineBorder(Color.lightGray, 1, true);
        editPanel.setBorder(border);

        Border margin1 = new EmptyBorder(50, 50, 50, 50);
        editPanel.setBorder(new CompoundBorder(editPanel.getBorder(), margin1));
        editPanel.setBackground(Color.white);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();


        backButton = new JButton("BACK");
        ImageIcon backImg = new ImageIcon("./images/backarrow.png");
        Image scaledEditImg = backImg.getImage().getScaledInstance(15, 15,
                Image.SCALE_AREA_AVERAGING);
        backButton.setIcon(new ImageIcon(scaledEditImg));
        backButton.setFont(new Font("Arial", Font.BOLD, 10));
        backButton.setBackground(Color.white);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        contentPanel.add(backButton, gbc);

        saveButton = new JButton();
        saveButton.setPreferredSize(new Dimension(120, 45));
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon saveImg = new ImageIcon("./images/save-icon.png");
        Image scaledEditImage = saveImg.getImage().getScaledInstance(26, 25,
                Image.SCALE_AREA_AVERAGING);

        saveButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel iconLabel = new JLabel(new ImageIcon(scaledEditImage));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel textLabel = new JLabel("SAVE");
        saveButton.add(iconLabel);
        saveButton.add(textLabel);
        textLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        saveButton.setBackground(Color.white);
        saveButton.addActionListener(this);
        saveButton.setFont(new Font("Arial", Font.BOLD, 10));
        gbc.gridx = 3;
        gbc.gridy = 40;
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.PAGE_END;
        contentPanel.add(saveButton, gbc);

        JLabel title = new JLabel("ENTER PLANT DETAILS");
        title.setFont(new Font("Calibri light", Font.BOLD, 25));
        gbc.gridy = 3;
        gbc.gridx = 3;
        gbc.weighty = 5;
        gbc.gridheight = 1;
        contentPanel.add(title, gbc);


        JLabel nicknameLabel = new JLabel("Nickname (optional): ");
        nicknameLabel.setFont(new Font("Calibri light", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.weighty = 15;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(nicknameLabel, gbc);

        nicknameTF = new JTextField();
        nicknameTF.setPreferredSize(new Dimension(275, 30));
        nicknameTF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nicknameTF.setText("");
            }
        });
        gbc.gridx = 4;
        gbc.gridy = 13;
        gbc.weighty = 15;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(nicknameTF, gbc);

        JLabel climateLabel = new JLabel("Climate: ");
        climateLabel.setFont(new Font("Calibri light", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.weighty = 15;
        gbc.weightx = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(climateLabel, gbc);

        String[] choices = {"Inside", "Outside"};
        JComboBox<String> climateMenu = new JComboBox<>(choices);
        climateMenu.setBackground(Color.white);
        climateMenu.setPreferredSize(new Dimension(275, 30));
        gbc.gridx = 4;
        gbc.gridy = 14;
        gbc.weighty = 5;
        gbc.weightx = 2;
        gbc.weighty = 15;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(climateMenu, gbc);

        JLabel waterLabel = new JLabel("Interval Between Watering: ");
        waterLabel.setFont(new Font("Calibri light", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 15;
        gbc.weighty = 15;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(waterLabel, gbc);

        waterTF = new JTextField();
        waterTF.setPreferredSize(new Dimension(275, 30));
        waterTF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                waterTF.setText("");
            }
        });
        gbc.gridx = 4;
        gbc.gridy = 15;
        gbc.weighty = 15;
        gbc.weightx = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(waterTF, gbc);

        JLabel sizeLabel = new JLabel("Size (optional): ");
        sizeLabel.setToolTipText("This is used for our remaining days between watering estimation.");
        sizeLabel.setFont(new Font("Calibri light", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.weighty = 15;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(sizeLabel, gbc);


        DefaultListModel model = new DefaultListModel();

        Image img1 = new ImageIcon("images/flower_small.png").getImage().getScaledInstance(70,110, Image.SCALE_SMOOTH);
        Image img2 = new ImageIcon("images/flower_medium.png").getImage().getScaledInstance(80,120, Image.SCALE_SMOOTH);
        Image img3 = new ImageIcon("images/flower_big.png").getImage().getScaledInstance(90,130, Image.SCALE_SMOOTH);

        model.addElement(new ImageIcon(img1));
        model.addElement(new ImageIcon(img2));
        model.addElement(new ImageIcon(img3));

        JList list = new JList(model);
        list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        list.setToolTipText("This is used for our remaining days between watering estimation.");
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        list.setVisibleRowCount(1);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        gbc.gridx = 4;
        gbc.gridy = 16;
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        contentPanel.add(list, gbc);

        gbc.gridx = 4;
        gbc.gridy = 17;
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.gridwidth = 1;

        JTextPane tPane = new JTextPane();
        tPane.setText("Plants in smaller pots have less soil and will \ndry out faster than plants with bigger pots.");
        tPane.setEditable(false);
        StyledDocument styledDocument = tPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        styledDocument.setParagraphAttributes(0, styledDocument.getLength(), center, false);
        contentPanel.add(tPane, gbc);

        JButton linkBtn = new JButton("(source)");
        linkBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        linkBtn.addActionListener(e -> {
            try {
                URI url = new URI("https://www.thesill.com/blogs/plants-101/drink-up");
                Desktop.getDesktop().browse(url);
            } catch (URISyntaxException | IOException exception) {
            }
        });
        gbc.gridx = 4;
        gbc.gridy = 18;
        gbc.weighty = 0;
        gbc.weightx = 0;
        linkBtn.setBackground(null);
        linkBtn.setBorder(null);
        linkBtn.setForeground(new Color(26, 122, 169));
        contentPanel.add(linkBtn, gbc);

        contentPanel.setPreferredSize(new Dimension(750,450));
        g.fill = GridBagConstraints.HORIZONTAL;
        editPanel.add(contentPanel, g);

        JScrollPane scrollPane = new JScrollPane(editPanel);
        add(scrollPane);

    }

    public void setCreationMode(boolean creationMode) {
        this.creationMode = creationMode;
    }

    /**
     *
     * @author Viktor Johansson
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new ConfirmationDialog(controller)
                    .setConfirmationMessage("Are you sure you want to go back?")
                    .showConfirmationDialog(DialogType.PROCEED_BACK_CONFIRMATION_DIALOG);
        } else if (e.getSource() == saveButton) {
            if (Utility.getStringToInt(waterTF.getText()) == 0) {
                new MessageDialog("Enter valid watering interval days.\nThis is needed to remind you whenever \nyour plants needs to be watered.");
                controller.setCardLayout("plant creation page");
                return;
            }
            new Thread(() -> upsertPlantDetails()).start();
        }
    }

    public void setWaterTF(String text) {
        waterTF.setText(text);
    }

    public void setNicknameTF(String text) {
        nicknameTF.setText(text);
    }

    public boolean isCreationMode() {
        return creationMode;
    }

    public void upsertPlantDetails() {
        controller.setCardLayout("loading-screen");
        if (creationMode) {
            controller.createPlant(nicknameTF.getText(), waterTF.getText());
        } else {
            controller.editSelectedPlant(nicknameTF.getText(), waterTF.getText());
        }
        if (saveButton != null) {
            getRootPane().setDefaultButton(saveButton);
        }
    }

}