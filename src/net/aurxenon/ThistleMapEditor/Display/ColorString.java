package net.aurxenon.ThistleMapEditor.Display;

import com.googlecode.lanterna.TextColor;

public class ColorString {
    private String text;
    private TextColor backgroundColor;
    private TextColor foregroundColor;

    public ColorString(String text, TextColor backgroundColor, TextColor foregroundColor) {
        this.text = text;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }
    public ColorString(String text) {
        this.text = text;
        this.backgroundColor = new TextColor.RGB(0, 0, 0);
        this.foregroundColor = new TextColor.RGB(255, 0, 0);
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public TextColor getForegroundColor() {
        return foregroundColor;
    }
    public void setForegroundColor(TextColor foregroundColor) {
        this.foregroundColor = foregroundColor;
    }
    public TextColor getBackgroundColor() {
        return backgroundColor;
    }
    public void setBackgroundColor(TextColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}