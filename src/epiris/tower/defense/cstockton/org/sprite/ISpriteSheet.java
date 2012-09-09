package epiris.tower.defense.cstockton.org.sprite;

public interface ISpriteSheet {
    public int ordinal();

    public String getSheet();
    public int getWidth();
    public int getHeight();
    public int getTileWidth();
    public int getTileHeight();
    public int getCols();
    public int getRows();
}
