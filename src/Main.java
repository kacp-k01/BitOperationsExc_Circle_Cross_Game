import java.util.Map;
import java.util.Scanner;

import static java.util.Map.entry;

public class Main {
    public static void main(String[] args) {

        System.out.println("Start!");
        Scanner scan = new Scanner(System.in);
        int field, player = 0, figure = 0b01, board = 0b000000000000000000;

        Map<Integer, Integer> winningCombinations = Map.ofEntries(
                entry(0, 0b000000000000010101),
                entry(1, 0b000000000000010101),
                entry(2, 0b010101000000000000),
                entry(3, 0b000001000001000001),
                entry(4, 0b000100000100000100),
                entry(5, 0b010000010000010000),
                entry(6, 0b010000000100000001),
                entry(7, 0b000001000100010000),
                entry(8, 0b000000000000101010),
                entry(9, 0b000000101010000000),
                entry(10, 0b101010000000000000),
                entry(11, 0b000010000010000010),
                entry(12, 0b001000001000001000),
                entry(13, 0b100000100000100000),
                entry(14, 0b100000001000000010),
                entry(15, 0b000010001000100000)
        );
        String line = "\u2500" + "\u2500" + "\u2500" + "\u2500" + "\u2500" + "\u2500" +
                "\u2500" + "\u2500" + "\u2500" + "\u2500" + "\u2500" + "\u2500" +
                "\u2500" + "\u2500" + "\u2500" + "\u2500" + "\u2500" + "\u2500" + "\u2500" + "\u2500" + "\u2500" + "\u2500" + "\u2500" + "\u2500";

        while (true) {
            player = changePlayer(player);
            drawBoard(line, board);
            field = playerMove(scan, player, board);
            board = (figure << (2 * (field - 1))) | board;
            if (checkWin(board, player, winningCombinations)) {
                drawBoard(line, board);
                break;
            }
            figure = changeFigure(player);
        }
    }
    static boolean checkWin(int board, int player, Map<Integer, Integer> winningCombinations) {
        for (int i = 0; i < 16; i++) {
            if ((board & winningCombinations.get(i)) == winningCombinations.get(i)) {
                System.out.println("Win - Player nr: " + player);
                return true;
            }
        }
        return false;
    }
    static int changePlayer(int player) {
        if (player == 1) {
            return 2;
        } else {
            return 1;
        }
    }
    static int changeFigure(int player) {
        if (player == 1) {
            return 0b10;
        } else {
            return 0b01;
        }
    }
    static void drawBoard(String line, int board) {
        int row = 0;

        for (int i = 0; i < 3; i++) {
            if (i == 1) System.out.println(line);
            for (int j = 0; j < 3; j++) {
                if (j == 1) System.out.print("\u257F");
                int boardField = ((3 << (row * 2)) & board) >> (row * 2);

                System.out.print(switch (boardField) {
                    case 0 -> "\t  \t";
                    case 1 -> "\tx\t";
                    case 2 -> "\to\t";
                    default -> "";
                });

                row++;
                if (j == 1) System.out.print("\u257F");
            }
            System.out.println();
            if (i == 1) System.out.println(line);
        }
    }
    static int playerMove(Scanner scan, int player, int board) {
        System.out.println("Player: " + player);
        System.out.print("Write number of field (Top left - 1, top right 3, bottom right - 9): ");
        int field = Integer.parseInt(scan.next());
        if ((!(field >= 0 && field <= 9)) || ((board >> (2 * (field - 1))) & 3) != 0) {
            while ((!(field >= 0 && field <= 9)) || ((board >> (2 * (field - 1))) & 3) != 0) {
                if ((!(field >= 0 && field <= 9))) {
                    System.err.println("Number too large - write the correct number");
                    field = Integer.parseInt(scan.next());
                }
                if (((board >> (2 * (field - 1))) & 3) != 0) {
                    System.err.println("Field taken - write the correct number");
                    field = Integer.parseInt(scan.next());
                }
            }
        }
        return field;
    }
}

