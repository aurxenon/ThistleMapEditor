package net.aurxenon.ThistleMapEditor.Display;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import net.aurxenon.ThistleMapEditor.Globals;
import net.aurxenon.ThistleMapEditor.Thistle;
import net.aurxenon.ThistleMapEditor.Utils.Vec2D;

import java.io.IOException;

public class Display {
    private Screen screen;
    private TerminalSize terminalSize;

    int columns;
    int rows;

    public Display() {
        try {
            Thistle.getTerminal().enterPrivateMode();
            //terminal.setBackgroundColor(TextColor.ANSI.GREEN);
            screen = new TerminalScreen(Thistle.getTerminal());
            screen.startScreen();
            screen.setCursorPosition(null);
            terminalSize = Thistle.getTerminal().getTerminalSize();
            Thistle.getTerminal().clearScreen();
            columns = terminalSize.getColumns();
            rows = terminalSize.getRows();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void drawSymbol(Tile tile) {
        try {
            TextColor textColor = tile.getForegroundColor();
            TextColor backgroundColor = tile.getBackgroundColor();
            TextCharacter drawCharacter = new TextCharacter(tile.getLabel(), textColor, backgroundColor);
            Vec2D tileLocation = Thistle.getCamera().getCameraCoords(tile);

            screen.setCharacter(tileLocation.getX(), tileLocation.getY(), drawCharacter);
            screen.refresh();
            Thistle.getTerminal().flush();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void writeText(Vec2D location, ColorString text) {
        try {
            TextGraphics textGraphics = screen.newTextGraphics();
            textGraphics.setBackgroundColor(text.getBackgroundColor());
            textGraphics.setForegroundColor(text.getForegroundColor());
            textGraphics.putString(location.getX(), location.getY(), text.getText());
            screen.refresh();
            Thistle.getTerminal().flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void moveCursor(Vec2D cursorPos) {
        TerminalPosition terminalPosition = new TerminalPosition(cursorPos.getX(), cursorPos.getY());
        screen.setCursorPosition(terminalPosition);
        try {
            screen.refresh();
            Thistle.getTerminal().flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void clear(){
        screen.clear();
    }

    public void refresh() {
        try {
            screen.refresh();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Screen getScreen() {
        return screen;
    }

    public int getMaxX() {
        return terminalSize.getColumns();
    }
    public int getMaxY() {
        return terminalSize.getRows();
    }

    public void closeApp() {
        try {
            screen.close();
            Thistle.getTerminal().exitPrivateMode();
            Thistle.getTerminal().close();
            System.exit(0);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}