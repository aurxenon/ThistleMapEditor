package net.aurxenon.ThistleMapEditor.Display;

import net.aurxenon.ThistleMapEditor.Utils.Vec2D;

public class Cursor {
    private Vec2D cursorPos;

    public Cursor() {
        this.cursorPos = new Vec2D(0,0);
    }
    public Cursor(int x, int y) {
        this.cursorPos = new Vec2D(x,y);
    }

    public void moveRight(int distance) { cursorPos.setX(cursorPos.getX() + distance); }
    public void moveLeft(int distance) { cursorPos.setX(cursorPos.getX() - distance); }
    public void moveUp(int distance) { cursorPos.setY(cursorPos.getY() + distance); }
    public void moveDown(int distance) { cursorPos.setY(cursorPos.getY() - distance); }
    public void setPosition(Vec2D position) { cursorPos = position; }
    public Vec2D getCursorPos() { return cursorPos; }
}
