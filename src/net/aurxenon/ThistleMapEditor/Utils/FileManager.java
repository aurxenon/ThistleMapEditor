package net.aurxenon.ThistleMapEditor.Utils;

import com.googlecode.lanterna.TextColor;
import com.sun.org.apache.xpath.internal.operations.Bool;
import net.aurxenon.ThistleMapEditor.Display.Tile;
import net.aurxenon.ThistleMapEditor.Display.TileType;
import net.aurxenon.ThistleMapEditor.Thistle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FileManager {
    String filePath;
    Document doc;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public FileManager() {}

    public HashMap<String, TileType> extractTileTypes() {
        try {
            File input;
            //very hacky but it manages to get the directory the program is running in
            if (!new File(new File("").getAbsolutePath() + "\\TileType.xml").exists()) {
                input = new File(new File("").getAbsolutePath() + "\\TileType.xml");
                input.createNewFile();
            } else {
                input = new File(new File("").getAbsolutePath() + "\\TileType.xml");
            }
            doc = Jsoup.parse(input, "UTF-8");
        }catch(IOException e) {
            e.printStackTrace();
        }
        HashMap<String, TileType> tileTypeList = new HashMap<>();
        Elements mapTiles = doc.getElementsByTag("tiletype");
        for (Element mapTile : mapTiles) {
            String name = mapTile.text();

            char label = mapTile.attr("label").charAt(0);

            boolean naturalGeneration = Boolean.parseBoolean(mapTile.attr("naturalGeneration").toString());

            double minChance = Double.valueOf(mapTile.attr("minChance").toString());
            double maxChance = Double.valueOf(mapTile.attr("maxChance").toString());

            int foregroundR = Integer.parseInt(mapTile.attr("foregroundR"));
            int foregroundG = Integer.parseInt(mapTile.attr("foregroundG"));
            int foregroundB = Integer.parseInt(mapTile.attr("foregroundB"));
            TextColor foregroundColor = new TextColor.RGB(foregroundR, foregroundG, foregroundB);

            int backgroundR = Integer.parseInt(mapTile.attr("backgroundR"));
            int backgroundG = Integer.parseInt(mapTile.attr("backgroundG"));
            int backgroundB = Integer.parseInt(mapTile.attr("backgroundB"));

            TextColor backgroundColor = new TextColor.RGB(backgroundR, backgroundG, backgroundB);
            tileTypeList.put(name, new TileType(name, label, naturalGeneration, minChance, maxChance, foregroundColor, backgroundColor));
        }
        return tileTypeList;
    }

    public ArrayList<Tile> extractTiles() {
        try {
            File input = new File(filePath);
            doc = Jsoup.parse(input, "UTF-8");
        }catch(IOException e) {
            e.printStackTrace();
        }
        ArrayList<Tile> tileList = new ArrayList<Tile>();
        Elements mapTiles = doc.getElementsByTag("tile");
        for (Element mapTile : mapTiles) {
            String name = mapTile.text();
            int x = Integer.parseInt(mapTile.attr("x"));
            int y = Integer.parseInt(mapTile.attr("y"));
            tileList.add(new Tile(Thistle.getTileTypes().get(name), new Vec2D(x,y)));
        }
        return tileList;
    }

    public void saveTiles(ArrayList<Tile> tileList) {
        try {
            File input = new File(filePath);
            if (!input.exists()) {
                input.createNewFile();
            }
            doc = Jsoup.parse("<html></html>");
            doc.empty();
            Element tiles = new Element("tiles");
            tiles.appendTo(doc);
            for (Tile tile : tileList) {
                Element mapTile = new Element("tile");
                mapTile.appendTo(tiles);
                mapTile.text(tile.getTileType().getName());
                mapTile.attr("x", Integer.toString(tile.getLocation().getX()));
                mapTile.attr("y", Integer.toString(tile.getLocation().getY()));
            }
            FileWriter mapWriter = new FileWriter(filePath, false);
            mapWriter.write(doc.outerHtml());
            mapWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}