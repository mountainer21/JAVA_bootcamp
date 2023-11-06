package chaseLogic;

import game.Map;
import game.Map.Direction;

public class Player {
    public void addPlayer(Map map) {
        int x = map.getRandom().nextInt(map.getSize());
        int y = map.getRandom().nextInt(map.getSize());
        map.setCell(x, y, 'o');
    }

    public int[] findPlayer(Map map) {
        for (int i = 0; i < map.getSize(); i++) {
            for (int j = 0; j < map.getSize(); j++) {
                if (map.getCells()[i][j] == Map.PLAYER) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalStateException("fp; Player not found");
    }

    public void movePlayer(Direction direction, Map map) {
        int[] playerPosition = map.getPlayer().findPlayer(map);
        int x = playerPosition[0];
        int y = playerPosition[1];
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
            return; // игрок не может выйти за пределы карты
        }
        // char currentCell = map.getCells()[x][y];
        char nextCell = map.getCells()[newX][newY];
        if (nextCell == Map.WALL) {
            return; // игрок не может двигаться через стены
        }
        if (nextCell == Map.ENEMY) {
            // игрок погибает, если наступает на врага
            map.setCell(x, y, Map.EMPTY); 
            map.setCell(newX, newY, Map.PLAYER); 
            System.err.println("Game over!");
            System.exit(-1);
        }
        if (nextCell == Map.GOAL) {
            // игрок побеждает, если достигает цели
            map.setCell(x, y, Map.EMPTY); 
            map.setCell(newX, newY, Map.PLAYER); 
            map.print();
            System.err.println("You win!");
            System.exit(-1);
        }
        // перемещаем игрока на следующую ячейку
        map.setCell(x, y, Map.EMPTY); 
        map.setCell(newX, newY, Map.PLAYER); 
    }

    public boolean canMovePlayer(Map map) {
        int[] playerPosition = map.getPlayer().findPlayer(map);
        int x = playerPosition[0];
        int y = playerPosition[1];
        if (x > 0 && map.getCells()[x - 1][y] != Map.WALL && map.getCells()[x - 1][y] != Map.ENEMY) {
            return true;
        }
        if (x < map.getSize() - 1 && map.getCells()[x + 1][y] != Map.WALL && map.getCells()[x + 1][y] != Map.ENEMY) {
            return true;
        }
        if (y > 0 && map.getCells()[x][y - 1] != Map.WALL && map.getCells()[x][y - 1] != Map.ENEMY) {
            return true;
        }
        if (y < map.getSize() - 1 && map.getCells()[x][y + 1] != Map.WALL && map.getCells()[x][y + 1] != Map.ENEMY) {
            return true;
        }
        return false;
    }

}
