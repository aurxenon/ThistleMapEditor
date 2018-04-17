package net.aurxenon.ThistleMapEditor.Screen.Screens;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import net.aurxenon.ThistleMapEditor.Display.Camera;
import net.aurxenon.ThistleMapEditor.Display.Cursor;
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
    private Cursor cursor;

    public MapEditorScreen() {
        super(ScreenType.MAP_EDITOR);
    }

    @Override
    public void create() {
        display = new Display();
        try {
            cursor = new Cursor();
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
                        if (cursor.getCursorPos().getX() > Thistle.getCamera().getMaxViewableX()) {
                            Thistle.getCamera().moveRight(1);
                            /*System.out.println("Camera Size: " + Thistle.getCamera().getWorldWidth() + "-" + Thistle.getCamera().getWorldHeight());
                            System.out.println("Camera Position: " + Thistle.getCamera().getPosition().getX() + "-" + Thistle.getCamera().getPosition().getY());*/
                        } else {
                            cursor.moveRight(1);
                        }
                        break;
                    case ArrowLeft:
                        if (cursor.getCursorPos().getX() < Thistle.getCamera().getPosition().getX()) {
                            Thistle.getCamera().moveLeft(1);
                            /*System.out.println("Camera Size: " + Thistle.getCamera().getWorldWidth() + "-" + Thistle.getCamera().getWorldHeight());
                            System.out.println("Camera Position: " + Thistle.getCamera().getPosition().getX() + "-" + Thistle.getCamera().getPosition().getY());*/
                        } else {
                            cursor.moveLeft(1);
                        }
                        break;
                    case ArrowUp:
                        if (cursor.getCursorPos().getY() > Thistle.getCamera().getMaxViewableY()) {
                            Thistle.getCamera().moveUp(1);
                            /*System.out.println("Camera Size: " + Thistle.getCamera().getWorldWidth() + "-" + Thistle.getCamera().getWorldHeight());
                            System.out.println("Camera Position: " + Thistle.getCamera().getPosition().getX() + "-" + Thistle.getCamera().getPosition().getY());*/
                        } else {
                            cursor.moveUp(1);
                        }
                        break;
                    case ArrowDown:
                        if (cursor.getCursorPos().getY() < Thistle.getCamera().getPosition().getY()) {
                            Thistle.getCamera().moveDown(1);
                            /*System.out.println("Camera Size: " + Thistle.getCamera().getWorldWidth() + "-" + Thistle.getCamera().getWorldHeight());
                            System.out.println("Camera Position: " + Thistle.getCamera().getPosition().getX() + "-" + Thistle.getCamera().getPosition().getY());*/
                        } else {
                            cursor.moveDown(1);
                        }
                        break;
                    case Enter:
                        Vec2D placeDown = new Vec2D(cursor.getCursorPos().getX(), cursor.getCursorPos().getY());
                        Thistle.addTile(new Tile(placeDown));
                        break;
                    case Escape:
                        Thistle.getScreenManager().setScreen(new FileScreen());
                        break;
                }
                /*System.out.println("Cursor Camera Position: " + Thistle.getCamera().getCameraCoords(cursor).getX() + "-" + Thistle.getCamera().getCameraCoords(cursor).getY());
                System.out.println("Cursor Real Position: " + cursor.getCursorPos().getX() + "-" + cursor.getCursorPos().getY());*/
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
        display.drawCursor(cursor);
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