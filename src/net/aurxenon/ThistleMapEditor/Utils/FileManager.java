package net.aurxenon.ThistleMapEditor.Utils;

import net.aurxenon.ThistleMapEditor.Display.Tile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
    String filePath;
    Document doc;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Tile> extractTiles() {
        try {
            File input = new File(filePath);
            doc = Jsoup.parse(input, "UTF-8");
        }catch(IOException e) {
            e.printStackTrace();
        }
        ArrayList<Tile> tileList = new ArrayList<Tile>();
        Element mapTileList = doc.getElementById("tiles");
        Elements mapTiles = doc.getElementsByTag("tile");
        for (Element mapTile : mapTiles) {
            char label = mapTile.text().charAt(0);
            int x = Integer.parseInt(mapTile.attr("x"));
            int y = Integer.parseInt(mapTile.attr("y"));
            tileList.add(new Tile(label, new Vec2D(x,y)));
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
                mapTile.text(Character.toString(tile.getLabel()));
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