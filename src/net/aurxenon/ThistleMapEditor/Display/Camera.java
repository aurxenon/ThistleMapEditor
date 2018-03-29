package net.aurxenon.ThistleMapEditor.Display;

import net.aurxenon.ThistleMapEditor.Utils.Vec2D;

public class Camera {
    private Vec2D position;
    private int worldWidth;
    private int worldHeight;
    private int screenWidth;
    private int screenHeight;

    public Camera(Vec2D position, int worldWidth, int worldHeight, int screenWidth, int screenHeight) {
        this.position = position;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public Vec2D getCameraCoords(Tile tile) {
        Vec2D cameraCoords = new Vec2D();
        cameraCoords.setX(tile.getLocation().getX() - position.getX());
        cameraCoords.setY(tile.getLocation().getY() - position.getY());
        return cameraCoords;
    }
    public Vec2D getPosition() {
        return position;
    }
    public void setPosition(Vec2D position) {
        this.position = position;
    }
    public int getWorldWidth() {
        return worldWidth;
    }
    public void setWorldWidth(int worldWidth) {
        this.worldWidth = worldWidth;
    }
    public int getWorldHeight() {
        return worldHeight;
    }
    public void setWorldHeight() {
        this.worldHeight = worldHeight;
    }
    public int getScreenWidth() {
        return screenWidth;
    }
    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }
    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }
}
