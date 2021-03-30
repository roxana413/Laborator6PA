package com.company;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class MyCanvas extends JPanel implements MouseMotionListener {
    private Integer size;
    private Integer noOfSlides;
    private Integer stroke;
    private Color   color;
    private int x, y, radius;
    private BufferedImage image;
    private Graphics2D graphics; //ne folosim de un graphics pt a desena
    int x1,y1,x2,y2;
    private boolean first = true;
    ArrayList<Shape> shapes = new ArrayList<Shape>();

   //constructor canvas
    public MyCanvas(Color c , String s  ) {

        //selectam culoarea din toolbar
        this.color = c ;

        //punem un titlu pt panel-ul de desenare
        this.setBorder(BorderFactory.createTitledBorder("Drawing paper:"));

        setDoubleBuffered(false);

        addMouseMotionListener(this);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                x = e.getX();
                setX(x);
                y = e.getY();
                setY(y);
                //generam dimensiunea razei random
                radius = 50 + (int) (100 * Math.random());

                Random rand = new Random(); //generez o culoare random
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                Color randomColor = new Color(r, g, b);
                randomColor.brighter();
                //color = randomColor;

                //apelam metoda care deseneaza un oval
                if(s.compareTo("Oval")==0)
                {
                    drawO(x,y,color);
                    //incerc sa le adaug intr-un vector de shape

                }
                else
                    if (s.compareTo("Rectangle")==0) {
                        drawR(x, y, color);
                        System.out.println("Am ajuns aici");
                    }
                    else
                        if(s.compareTo("Line")==0)
                            drawL(x,y,color);
                        else
                            if(s.compareTo("Polygon")==0)
                                drawP(x,y,color);


                repaint(); //va apela metoda paintComponent
            }



        });


    }


   public void mouseDragged(MouseEvent e) {
        x =x1; y = y1;
        x1 = e.getX();
        y1 = e.getY();
        if(first){
            x = x1;
            y = y1;
            first  = false;
        }
       graphics.drawLine(x, y, x1, y1);
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }




    private void drawL(int x, int y, Color color) {
        Random rand = new Random();
        graphics.setColor(color);
        int f = rand.nextInt();
        int g = rand.nextInt();

        graphics.drawLine(x,y,f,g);



    }

    private void drawP(int x, int y, Color color) {
        Random rand = new Random();
        graphics.setColor(color);
        int f = rand.nextInt();
        int g = rand.nextInt();
        int[] xPoints = {rand.nextInt(),rand.nextInt(),rand.nextInt()};
        int[] yPoints = {rand.nextInt(),rand.nextInt(),rand.nextInt()};

        int n =3;
        graphics.drawPolygon(xPoints, yPoints, n);

        graphics.fillPolygon(xPoints, yPoints, n);


    }

    private void drawR(int x, int y, Color color) {
        Random r = new Random();
        int width=r.nextInt();
                int height=r.nextInt();
                graphics.setColor(color);
        graphics.drawRect(x,y, width,height);
        graphics.fillRect(x,y,width,height);


    }


    protected void paintComponent(Graphics g) {
        if(image == null) {
            image = new BufferedImage(500, 300, BufferedImage.TYPE_INT_ARGB);
            graphics = image.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
       g.drawImage(image, 0, 0, null);
        //graphics.drawLine(x, y, x1, y1);
    }

    public void drawO (int x, int y , Color c ) {
        graphics.setColor(c);
        graphics.drawOval(x - radius / 2, y - radius / 2, radius, radius);
        graphics.fillOval(x - radius / 2, y - radius / 2, radius, radius);


    }


    public void clear() {
        graphics.setPaint(Color.white);
        graphics.fillRect(0, 0, getSize().width, getSize().height);
       // graphics.setPaint(Color.black);
        repaint();
    }


    //metode folosite pentru load si save

   public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }


    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    public void clearAllShapes()
    {
        shapes.clear();
        repaint();
    }
    //clear the drawing
    public void clearLastShape()    //pop
    {
        int size = shapes.size();
        if(size > 0)
        {
            shapes.remove(size - 1);
        }
        repaint();
    }





}
