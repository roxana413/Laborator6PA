package com.company;

import javax.swing.*;

import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class ConfigurationPanel extends JPanel {

    //the size, the number of sides, the stroke
    JLabel sizeLabel = new JLabel("Size: ");
    JLabel strokeLabel = new JLabel("Stroke size: ");
    JLabel NoOfSidesLabel = new JLabel ( "No Of Sides : ");
    JLabel ColorLabel = new JLabel ( "Color : ");


    JTextField size = new JFormattedTextField();
    JTextField shapesStroke = new JFormattedTextField();
    JTextField sides = new JTextField();
    JTextField color= new JTextField();

    Configurations config= new Configurations(5,1,4,Color.red);


    public ConfigurationPanel(MainFrame frame) {
        this.setBorder(BorderFactory.createTitledBorder("Toolbar"));
        init();
        this.setLayout(new GridLayout(2,4, 20, 0));
    }

    private void init() {

        shapesStroke.setText("5");
        size.setText("1");
        sides.setText("4");
        color.setText("RED");


        add(sizeLabel);
        add(strokeLabel);
        add(NoOfSidesLabel);
        add(ColorLabel);

        add(shapesStroke);
        add(size);
        add(sides);
        add(color);
    }

    public Color getConfig ()
    {
        return config.getColor();
    }


}
