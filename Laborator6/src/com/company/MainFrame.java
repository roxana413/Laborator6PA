package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainFrame extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int       height     = screenSize.height / 3;
    int       width      = screenSize.width / 4;

    //definim cele 3 panel-uri
    static ConfigurationPanel form;
    MyCanvas drawArea;
    ControlPanel control;
    ListPanel shapes;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == control.resetButton) {
                drawArea.clear();
            } else if (e.getSource() == control.loadButton) {
                try {
                    load();
                } catch (IOException e1) {
                    System.out.println("Fisierul nu exista sau formatul nu este bun.");
                }
            } else if (e.getSource() == control.saveButton) {
                try {
                    save();
                } catch (IOException e1) {
                    System.out.println("Destinatia nu exista sau nu ai drept de salvare.");
                }
            } else if (e.getSource() == control.exitButton) {
                System.exit(0); // stop program
                dispose(); // close window
                setVisible(false); // hide window
            }
        }
    };

    public MainFrame() {
        super("JAVA Paint");
        rootPane.setBorder(BorderFactory.createTitledBorder("Drawing panel"));
        rootPane.setPreferredSize(new Dimension(width, height));
        init();
        addComponents();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form = new ConfigurationPanel(this);
        drawArea = new MyCanvas(form.getConfig(),"Oval");
        control = new ControlPanel(this);
        shapes = new ListPanel(this);

        control.resetButton.addActionListener(actionListener);
        control.saveButton.addActionListener(actionListener);
        control.loadButton.addActionListener(actionListener);
        control.exitButton.addActionListener(actionListener);

    }

    private void addComponents(){
        add(form, BorderLayout.NORTH);
        add(drawArea, BorderLayout.CENTER);
        add(control, BorderLayout.SOUTH);
        add(shapes,BorderLayout.EAST);
    }

    public void save() throws IOException {

        JFrame parentFrame =this;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            ImageIO.write(drawArea.getImage(), "PNG", new File( fileToSave.getAbsolutePath()));
        }
    }

    public void load() throws IOException {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            drawArea.setImage(ImageIO.read(new File(selectedFile.getAbsolutePath())));
        }

        //drawArea.setImage(ImageIO.read(new File(selectedFile.getAbsolutePath())));
        repaint();
    }
}
