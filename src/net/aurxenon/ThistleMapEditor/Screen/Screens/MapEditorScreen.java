package net.aurxenon.ThistleMapEditor.Screen.Screens;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import net.aurxenon.ThistleMapEditor.Display.Camera;
import net.aurxenon.ThistleMapEditor.Display.Display;
import net.aurxenon.ThistleMapEditor.Display.Tile;
import net.aurxenon.ThistleMapEditor.Screen.EditorScreen;
import net.aurxenon.ThistleMapEditor.Screen.ScreenType;
import net.aurxenon.ThistleMapEditor.Thistle;
import net.aurxenon.ThistleMapEditor.Utils.Vec2D;

import java.io.IOException;
import java.util.ArrayList;

public class MapEditorScreen extends EditorScreen {
    private Display display;
    private Vec2D cursorPosition = new Vec2D(0,0);

    public MapEditorScreen() {
        super(ScreenType.MAP_EDITOR);
    }

    @Override
    public void create() {
        display = new Display();
        try {
            //for now, the maps are only going to be the size of the terminal
            Thistle.setCamera(new Camera(new Vec2D(0,0), Thistle.getTerminal().getTerminalSize().getColumns(), Thistle.getTerminal().getTerminalSize().getRows(), Thistle.getTerminal().getTerminalSize().getColumns(), Thistle.getTerminal().getTerminalSize().getRows()));
            Thistle.getTerminal().setCursorVisible(true);
            //render once so the screen isn't blank when it's first created
            render();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        KeyStroke key;
        try {
            key = Thistle.getTerminal().readInput();
            if (key != null) {
                switch (key.getKeyType()) {
                    case ArrowRight:
                        cursorPosition.setX(cursorPosition.getX() + 1);
                        display.moveCursor(cursorPosition);
                        break;
                    case ArrowLeft:
                        cursorPosition.setX(cursorPosition.getX() - 1);
                        display.moveCursor(cursorPosition);
                        break;
                    case ArrowUp:
                        cursorPosition.setY(cursorPosition.getY() - 1);
                        display.moveCursor(cursorPosition);
                        break;
                    case ArrowDown:
                        cursorPosition.setY(cursorPosition.getY() + 1);
                        display.moveCursor(cursorPosition);
                        break;
                    case Enter:
                        Vec2D placeDown = new Vec2D(cursorPosition.getX(), cursorPosition.getY());
                        Thistle.addTile(new Tile(placeDown));
                        break;
                    case Escape:
                        Thistle.getScreenManager().setScreen(new FileScreen());
                        break;
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        //render is always called before renderUI so there shouldn't be any risks of it clearing the UI or anything
        clearScreen();
        for (Tile tile : Thistle.getTiles()) {
            display.drawSymbol(tile);
        }
    }

    @Override
    public void renderUI() {

    }

    @Override
    public void resize() {

    }

    @Override
    public void clearScreen() {
        display.clear();
    }

    @Override
    public void disposeScreen() {

    }
}