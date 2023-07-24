package game.model.heroes;

import game.model.Arena;
import game.model.BaseHero;

import java.util.List;
public class Archer extends ShooterHero {
    public Archer (String namePerson, Arena position) {
        super(namePerson,
                10,
                10,
                10,
                2,
                State.Stand,
                position,
                3);
        this.setInitiate(this.getInitiate() + 1);
    }
    public String getName(){
        return name;
    }
    public void step(List<BaseHero> ourTeam, List<BaseHero> oppositeTeam) {
        if (State.Dead.equals(this.state)) return;
        if (this.amountArrows < 1) {
            this.getDamage(1);
            return;
        }
        BaseHero victim = lookForEnemy(oppositeTeam);
        if (victim == null) return;
        victim.getDamage(damage);
        for (BaseHero item: ourTeam) {
            if (item instanceof Peasant && State.Stand.equals(item.state)) {  // Ищет в команде свободного крестьянина
                item.state = State.Busy;                                      // и поручает ему дело - принести стрелу
                return;
            }
        }
        this.amountArrows--;                                                  // количество стрел уменьшается, если крестьянина под рукой нет
    }
    @Override
    public String getInfo(){
        return super.getInfo();
    }
}

