package game.dark;

import GDK.engine.GameObject;
import GDK.engine.Tools;
import GDK.engine.Vector2;

public class Grid {
    public static Cell[][] cells;
    public static Trail[] allTrails = Trail.values();

    public static void initGrid(Vector2 gridSize){
        cells = new Cell[gridSize.yInt()][gridSize.xInt()];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public static Vector2 getRandomPos(){
        return new Vector2(Tools.randomValue(0, Config.GRIDSIZE.xInt()), Tools.randomValue(0, Config.GRIDSIZE.yInt()));
    }

    public static Vector2 gridToPos(Vector2 posInGrid){
        return posInGrid.increaseVector(Config.CELLSIZE);
    }

    public static Border checkBorder(Vector2 posInGrid) {
        return checkBorder(posInGrid, () -> {});
    }

    public static Border checkBorder(Vector2 posInGrid, Runnable defaultMethod){
        if (posInGrid.x >= Config.GRIDSIZE.x) {
            defaultMethod.run();
            return Border.RIGHT;
        } else if (posInGrid.x < 0) {
            defaultMethod.run();
            return Border.LEFT;
        } else if (posInGrid.y < 0) {
            defaultMethod.run();
            return Border.DOWN;
        } else if (posInGrid.y >= Config.GRIDSIZE.y) {
            defaultMethod.run();
            return Border.UP;
        }
        return Border.ALLGOOD;
    }

    public static Road instCell(){
        return GameObject.instantiate("cell.prefab").getScript(Road.class);
    }

    public static void createRoad(Vector2 posInGrid, Vector2 oldPosInGrid){
        if (checkBorder(posInGrid) != Border.ALLGOOD) return;
        Cell cell = cells[posInGrid.yInt()][posInGrid.xInt()];
        if (cell.road == null) {
            cell.road = instCell();
            cell.road.createBranches = true;
            cell.road.setPosInGrid(posInGrid, oldPosInGrid);
        }
        else{
            cell.road.createBranches();
        }
        if (oldPosInGrid != null) {
            Road oldC = cells[oldPosInGrid.yInt()][oldPosInGrid.xInt()].road;
            if (oldC != null){
                oldC.branches.remove(cell.road);
                oldC.clear();
            }
            cell.road.branches.add(oldC);
        }
    }

    public static void clearRoad(Vector2 posInGrid) {
        cells[posInGrid.yInt()][posInGrid.xInt()].road = null;
    }

    public static Vector2 convertWithBorderPos(Vector2 pos){
        Vector2 newPos = pos.copy();
        switch(Grid.checkBorder(newPos)){
            case Border.RIGHT -> newPos.x -= Config.GRIDSIZE.x;
            case Border.LEFT -> newPos.x += Config.GRIDSIZE.x;
            case Border.UP -> newPos.y -= Config.GRIDSIZE.y;
            case Border.DOWN -> newPos.y += Config.GRIDSIZE.y;
        }
        return newPos;
    }

    public static void checkCollisionCell(Vector2 coll){
        Cell cell = getCell(coll);
        if (cell == null) return;
        cell.release();
    }


    public static boolean posBusy(Vector2 newPos) {
        return getCell(newPos).road != null;
    }

    public static void addRoad(Road road, Vector2 posInGrid) {
        getCell(posInGrid).road = road;
    }

    public static Cell getCell(Vector2 pos) {
        try {
            return cells[pos.yInt()][pos.xInt()];
        }
        catch (ArrayIndexOutOfBoundsException ex){
            return null;
        }
    }
}
