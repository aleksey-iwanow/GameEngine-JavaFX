package games.dark;

public class Item extends ObjectInGrid{
    public boolean pickUp;
    public Cell cerrentCell;

    public void pickUp() {
        pickUp = true;
    }
}
