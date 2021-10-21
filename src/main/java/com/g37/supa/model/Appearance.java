package com.g37.supa.model;

import java.util.Objects;

public class Appearance {
    private char form; //Maybe Matrix of chars in the future
    private String foregroundColor;
    private String backgroundColor;

    public Appearance(char form, String foregroundColor) {
        this.form = form;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = "#000000";
    }

    public Appearance(char form, String foregroundColor, String backgroundColor) {
        this.form = form;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
    }

    public char getForm() {
        return this.form;
    }

    public void setForm(char form) {
        this.form = form;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String color) {
        this.backgroundColor = color;
    }

    public String getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(String color) {
        this.foregroundColor = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appearance that = (Appearance) o;
        return form == that.form && Objects.equals(foregroundColor, that.foregroundColor) && Objects.equals(backgroundColor, that.backgroundColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(form, foregroundColor, backgroundColor);
    }
}
