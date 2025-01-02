package games.dark;

import GDK.engine.*;
import GDK.engine.components.AudioSource;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class GameManager extends ScriptControl {
    public static Key key;
    public static Lock lock;
    public static Player player;
    public static GameObject menu;
    public static boolean isStartGame;
    public static GameManager instance;
    private BackBox[] backs;
    private ObjectMapper objectMapper = new ObjectMapper();
    public static ArrayList<Enemy> enemys = new ArrayList<>();
    public Levels levelsConfig;
    public int indexLevel=0;
    private AudioSource source;
    private AudioSource sourceNewLevel;
    private AudioSource sourceLose;

    public static void moveOthers() {
        for (int i = 0; i < enemys.size(); i++) {
            Enemy m = enemys.get(i);
            m.move();
        }
    }


    public void startGame(){
        backs = getBacks();
        offMenu();
        reloadGame();
    }

    public void reloadGame(){
        for (var road: findAllWithScript(Road.class)) {
            road.destroy();
        }
        Grid.initGrid(Config.GRIDSIZE);
        runForMoveables(this::destroy);
        enemys.clear();
        destroy((ScriptControl) key);
        destroy(lock);

        if (indexLevel == levelsConfig.levels.length){
            instantiate("endPanel.prefab");
        } else {
            openBackBoxes();
            loadLevel();
            isStartGame = true;
        }
    }

    private BackBox[] getBacks(){
        return new BackBox[]{
                instantiate("backBox.prefab").getScript(BackBox.class),
                instantiate("backBox.prefab").getScript(BackBox.class),
                instantiate("backBox.prefab").getScript(BackBox.class),
                instantiate("backBox.prefab").getScript(BackBox.class),
        };
    }

    public void openBackBoxes(){
        backs[0].transform.setCenterPosition(0, 0);
        backs[0].startMove(new Vector2(-1, -1));

        backs[1].transform.setCenterPosition(800, 0);
        backs[1].startMove(new Vector2(1, -1));

        backs[2].transform.setCenterPosition(0, 800);
        backs[2].startMove(new Vector2(-1, 1));

        backs[3].transform.setCenterPosition(800, 800);
        backs[3].startMove(new Vector2(1, 1));
    }

    public void closeBackBoxes(){
        backs[0].transform.setCenterPosition(-400, -400);
        backs[0].startMove(new Vector2(1, 1), true);

        backs[1].transform.setCenterPosition(1200, -400);
        backs[1].startMove(new Vector2(-1, 1));

        backs[2].transform.setCenterPosition(-400, 1200);
        backs[2].startMove(new Vector2(1, -1));

        backs[3].transform.setCenterPosition(1200, 1200);
        backs[3].startMove(new Vector2(-1, -1));

    }


    public void loseGame(){
        if (!isStartGame) return;
        isStartGame = false;
        sourceLose.Play();
        closeBackBoxes();
    }

    void newLevel(){
        indexLevel++;
        sourceNewLevel.Play();
        isStartGame = false;
        closeBackBoxes();
    }

    private Levels getLevelsConfig(){
        try {
            File file = new File(GDK.engine.Config.PATH_PROJECT+ "res\\levels.json");
            return objectMapper.readValue(file, Levels.class);
        }
        catch (IOException ex){
            debug(ex.getMessage());
            return null;
        }
    }

    @Override
    public void awake() {
        instance = this;
        source = new AudioSource("audio/backMusic.mp3");
        sourceNewLevel = new AudioSource("audio/newLevel.wav");
        sourceLose = new AudioSource("audio/lose.mp3");
        source.Play(true);
        levelsConfig = getLevelsConfig();
        menu = find("cover");
        player = findWithScript(Player.class);
    }

    public void runForMoveables(RunnableForMoveable func){
        for (int i = 0; i < enemys.size(); i++) {
            func.run(enemys.get(i));
        }
    }

    public void offMenu(){
        if (menu != null) {
            menu.destroy();
            menu = null;
        }
    }

    private Level currentLevel(){
        return levelsConfig.levels[indexLevel];
    }

    public void loadLevel(){
        player.playerStartGame(currentLevel().pPos);
        key = instantiate("key.prefab").getScript(Key.class);
        key.setPos(currentLevel().kPos);

        lock = instantiate("lock.prefab").getScript(Lock.class);
        lock.setPos(currentLevel().lockPos);
        for (var pos: currentLevel().mPos){
            var enemy = instantiate("enemy.prefab").getScript(Enemy.class);
            enemy.setPos(pos);
            enemys.add(enemy);
        }

    }
}
