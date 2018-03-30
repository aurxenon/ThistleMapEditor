package net.aurxenon.ThistleMapEditor.Screen.Screens;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import net.aurxenon.ThistleMapEditor.Display.ColorString;
import net.aurxenon.ThistleMapEditor.Display.Display;
import net.aurxenon.ThistleMapEditor.Screen.EditorScreen;
import net.aurxenon.ThistleMapEditor.Screen.ScreenType;
import net.aurxenon.ThistleMapEditor.Thistle;
import net.aurxenon.ThistleMapEditor.Utils.Vec2D;

import java.io.IOException;

public class MainScreen extends EditorScreen {
    private Display display;
    private String appName = "Thistle Map Editor";

    public MainScreen () {
        super(ScreenType.MAIN_MENU);
    }

    @Override
    public void create() {
        display = new Display();
        display.writeText(new Vec2D((display.getMaxX() / 2) - (appName.length() / 2), 0), new ColorString(appName));
        display.writeText(new Vec2D(0, 1), new ColorString("Press ENTER to load a map or ESC to quit the map editor"));
    }

    @Override
    public void update() {
        try {
            KeyStroke key;
            key = Thistle.getTerminal().readInput();
            if (key.getKeyType() == KeyType.Enter) {
                Thistle.getScreenManager().setScreen(new MapEditorScreen());
            }
            if (key.getKeyType() == KeyType.Escape) {
                this.clearScreen();
                display.closeApp();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {

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
        clearScreen();
    }
}
