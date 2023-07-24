package game.model;

import game.model.heroes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameMechanic {
    public static List<BaseHero> fillTeam(boolean side){
        int commandSize = 10;
        List<BaseHero> team = new ArrayList<>();
        for (int i = 0; i < commandSize; i++) {
            int coordX = 1, coordY = i + 1;
            if (side) coordX = 10;
            String prefix = ((side)?"B":"A") + i;
            int cnt = new Random().nextInt(8);
            switch (cnt) {
                case 0 -> team.add(new Peasant("Батрак" + prefix, new Arena(coordX, coordY)));
                case 1 -> team.add(new Sorcerer("Колдуй" + prefix, new Arena(coordX, coordY)));
                case 2 -> team.add(new Monk("Монахъ" + prefix, new Arena(coordX, coordY)));
                case 3 -> team.add(new Robber("Разбой" + prefix, new Arena(coordX, coordY)));
                case 4 -> team.add(new Crossbowman("Арбале" + prefix, new Arena(coordX, coordY)));
                case 5 -> team.add(new Archer("Лучник" +  prefix, new Arena(coordX, coordY)));
                default -> team.add(new Spearman("Копейщ" + prefix, new Arena(coordX, coordY)));
            }
        }
        return team;
    }
    public static List<BaseHero> mergedTeam(List<BaseHero> team1, List<BaseHero> team2){
        List<BaseHero> mergedTeam = new ArrayList<>();
        mergedTeam.addAll(team1);
        mergedTeam.addAll(team2);
        return mergedTeam;
    }
    public static void letsBattle(List<BaseHero> team1, List<BaseHero> team2){
        List<BaseHero> allTeam = mergedTeam(team1, team2);
        allTeam.sort((o1, o2) -> {
            if (o2.getInitiate() - o1.getInitiate() == 0) {
                return o2.getInitiate() - o1.getInitiate() - new Random().nextInt(2);
            }
            return o2.getInitiate() - o1.getInitiate();
        });
        for (BaseHero baseHero : allTeam) {
            if (team1.contains(baseHero)) {
                baseHero.step(team1, team2);
            } else {
                baseHero.step(team2, team1);
            }
        }
    }
}
