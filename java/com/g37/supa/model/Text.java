package com.g37.supa.model;

import java.util.EnumSet;
import java.util.Objects;

public class Text {
    private final String string;
    private final String backgroundColor;
    private final String foregroundColor;
    private final EnumSet<TEXTMODIFIER> textmodifiers;

    public Text(String string, String backgroundColor, String foregroundColor) {
        this.string = string;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.textmodifiers = EnumSet.noneOf(TEXTMODIFIER.class);
    }

    public Text(String string, String backgroundColor, String foregroundColor, EnumSet<TEXTMODIFIER> textmodifiers) {
        this.string = string;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.textmodifiers = textmodifiers;
    }

    public String getString() {
        return string;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getForegroundColor() {
        return foregroundColor;
    }

    public EnumSet<TEXTMODIFIER> getModifiers() {
        return textmodifiers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return Objects.equals(string, text.string) && Objects.equals(backgroundColor, text.backgroundColor) && Objects.equals(foregroundColor, text.foregroundColor) && Objects.equals(textmodifiers, text.textmodifiers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string, backgroundColor, foregroundColor, textmodifiers);
    }

    public enum TEXTMODIFIER {
        BLINK,
        BOLD,
        BORDERED,
        CIRCLED,
        CROSSED_OUT,
        FRAKTUR,
        ITALIC,
        REVERSE,
        UNDERLINE
    }
}
