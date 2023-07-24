package game.model.heroes;

import game.model.Arena;
import game.model.BaseHero;

import java.util.List;
public class KholopHero extends BaseHero {
    public KholopHero(String namePerson,
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
                0,
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
