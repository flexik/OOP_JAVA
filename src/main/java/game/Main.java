package game;

import game.model.BaseHero;
import game.model.GameMechanic;

import java.util.*;

public class Main {
        public static final int[] countDead = {0};
        public static final List<BaseHero> teamGood = GameMechanic.fillTeam(false);
        public static final List<BaseHero> teamEvil = GameMechanic.fillTeam(true);
        public static final List<BaseHero> allTeam = GameMechanic.mergedTeam(teamGood, teamEvil);
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String makeStep = "";
        while (!makeStep.equals("q")) {
        if (isAlive(teamGood)) {
            System.out.println("Bad wins!");
            break;
        }
        if (isAlive(teamEvil)) {
            System.out.println("Good wins!");
            break;
        }
        GameMechanic.letsBattle(teamGood, teamEvil);
        View.view();
        makeStep = in.nextLine();
        }
    }
    public static boolean isAlive(List<BaseHero> team){
        team.forEach((v) -> countDead[0] += (BaseHero.State.Dead.equals(v.state))? 1:0);
        if (countDead[0] == 10) {
            countDead[0] = 0;
            return true;
        } else {
            countDead[0] = 0;
            return false;
        }
    }
}
