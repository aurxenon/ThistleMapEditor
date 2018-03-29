package net.aurxenon.ThistleMapEditor.Display;

import com.googlecode.lanterna.TextColor;
import net.aurxenon.ThistleMapEditor.Utils.Vec2D;

public class Tile {
    private char label = 'D';
    private Vec2D location = new Vec2D(0,0);
    private TextColor backgroundColor = new TextColor.RGB(0,0,0);
    private TextColor foregroundColor = new TextColor.RGB(0,255,0);

    public Tile(char label, Vec2D location, TextColor foregroundColor, TextColor backgroundColor) {
        this.label = label;
        this.location = location;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
    }
    public Tile(char label, Vec2D location, TextColor foregroundColor) {
        this.label = label;
        this.location = location;
        this.foregroundColor = foregroundColor;
    }
    public Tile(char label, Vec2D location) {
        this.label = label;
        this.location = location;
    }
    public Tile(char label) {
        this.label = label;
    }
    public Tile(Vec2D location) {
        this.location = location;
    }

    public Vec2D getLocation() {
        return location;
    }
    public char getLabel() {
        return label;
    }
    public void setLabel(char label) {
        this.label = label;
    }
    public void setLocation(Vec2D location) {
        this.location = location;
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