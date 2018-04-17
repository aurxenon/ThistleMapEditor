package net.aurxenon.ThistleMapEditor.Display;

import com.googlecode.lanterna.TextColor;

public class TileType {
    private String name;
    private char label;
    boolean naturalGeneration;
    double minChance;
    double maxChance;
    private TextColor foregroundColor;
    private TextColor backgroundColor;

    public TileType(String name, char label, boolean naturalGeneration, double minChance, double maxChance, TextColor foregroundColor, TextColor backgroundColor) {
        this.name = name;
        this.label = label;
        this.naturalGeneration = naturalGeneration;
        this.minChance = minChance;
        this.maxChance = maxChance;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
    }

    public String getName() {return name;}
    public char getLabel() {return label;}
    public boolean getNaturalGeneration() {return naturalGeneration;}
    public double getMinChance() {return minChance;}
    public double getMaxChance() {return maxChance;}
    public TextColor getForegroundColor() {return foregroundColor;}
    public TextColor getBackgroundColor() {return backgroundColor;}
}