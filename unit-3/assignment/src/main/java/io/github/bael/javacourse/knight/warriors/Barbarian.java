package io.github.bael.javacourse.knight.warriors;

import io.github.bael.javacourse.knight.chances.Chance;

/**
 * Варвар. при низком здоровье (20 %) сила умножается на 5.
 */
public class Barbarian extends Warrior {

    private static final double RAGE_HEALTH_EDGE = .2d;

    private Barbarian() {
    }

    public Barbarian(ActorState state) {
        super(state);
    }

    /**
     * При пониженном здоровье урон от силы в пять раз выше
     */
    @Override
    public void attackEnemy(Chance chance, Warrior warrior) {
        System.out.println("Attacking the enemy!");

        boolean isCritical = detectCriticalAttackChance(chance.getProbability(), warrior);
        System.out.println("Barbarian's health = " + state.getHP() + ". Barbarian's maxHP = " + state.getMaxHP());

        int multiDamage = 1;
        if (state.getHP() <= state.getMaxHP()*0.2) multiDamage = 5;

        Damage damage = new Damage(state.getAttackLevel() + multiDamage*state.getStrength(), isCritical);

        warrior.receiveAttack(damage);
        System.out.println("Barbarian's damage = " + damage);
    }

}
