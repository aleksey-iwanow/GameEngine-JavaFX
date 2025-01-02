package games.dark;

public class Cell {
    public Item item;
    public Road road;

    public void setItem(Item item){
        this.item = item;
        if (item != null)
            item.cerrentCell = this;
    }

    public void release(){
        if (item != null && !item.pickUp)
            item.pickUp();
    }

}
