package chaseLogic;
import java.util.ArrayList;

import game.Map;

import java.awt.Point;

public class Enemy {
    

    
    public void addEnemy(int count, Map map) {
        for (int i = 0; i < count; i++) {
            int x = map.getRandom().nextInt(map.getSize());
            int y = map.getRandom().nextInt(map.getSize());
            map.setCell(x, y, Map.ENEMY);
        }
    }

    public void logicEnemy(int[] enemy, Map map) {
        int[] player = map.getPlayer().findPlayer(map);
        int x = enemy[0];
        int y = enemy[1];
        int newX = x;
        int newY = y;

        if (player[0] > x && canMoveEnemy(x, y, map, Map.Direction.RIGHT)) {
            newX++;
        } else if ((player[0] < x) && canMoveEnemy(x, y, map, Map.Direction.LEFT)) {
            newX--;
        } else if (player[1] > y && canMoveEnemy(x, y, map, Map.Direction.DOWN)) {
            newY++;
        } else if ((player[1] < y) && canMoveEnemy(x, y, map, Map.Direction.UP)) {
            newY--;
        }

        if (newX == player[0] && newY == player[1]) {
            map.setCell(newX, newY, Map.ENEMY);
            map.setCell(x, y, Map.EMPTY);
            map.print();
            System.err.println("You loose!");
            System.exit(-1);
        }
        // перемещаем enemy на следующую ячейку
        map.setCell(x, y, Map.EMPTY);
        map.setCell(newX, newY, Map.ENEMY);
    }

    public int[] findEnemy(Map map) {
        for (int i = 0; i < map.getSize(); i++) {
            for (int j = 0; j < map.getSize(); j++) {
                if (map.getCells()[i][j] == Map.ENEMY) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalStateException("Enemy not found");
    }

    public boolean canMoveEnemy(int x, int y, Map map, Map.Direction direction) {
        int newX = x;
        int newY = y;

        switch (direction) {
            case UP:
                newY = y - 1;
                break;
            case DOWN:
                newY = y + 1;
                break;
            case LEFT:
                newX = x - 1;
                break;
            case RIGHT:
                newX = x + 1;
                break;
        }

        if (newX < 0 || newX >= map.getSize() || newY < 0 || newY >= map.getSize()) {
            return false; // враг не может выйти за пределы карты
        }

        char nextCell = map.getCells()[newX][newY];
        if (nextCell == Map.WALL || nextCell == Map.ENEMY || nextCell == Map.GOAL) {
            return false; // враг не может двигаться через стены или других врагов 
        }

        return true; // перемещение врага возможно
    }

    public void moveEnemy(Map map) {
        ArrayList<Point> coordinates = new ArrayList<Point>();
        for (int i = 0; i < map.getSize(); i++) {
            for (int j = 0; j < map.getSize(); j++) {
                if (map.getCells()[i][j] == Map.ENEMY) {
                    coordinates.add(new Point(i, j));
                }
            }
        }

        for (Point p : coordinates) {
            int x = p.x;
            int y = p.y;

            if (canMoveEnemy(x, y, map, Map.Direction.UP)) {
                logicEnemy(new int[] { x, y }, map);
            } else if (canMoveEnemy(x, y, map, Map.Direction.DOWN)) {
                logicEnemy(new int[] { x, y }, map);
            } else if (canMoveEnemy(x, y, map, Map.Direction.LEFT)) {
                logicEnemy(new int[] { x, y }, map);
            } else if (canMoveEnemy(x, y, map, Map.Direction.RIGHT)) {
                logicEnemy(new int[] { x, y }, map);
            } 
        }
    }

}