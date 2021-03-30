package com.company;

import java.awt.*;

public class Configurations {
    private Integer size;
    private Integer noOfSlides;
    private Integer stroke;
    private Color color;

    public Configurations(Integer size, Integer no_of_slides, Integer stroke, Color color) {
        this.size = size;
        this.noOfSlides = no_of_slides;
        this.stroke = stroke;
        this.color = color;
    }



    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }


    public Integer getStroke() {
        return stroke;
    }

    public void setStroke(Integer stroke) {
        this.stroke = stroke;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getNoOfSlides() {
        return noOfSlides;
    }

    public void setNoOfSlides(Integer noOfSlides) {
        this.noOfSlides = noOfSlides;
    }
}
