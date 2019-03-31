package io.github.bael.javacourse.knight.warriors;

import io.github.bael.javacourse.knight.chances.Chance;

public abstract class Warrior implements Actor {

    private static final int CRITICAL_DAMAGE_EDGE = 95;
    public static final int CRITICAL_DAMAGE_RATE = 3;
    ActorState state;

    protected Warrior() {
    }

    public Warrior(ActorState state) {
        this.state = state;
    }


    /**
     * Сила атаки = уровень атаки + сила + признак критичности удара.
     */
    @Override
    public void attackEnemy(Chance chance, Warrior warrior) {
        System.out.println("Attacking the enemy! " + warrior);
        int percent = chance.getProbability();
        // to do insert damage calculation
        int damagevalue = 0; //
        Damage damage = new Damage(damagevalue, detectCriticalAttackChance(percent, warrior));
        System.out.println("Attack damage is ! " + damage);
        warrior.receiveAttack(damage);
    }

    // считаем что урон критический если вероятность критического удара + уровень воина выше порога
    protected boolean detectCriticalAttackChance(int percent, Warrior warrior) {
        // todo
        //   возвращать true если процент + уровень больше или равен CRITICAL_DAMAGE_EDGE;
        if (warrior instanceof BlackKnight) return false;
        else if (percent + state.getLevel() >= CRITICAL_DAMAGE_EDGE) return true;
        else return false;
    }


    /***
     * Принимаемый урон от атаки = уровень атаки * (мультипликатор критичности удара) - сила защиты.
     */
    @Override
    public void receiveAttack(Damage strike) {
        System.out.println("Recieveing the attack: " + strike);


        // todo - поглощенный урон = защита - урон
        int damage = strike.getValue() - state.getDefenceLevel() ;


        // если удар  критический - утраиваем урон
        if (strike.getIsCritical()) {
            // todo
            damage *= CRITICAL_DAMAGE_RATE;
        }

        // todo нужно уменьшить здоровье юнит. для этого в интерфейсе ActorState есть метод takeDamage. Примените его
        this.state = this.state.takeDamage(damage);
    }

    @Override
    public ActorState getState() {
        return state;
    }


}
