package net.aurxenon.ThistleMapEditor.Utils;

import com.googlecode.lanterna.TerminalPosition;

public class Vec2D {
    private int x;
    private int y;

    public Vec2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2D() {
        x = 0;
        y = 0;
    }

    public TerminalPosition geTerminalPosition() {
        return new TerminalPosition(x, y);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}