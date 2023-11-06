package game;
import java.util.Random;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import chaseLogic.Enemy;
import chaseLogic.Player;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Map {

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public static final char WALL = '#';
    public static final char ENEMY = 'X';
    public static final char GOAL = 'O';
    public static final char PLAYER = 'o';
    public static final char EMPTY = ' ';

    private int size;
    private char[][] cells;

    private Random random = new Random();
    private Player player = new Player();
    private Enemy enemy = new Enemy();

    public Map(int size) {
        this.size = size;
        this.cells = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = ' ';
            }
        }
    }

    public Random getRandom() {
        return random;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public int getSize() {
        return size;
    }

    public char[][] getCells() {
        return cells;
    }

    public void setCell(int i, int j, char val) {
        cells[i][j] = val;
    }

    public void print() {
        Properties props = new Properties();
        try (InputStream inputStream = Map.class.getResourceAsStream("/application-production.properties")) {
            props.load(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        ColoredPrinter cp;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Ansi.BColor backgroundColor;
                char cell = cells[i][j];
                switch (cell) {
                    case WALL:
                        backgroundColor = Ansi.BColor.valueOf(props.getProperty("wall.color"));
                        break;
                    case ENEMY:
                        backgroundColor = Ansi.BColor.valueOf(props.getProperty("enemy.color"));
                        break;
                    case GOAL:
                        backgroundColor = Ansi.BColor.valueOf(props.getProperty("goal.color"));
                        break;
                    case PLAYER:
                        backgroundColor = Ansi.BColor.valueOf(props.getProperty("player.color"));
                        break;
                    case EMPTY:
                        backgroundColor = Ansi.BColor.valueOf(props.getProperty("empty.color"));
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid cell value: " + cell);
                }
                cp = new ColoredPrinter.Builder(1, false)
                        .foreground(Ansi.FColor.BLACK)
                        .background(backgroundColor)
                        .build();
                cp.print(cell);
                cp.clear();
            }
            System.out.println();
        }
    }

    public void generate(int wallsCount, int enemiesCount) {
        // Generate an empty map
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = EMPTY;
            }
        }

        // Place the player in a random empty cell
        int playerX = random.nextInt(size);
        int playerY = random.nextInt(size);
        cells[playerX][playerY] = PLAYER;

        // Place the goal in a random empty cell
        int goalX = random.nextInt(size);
        int goalY = random.nextInt(size);
        cells[goalX][goalY] = GOAL;

        // Place the obstacles and enemies in random empty cells
        for (int i = 0; i < wallsCount; i++) {
            int obstacleX, obstacleY;
            do {
                obstacleX = random.nextInt(size);
                obstacleY = random.nextInt(size);
            } while (cells[obstacleX][obstacleY] != EMPTY);
            cells[obstacleX][obstacleY] = WALL;
        }

        for (int i = 0; i < enemiesCount; i++) {
            int enemyX, enemyY;
            do {
                enemyX = random.nextInt(size);
                enemyY = random.nextInt(size);
            } while (cells[enemyX][enemyY] != EMPTY);
            cells[enemyX][enemyY] = ENEMY;
        }
    }

}
