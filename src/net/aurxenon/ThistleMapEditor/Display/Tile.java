package net.aurxenon.ThistleMapEditor.Display;

import com.googlecode.lanterna.TextColor;
import net.aurxenon.ThistleMapEditor.Utils.Vec2D;

public class Tile {
    private TileType tileType = new TileType("undefined", '?', false, 0.0, 0.0, TextColor.ANSI.RED, TextColor.ANSI.BLUE);
    private Vec2D location;

    public Tile(TileType tileType, Vec2D location) {
        this.tileType = tileType;
        this.location = location;
    }
    public Tile(Vec2D location) {
        this.location = location;
    }

    public void setTileType(TileType tileType) {this.tileType = tileType;}
    public void setLocation(Vec2D location) {
        this.location = location;
    }

    public TileType getTileType() {return tileType;}
    public Vec2D getLocation() {return location;}
}