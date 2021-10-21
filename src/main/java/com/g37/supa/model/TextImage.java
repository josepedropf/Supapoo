package com.g37.supa.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextImage {
    List<List<Appearance>> chars;

    public TextImage(Size size, Appearance appearance) {
        chars = new ArrayList<>(size.getHeight());
        List<Appearance> line_array;
        for (int line = 0; line < size.getHeight(); line++) {
            line_array = new ArrayList<>();
            for (int column = 0; column < size.getWidth(); column++) {
                line_array.add(column, appearance);
            }
            chars.add(line_array);
        }
    }

    public TextImage(List<List<Appearance>> chars) {
        this.chars = chars;
    }

    public Size getSize() {
        return new Size(chars.get(0).size(), chars.size());
    }

    public Appearance getAppearanceAt(Position position) {
        return chars.get(position.getY()).get(position.getX());
    }

    public void setAppearanceAt(Position position, Appearance appearance) {
        chars.get(position.getY()).set(position.getX(), appearance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextImage textImage = (TextImage) o;
        return Objects.equals(chars, textImage.chars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chars);
    }
}
