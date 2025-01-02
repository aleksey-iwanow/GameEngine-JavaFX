package games.dark;

import GDK.engine.Vector2;

public enum Trail {
    VER (new Vector2[]{
            Vector2.up(),
            Vector2.down(),
    }, "img/trail1.png"),
    HOR (new Vector2[]{
            Vector2.left(),
            Vector2.right(),
    }, "img/trail2.png"),
    HOR_AND_UP (new Vector2[]{
            Vector2.left(),
            Vector2.right(),
            Vector2.up()
    }, "img/trail5.png"),
    HOR_AND_DW (new Vector2[]{
            Vector2.left(),
            Vector2.right(),
            Vector2.down()
    }, "img/trail4.png"),
    ALL (new Vector2[]{
            Vector2.left(),
            Vector2.right(),
            Vector2.up(),
            Vector2.down()
    }, "img/trail3.png"),
    VER_AND_LF (new Vector2[]{
            Vector2.left(),
            Vector2.up(),
            Vector2.down()
    }, "img/trail7.png"),
    VER_AND_RG (new Vector2[]{
            Vector2.right(),
            Vector2.up(),
            Vector2.down()
    }, "img/trail6.png");

    private Vector2[] branches;
    private String pathImage;

    Trail(Vector2[] branches, String pathImage) {
       this.branches = branches;
       this.pathImage = pathImage;
    }

    public String getPathImage() {
        return pathImage;
    }

    public Vector2[] getBranches() {
        return branches;
    }
}
