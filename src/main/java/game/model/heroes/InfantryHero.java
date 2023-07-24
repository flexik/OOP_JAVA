package game.model.heroes;

import game.model.Arena;
import game.model.BaseHero;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Герой этого класса должны ходить по полю
 */
public class InfantryHero extends BaseHero {
    public InfantryHero(String namePerson,
                        int hP,
                        int maxHP,
                        int defense,
                        int damage,
                        State state,
                        Arena position) {
        super(namePerson,
                hP,
                maxHP,
                defense,
                damage,
                15,
                state,
                position);
    }
    public String getName(){
        return name;
    }

    public String getInfo(){
        return super.getInfo();
    }

    @Override
    public void step(List<BaseHero> ourTeam, List<BaseHero> oppositeTeam) {

    }


}
