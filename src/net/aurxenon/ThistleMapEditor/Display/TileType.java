package net.aurxenon.ThistleMapEditor.Display;

import com.googlecode.lanterna.TextColor;

public class TileType {
    private String name;
    private char label;
    private TextColor foregroundColor;
    private TextColor backgroundColor;

    public TileType(String name, char label, TextColor foregroundColor, TextColor backgroundColor) {
        this.name = name;
        this.label = label;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
    }

    public String getName() {return name;}
    public char getLabel() {return label;}
    public TextColor getForegroundColor() {return foregroundColor;}
    public TextColor getBackgroundColor() {return backgroundColor;}
}