package game.model;

import java.util.List;

public abstract class BaseHero implements GameInterface {
    protected String name;
    protected int hP;
    protected int maxHP;
    protected int defense;
    protected int damage;
    private int initiate;
    public State state;
    protected Arena position;
    public BaseHero(String name,
                    int hP,
                    int maxHP,
                    int defense,
                    int damage,
                    int initiate,
                    State state,
                    Arena position) {
        this.name = name;
        this.hP = hP;
        this.maxHP = maxHP;
        this.defense = defense;
        this.damage = damage;
        this.setInitiate(initiate);
        this.state = state;
        this.position = position;
    }
    public String getName() {
        return name;
    }

    public int gethP() {
        return hP;
    }

    public String getInfo(){
        return String.format("N: %s %s ♡: %.2s ♤: %s ⛨: %d ин:%d",
                this.name,
                this.getClass().getSuperclass().getSimpleName().replace("Hero", ""),
                state.equals(State.Dead)? "✟": this.hP,
                this.damage,
                this.defense,
                getInitiate()
        );
    }

    public int getMaxHP() {
        return maxHP;
    }

    public Arena getPosition() {
        return this.position;
    }
    public void setPosition(Arena position) {
        this.position = position;
    }
    public enum State{
            Stand, Busy, Dead
    }
    public void getDamage(int damage){
        this.hP -= damage;
        if (this.hP <= 0) {
            this.hP = 0;
            this.state = State.Dead;
        }
    }

    public int getInitiate() {
        return initiate;
    }
    public void setInitiate(int initiate) {
        this.initiate = initiate;
    }
    public String toString(){
        return (String) String.valueOf(getClass().getSuperclass()).subSequence(24, 25);
//        return String.valueOf(getName().charAt(7));
    }
    public BaseHero lookForEnemy(List<BaseHero> oppositeTeam) {
        oppositeTeam.sort((o1, o2) -> (int) (Arena.distance(o1.getPosition(), getPosition()) - Arena.distance(o2.getPosition(), getPosition())));
        for (BaseHero enemyItem: oppositeTeam) {
            if (!BaseHero.State.Dead.equals(enemyItem.state)) {
                return enemyItem;
            }
        }
        return null;
    }
}
