package ru.ifmo.uml.entity;

public class Color {

    private int colorId;
    private String colorName;

    public Color() {
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public String toString() {
        return "Color{" +
                "colorId=" + colorId +
                ", colorName='" + colorName + '\'' +
                '}';
    }
}
