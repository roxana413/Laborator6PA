package com.company;

import javax.swing.*;


import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ListPanel extends JPanel {
    private final MainFrame mainFrame;

    String shapes [] = {"Rectangle", "Polygon", "Line"};


    JList list = new JList(shapes);

    MouseListener mouseListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent mouseEvent) {
            JList theList = (JList) mouseEvent.getSource();
            if (mouseEvent.getClickCount() == 2) {
                int index = theList.locationToIndex(mouseEvent.getPoint());
                if (index >= 0) {
                    Object o = theList.getModel().getElementAt(index);
                    System.out.println("Double-clicked on: " + o.toString());
                    if(o.toString().compareTo("Rectangle") == 0)
                    {
                       MyCanvas c = new MyCanvas (Color.red, "Rectangle");
                       System.out.println("Am ajuns aici ");
                    }
                    else
                        if(o.toString().compareTo("Line")==0)
                        {
                            MyCanvas c = new MyCanvas (Color.red, "Line");
                        }
                        else
                        {
                            MyCanvas c = new MyCanvas (Color.red, "Polygon");
                        }
                }
            }
        }
    };





    public ListPanel(MainFrame frame) {
        mainFrame=frame;
        this.setBorder(BorderFactory.createTitledBorder("Available shapes"));
        init();
    }

    private void init() {

        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);

        /*JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80)); */
        list.addMouseListener(mouseListener);
        add(list);
        //add(listScroller);

    }

}
