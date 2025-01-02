package game.dark;

import GDK.engine.*;

public class Item extends ObjectInGrid{
    public boolean pickUp;
    public Cell cerrentCell;

    public void pickUp() {
        pickUp = true;
    }
}
