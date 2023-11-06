import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import chaseLogic.Enemy;
import chaseLogic.Player;

import com.beust.jcommander.JCommander;
import game.Map;

import java.util.Scanner;

@Parameters(separators = "=")
public class Main {
    @Parameter(names = "--size", required = true)
    int size;

    @Parameter(names = "--wallsCount", required = true)
    int walls;

    @Parameter(names = "--enemiesCount", required = true)
    int enemies;

    @Parameter(names = "--profile", required = true)
    String profile;

    public static void main(String[] args) {

        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);

        if ((main.walls + main.enemies + 2) > main.size * main.size) {
            throw new IllegalArgumentException("Field size is too small for enemies and walls");
        }
        Map map = new Map(main.size);
        map.generate(main.walls, main.enemies);
        map.print();
        if (!map.getPlayer().canMovePlayer(map)) {
            System.err.println("No way to move");
            System.exit(-1);
        }
        ;
        Scanner scanner = new Scanner(System.in);
        if (main.profile.equals("production")) {
            // код для режима production
            while (true) {
                String input = scanner.nextLine();
                if ("exit".equals(input)) {
                    break;
                }
                switch (input) {
                    case "w":
                        map.getPlayer().movePlayer(Map.Direction.LEFT, map);
                        break;
                    case "s":
                        map.getPlayer().movePlayer(Map.Direction.RIGHT, map);
                        break;
                    case "a":
                        map.getPlayer().movePlayer(Map.Direction.UP, map);
                        break;
                    case "d":
                        map.getPlayer().movePlayer(Map.Direction.DOWN, map);
                        break;
                    case "9":
                        System.exit(-1);
                    default:
                        System.out.println("Invalid input");
                        break;
                }
                map.getEnemy().moveEnemy(map);
                map.print();
            }
        } else if (main.profile.equals("develop")) {
            // код для режима develop
            while (true) {
                String input = scanner.nextLine();
                if ("exit".equals(input)) {
                    break;
                }
                switch (input) {
                    case "w":
                        map.getPlayer().movePlayer(Map.Direction.LEFT, map);
                        break;
                    case "s":
                        map.getPlayer().movePlayer(Map.Direction.RIGHT, map);
                        break;
                    case "a":
                        map.getPlayer().movePlayer(Map.Direction.UP, map);
                        break;
                    case "d":
                        map.getPlayer().movePlayer(Map.Direction.DOWN, map);
                        break;
                    case "8":
                        map.getEnemy().moveEnemy(map);
                        break;
                    case "9":
                        System.exit(-1);
                    default:
                        System.out.println("Invalid input");
                        break;
                }
                map.print();
            }
        } else {
            System.out.println("Invalid profile");
            System.exit(-1);
        }
    }

}