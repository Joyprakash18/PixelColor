package com.example.pixelcolor;

public class Color {
    private  int red;
    private  int green;
    private  int blue;
    private String hexCode;

    public Color(int red, int green, int blue, String hexCode) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.hexCode = hexCode;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }
}
