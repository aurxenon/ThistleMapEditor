package net.aurxenon.ThistleMapEditor.Screen;

import net.aurxenon.ThistleMapEditor.Display.Tile;

import java.util.ArrayList;

public abstract class EditorScreen {

    public ScreenType type = ScreenType.MAIN_MENU;

    public EditorScreen (ScreenType type) {
        this.type = type;
    }

    public abstract void create ();
    public abstract void update ();
    public abstract void render ();
    public abstract void renderUI ();
    public abstract void resize ();
    public abstract void clearScreen();
    public abstract void disposeScreen();
}