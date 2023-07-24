package game.model.heroes;

import game.model.Arena;
import game.model.BaseHero;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Sorcerer extends MagicianHero {
    public Sorcerer(String namePerson, Arena position) {
        super(namePerson,
                10,
                10,
                10,
                5,
                State.Stand,
                position);
        this.setInitiate(this.getInitiate() + 1);
    }
    @Override
    public String getInfo(){
        return super.getInfo();
    }
    public String getName(){
        return name;
    }
    public void step(List<BaseHero> ourTeam, List<BaseHero> oppositeTeam) {
        if (State.Dead.equals(this.state)) return;
        super.step(ourTeam, oppositeTeam);
        ourTeam.sort((o1, o2) -> {
            if (o1.gethP() - o2.gethP() == 0) {
                return o1.gethP() - o2.gethP() - new Random().nextInt(2);
            }
            return o1.gethP() - o2.gethP();
        });               // получает список своих отсортированный по степени живости
        BaseHero wia = null;
        for (BaseHero itemWia: ourTeam) {                       // ищет самого раненого, но еще живого и лечит его
            if (!State.Dead.equals(itemWia.state)
                    && itemWia.gethP() < itemWia.getMaxHP()
                    && !Objects.equals(itemWia.getName(), getName())){
                wia = itemWia;
                break;
            }
        }
        if (wia == null) {
            getDamage(1);
            return;
        }
        if (wia.gethP() + damage > wia.getMaxHP()) wia.getDamage(wia.getMaxHP() - wia.gethP());
        else wia.getDamage(-damage);
    }
}
