package game.model.heroes;

import game.model.Arena;
import game.model.BaseHero;

import java.util.List;

public class Peasant extends KholopHero {
    public Peasant(String namePerson, Arena position) {
        super(namePerson,
                10,
                10,
                1,
                0,
                State.Stand,
                position);
        this.setInitiate(this.getInitiate() + 1);
    }
    public String getInfo(){
        return super.getInfo();
    }
    public String getName(){
        return name;
    }
    public void step(List<BaseHero> ourTeam, List<BaseHero> oppositeTeam) {
        if (State.Dead.equals(this.state)) return;
        if (State.Busy.equals(this.state)) this.state = State.Stand;
        // пришел, отдыхает, ждет дальнейших указаний
    }
}
