package io.github.bael.javacourse.knight.warriors;

import io.github.bael.javacourse.knight.chances.Chance;

/**
 * Рыцарь. наносит урон черному рыцарю с удвоенной силой.
 */
public class Knight extends Warrior {

    private Knight() {
    }

    public Knight(ActorState state) {
        super(state);
    }


    /***
     * Удваиваем урон если противник черный рыцарь.
     * @param chance шанс критического удара
     * @param blackKnight тип противника - черный рыцарь
     */
    public void attackEnemy(Chance chance, BlackKnight blackKnight) {

            System.out.println("Attacking the blackKnight with double damage!");

            boolean isCritical = detectCriticalAttackChance(chance.getProbability(), blackKnight);

            Damage damage = new Damage(state.getAttackLevel() + 2 * state.getStrength(), isCritical);

            blackKnight.receiveAttack(damage);


    }

    public void attackEnemy(Chance chance, Knight whiteknight) {
        System.out.println("Attacking the other knight!");

        boolean isCritical = detectCriticalAttackChance(chance.getProbability(), whiteknight);

        Damage damage = new Damage(state.getAttackLevel() + state.getStrength(), isCritical);

        whiteknight.receiveAttack(damage);

    }
}
