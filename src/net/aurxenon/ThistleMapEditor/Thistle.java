package net.aurxenon.ThistleMapEditor;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import net.aurxenon.ThistleMapEditor.Display.Camera;
import net.aurxenon.ThistleMapEditor.Display.Tile;
import net.aurxenon.ThistleMapEditor.Display.TileType;
import net.aurxenon.ThistleMapEditor.Screen.ScreenManager;
import net.aurxenon.ThistleMapEditor.Screen.Screens.MainScreen;
import net.aurxenon.ThistleMapEditor.Utils.FileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Thistle {
    private static ScreenManager screenManager;
    private static ArrayList<Tile> tiles = new ArrayList<>();
    private static HashMap<String, TileType> tileTypeList = new HashMap<>();
    private static Camera camera;
    private static Terminal terminal;

    public static void main(String[] args) {
        create();
        while (true) {
            update();
            render();
            try {
                Thread.sleep(10);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static void create() {
        try {
            tileTypeList = new FileManager().extractTileTypes();
            terminal = new DefaultTerminalFactory().createTerminal();
        } catch(IOException e) {
            e.printStackTrace();
        }
        screenManager = new ScreenManager();
        screenManager.setScreen(new MainScreen());
        //this is mostly here for testing purposes
        /*for (int i = 0; i < 9; i++) {
            int x = new Random().nextInt(9);
            int y = new Random().nextInt(9);
            tiles.add(new Tile('%', new Vec2D(x,y)));
        }*/
    }
    private static void update() {
        screenManager.update();
    }
    private static void render() {
        screenManager.render();
        screenManager.renderUI();
    }

    public static ScreenManager getScreenManager() {return screenManager;}
    public static Camera getCamera() {return camera;}
    public static void setCamera(Camera screenCamera) {camera = screenCamera;}
    public static void addTile(Tile tile) {tiles.add(tile);}
    public static ArrayList<Tile> getTiles() {return tiles;}
    public static void setTiles(ArrayList<Tile> tileList) {tiles = tileList;}
    public static HashMap<String, TileType> getTileTypes() {return tileTypeList;}
    public static Terminal getTerminal() {return terminal;}
}