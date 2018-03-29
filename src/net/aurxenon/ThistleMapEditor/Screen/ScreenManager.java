package net.aurxenon.ThistleMapEditor.Screen;

import net.aurxenon.ThistleMapEditor.Display.Tile;

import java.util.ArrayList;

public class ScreenManager {

    private EditorScreen currentScreen;
    private EditorScreen newScreen;

    public ScreenManager () {
        newScreen = null;
    }

    public void render () {
        if (currentScreen != null)
            currentScreen.render();
    }

    public void renderUI () {
        if (currentScreen != null)
            currentScreen.renderUI();
    }

    public void resize () {
        if (currentScreen != null)
            currentScreen.resize();
    }

    public void update () {
        if (newScreen != null) {
            if (currentScreen != null) {
                currentScreen.disposeScreen();
            }
            currentScreen = newScreen;
            newScreen.create();
            newScreen = null;
        }
        currentScreen.update();
    }

    public void setScreen (EditorScreen screen) {
        newScreen = screen;
    }

    public EditorScreen getScreen () {
        return currentScreen;
    }

    public ScreenType getScreenType () {
        return currentScreen.type;
    }
}