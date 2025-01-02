package games.dark;

import GDK.engine.*;

import java.util.ArrayList;

public class Road extends ScriptControl {
    public Trail currentTrail;
    public Item item;
    public Vector2 posInGrid;
    public ArrayList<Road> branches = new ArrayList<>();
    public boolean createBranches;
    private Vector2 oldPosInGrid = null;


    public void setPosInGrid(Vector2 posInGrid, Vector2 oldPosInGrid){
        setPosInGrid(posInGrid, oldPosInGrid, null);
    }

    public void setPosInGrid(Vector2 posInGrid, Vector2 oldPosInGrid, Vector2 vecParent){
        transform.setSize(Config.CELLSIZE);
        this.posInGrid = posInGrid;
        this.oldPosInGrid = oldPosInGrid;
        transform.setPos(posInGrid.increaseVector(Config.CELLSIZE));

        if (createBranches) {
            currentTrail = getTrail(Grid.allTrails);
            createBranches();
        }
        else{
            setTrail(vecParent);
        }
        gameObject.image.setImageView(currentTrail.getPathImage());
    }

    public void createBranches(){
        for (Vector2 vec: currentTrail.getBranches()) {
            Vector2 newPos = posInGrid.addVector(vec);

            newPos = Grid.convertWithBorderPos(newPos);

            if (!Grid.posBusy(newPos)){
                Road road = Grid.instCell();
                Grid.addRoad(road, newPos);
                road.setPosInGrid(newPos, posInGrid, vec.reverse());
                branches.add(road);
            }

        }
    }

    private Trail getTrail(Trail[] trails){
        int random = Tools.randomValue(0, trails.length);
        return trails[random];
    }

    public void setTrail(Vector2 vecParent){
        ArrayList<Trail> possibleTrails = new ArrayList<>();
        for (int i = 0; i < Grid.allTrails.length; i++) {
            var trail = Grid.allTrails[i];
            for (var br: trail.getBranches()){
                if (br.equal(vecParent)){
                    possibleTrails.add(trail);
                }
            }
        }
        currentTrail = getTrail(possibleTrails.toArray(Trail[]::new));
    }

    public void remove(){
        Grid.clearRoad(posInGrid);
        destroy();
    }

    public void clear() {
        for (Road branch : branches) {
            branch.remove();
        }
        branches.clear();
    }
}
