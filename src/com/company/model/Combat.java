package com.company.model;

import com.company.Main;
import com.company.Utils;
import com.company.model.gladiators.BaseGladiator;

/**
 * Combat class, used for simulating fights between pairs of gladiators
 */
public class Combat {

    // Note: All usages of View are optional (the simulation can work without initializing View module), hence the null propagation

    private boolean turnA;

    public BaseGladiator a;
    public BaseGladiator b;

    public int healthA;
    public int healthB;

    public Combat(BaseGladiator a, BaseGladiator b) {
        this.a = a;
        this.b = b;

        healthA = a.getHp();
        healthB = b.getHp();

        turnA = true;
    }

    /**
     * Simulates the combat and returns
     *
     * @return winner of combat
     */
    public BaseGladiator Simulate() {
        if (Main.view != null) {
            Main.view.display(String.format("\nDuel %s versus %s", a.getName(), b.getName()));
            Main.view.display(String.format("%s %s - %s HP, %s SP, %s DEX,  %s LVL",
                    a.className(), a.getName(), a.getHp(), a.getSp(), a.getDex(), a.getLevel()));
            Main.view.display(String.format("%s %s - %s HP, %s SP, %s DEX,  %s LVL",
                    b.className(), b.getName(), b.getHp(), b.getSp(), b.getDex(), b.getLevel()));
            Main.view.display("\nBegin!");
        }
        boolean ongoingCombat = true;

        while (ongoingCombat) ongoingCombat = !SimulateTurn();

        var victor = healthA <= 0 ? b : a;
        var looser = healthA <= 0 ? a : b;

        if (Main.view != null) {
            Main.view.display(String.format(
                    "\n%s %s has died, %s %s wins!", looser.className(), looser.getName(), victor.className(), victor.getName()));
        }
        // Return the victor of this combat
        return victor;
    }

    /**
     * Simulates a single turn of combat
     *
     * @return true if the fight is over
     */
    private boolean SimulateTurn() {
        var current = turnA ? a : b;
        var enemy = turnA ? b : a;

        var chancePercentage = CalculateHitChance(current, enemy);
        var randomChance = Utils.random.nextInt(101);

        if (randomChance <= chancePercentage) {
            // Hit
            var dmg = CalculateDamage(current);

            if (turnA)
                healthB -= dmg;
            else
                healthA -= dmg;

            if (Main.view != null)
                Main.view.display(String.format("%s deals %s damage.", current.getName(), dmg));
        } else {
            // Miss
            if (Main.view != null)
                Main.view.display(String.format("%s missed.", current.getName()));
        }

        turnA = !turnA;

        // Return boolean value whether someone has died
        return healthA <= 0 || healthB <= 0;
    }

    /**
     * Calculates a percentage chance of hitting the enemy
     *
     * @param current
     * @param enemy
     * @return
     */
    public int CalculateHitChance(BaseGladiator current, BaseGladiator enemy) {
        int currentDex = current.getDex();
        int enemyDex = enemy.getDex();
        int unclapmedChance = currentDex - enemyDex;

        return Math.max(10, Math.min(unclapmedChance, 100));
    }

    /// <summary>
    ///     Calculates the damage for an attack
    /// </summary>
    /// <param name="current"></param>
    /// <returns></returns>
    public int CalculateDamage(BaseGladiator current) {
        return Utils.random.nextInt(((Constants.MaxDamageMultiplier - Constants.MinDamageMultiplier) + 1) +
                Constants.MinDamageMultiplier) * current.getSp() / Constants.DamageDivisor;
    }
}
