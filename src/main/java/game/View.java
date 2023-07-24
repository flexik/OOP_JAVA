package game;

import game.model.*;

import java.util.Comparator;

public class View {
    private static int step;
    private static final int[] maxWidth = {0};
    private static final String top10;
    private static final String middle10;
    private static final String bottom10;
    private static final String nameTeams;
    static {
        Main.teamGood.forEach((v) -> maxWidth[0] = Math.max(maxWidth[0], v.getInfo().length()));  // Самая длиная строка l[0]
        step = 1;
        top10    = String.join("", "┌" + "─┬".repeat(9) + "─┐");
        middle10 = String.join("", "├" + "─┼".repeat(9) + "─┤");
        bottom10 = String.join("", "└" + "─┴".repeat(9) + "─┘");
        nameTeams = Colors.BRIGHT_WHITE + Colors.GREEN_BACKGROUND
                + " * Good Guys * " + Colors.RESET + " ".repeat(maxWidth[0] - 7)
                + Colors.BRIGHT_WHITE + Colors.BLUE_BACKGROUND
                + " * Bad Guys * " + Colors.RESET;
    }

    private static void tabSetter(int cnt, int max){
        int dif = max - cnt + 4;
        System.out.printf((dif > 0) ? " ".repeat(dif) + "-\t" : "-\t");
    }
    private static String getChar(int y, int x){
        String out = " ";
        char letter;
        for (BaseHero human: Main.allTeam) {
            letter = human.toString().charAt(0);                             // first letter of super class name
            if (human.getPosition().x == x && human.getPosition().y == y) {
                if (BaseHero.State.Dead.equals(human.state)) {
                    out = Main.teamGood.contains(human) ?
                            Colors.BRIGHT_YELLOW_ON_GREEN + letter + Colors.RESET :
                            Colors.BRIGHT_YELLOW_ON_BLUE + letter + Colors.RESET;
                } else {
                    out = Main.teamGood.contains(human) ?
                            Colors.GREEN + letter + Colors.RESET : Colors.BLUE + letter + Colors.RESET;
                }
            }
        }
        return "|" + out;
    }
    public static void view() {
        System.out.print(Colors.YELLOW + (step == 1 ? "First step " : "Step " + step) + Colors.RESET);
        step++;
        System.out.println("_ ".repeat(maxWidth[0] + 10));
        System.out.println();
        Main.teamGood.sort(Comparator.comparingInt(o -> o.getInfo().charAt(10))); // Сортировка для вывода по порядку
        Main.teamEvil.sort(Comparator.comparingInt(o -> o.getInfo().charAt(10)));

        for (int i = 1; i < 11; i++) {
            if (i == 1) System.out.println(top10 + "    " + nameTeams);     // Крышка решетки и Названия команд
            for (int j = 1; j < 11; j++) System.out.print(getChar(i, j));     // Клетки поля с инициалами персонажей
            System.out.print("|    ");                                        // Вертикальная линия между рядов
            System.out.print(Main.teamGood.get(i - 1).getInfo());             // Инфо о первой команде
            tabSetter(Main.teamGood.get(i - 1).getInfo().length(), maxWidth[0]); // Табуляция между командами
            System.out.println(Main.teamEvil.get(i - 1).getInfo());           // Инфо о второй команде
            System.out.println((i == 10) ? bottom10: middle10);                 // Дно решетки
        }
    }
}

