package testesokoban2;

import java.awt.Point;
import java.util.Scanner;

/**
 * http://www.sourcecode.se/sokoban/levels -> incrível
 * 
 * @author leonardo
 */
public class Model {

    private String level = ""
            + "########\n"
            + "###   ##\n"
            + "#.H O ##\n"
            + "### O.##\n"
            + "#.##O ##\n"
            + "# # . ##\n"
            + "#OO.OO.#\n"
            + "#   .  #\n"
            + "########";
    
    private char[][][] map = new char[2][8][9];
    private Point p = new Point();

    public Model() {
        init();
    }

    private void init() {
        String[] lines = level.split("\\n");
        int y = 0;
        for (String line : lines) {
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                map[c / 70][x][y] = c;
                map[1 - c / 70][x][y] = ' ';
                if (c == 'H') {
                    p.x = x;
                    p.y = y;
                }
            }
            y++;
        }
        map[1][p.x][p.y] = ' ';
    }

    // 37-esq 38-cima 39-dir 40-baixo
    public void move(int d) {
        int dx = d == 37 ? -1 : d == 39 ? 1 : 0;
        int dy = d == 38 ? -1 : d == 40 ? 1 : 0;
        if (map[1][p.x + dx][p.y + dy] == 'O' 
                && map[0][p.x + 2 * dx][p.y + 2 * dy]!='#' 
                && map[1][p.x + 2 * dx][p.y + 2 * dy]==' ') {
            map[1][p.x + dx][p.y + dy] = ' ';
            map[1][p.x + 2 * dx][p.y + 2 * dy] = 'O'; 
        }
        if (map[1][p.x + dx][p.y + dy] != 'O'
                && map[0][p.x + dx][p.y + dy] != '#') {
            p.x += dx;
            p.y += dy;
        } 
    }

    public boolean isCompleted() {
        for (int y = 0; y < map[0][0].length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[1][x][y]=='O' && map[0][x][y]!='.') {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String out = "";
        for (int y = 0; y < map[0][0].length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                out += p.x == x && p.y == y ? 'H' 
                    : map[1][x][y] != ' ' ? map[1][x][y] : map[0][x][y];
            }
            out += "\n";
        }
        return out;
    }

    public static void main(String[] args) {
        Model model = new Model();
        System.out.println(model.toString());
        Scanner sc = new Scanner(System.in);
        while (true) {
            int d = sc.nextInt();
            model.move(d);
            System.out.println(model.toString());
        }
    }
    
}
