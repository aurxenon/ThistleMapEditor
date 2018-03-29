package net.aurxenon.ThistleMapEditor.Screen.Screens;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import net.aurxenon.ThistleMapEditor.Display.ColorString;
import net.aurxenon.ThistleMapEditor.Display.Display;
import net.aurxenon.ThistleMapEditor.Display.Tile;
import net.aurxenon.ThistleMapEditor.Screen.EditorScreen;
import net.aurxenon.ThistleMapEditor.Screen.ScreenType;
import net.aurxenon.ThistleMapEditor.Thistle;
import net.aurxenon.ThistleMapEditor.Utils.FileManager;
import net.aurxenon.ThistleMapEditor.Utils.Vec2D;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class FileScreen extends EditorScreen {
    private Display display;
    private String appName = "Thistle Map Editor";
    private String options = "Press ENTER to load a map or TAB to save the map";

    public FileScreen() {
        super(ScreenType.FILE_MANAGER);
    }

    @Override
    public void create() {
        display = new Display();
        display.writeText(new Vec2D((display.getMaxX() / 2) - (appName.length() / 2), 0), new ColorString(appName));
        display.writeText(new Vec2D(0, 1), new ColorString(options));
    }

    @Override
    public void update() {
        try {
            KeyStroke key;
            key = Thistle.getTerminal().readInput();
            if (key.getKeyType() == KeyType.Enter) {
                Panel panel = new Panel();
                panel.setLayoutManager(new GridLayout(2));

                panel.addComponent(new Label("File Path:"));
                final TextBox filePath = new TextBox().setValidationPattern(Pattern.compile(".*?")).addTo(panel);

                BasicWindow window = new BasicWindow();

                panel.addComponent(new EmptySpace(new TerminalSize(0, 0)));
                new Button("Load", new Runnable() {
                    @Override
                    public void run() {
                        String path = filePath.getText();
                        FileManager fileManager = new FileManager(path);
                        Thistle.setTiles(fileManager.extractTiles());
                        window.close();
                        Thistle.getScreenManager().setScreen(new MapEditorScreen());
                    }
                }).addTo(panel);

                window.setComponent(panel);

                MultiWindowTextGUI gui = new MultiWindowTextGUI(display.getScreen(), new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
                gui.addWindowAndWait(window);
            }
            if (key.getKeyType() == KeyType.Tab) {
                Panel panel = new Panel();
                panel.setLayoutManager(new GridLayout(2));

                panel.addComponent(new Label("File Path:"));
                final TextBox filePath = new TextBox().setValidationPattern(Pattern.compile(".*?")).addTo(panel);

                BasicWindow window = new BasicWindow();

                panel.addComponent(new EmptySpace(new TerminalSize(0, 0)));
                new Button("Save", new Runnable() {
                    @Override
                    public void run() {
                        String path = filePath.getText();
                        FileManager fileManager = new FileManager(path);
                        fileManager.saveTiles(Thistle.getTiles());
                        window.close();
                        Thistle.getScreenManager().setScreen(new MapEditorScreen());
                    }
                }).addTo(panel);

                window.setComponent(panel);

                MultiWindowTextGUI gui = new MultiWindowTextGUI(display.getScreen(), new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
                gui.addWindowAndWait(window);
            }
            else if (key.getKeyType() == KeyType.Escape) {
                Thistle.getScreenManager().setScreen(new FileScreen());
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

    }

    @Override
    public void disposeScreen() {

    }
}
