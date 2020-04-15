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
    // TODO variable names 'a' and 'b' are not explanatory enough
    // TODO these should be private
    public BaseGladiator a;
    public BaseGladiator b;
    // TODO these should be private
    public int healthA;
    public int healthB;

    public Combat(BaseGladiator a, BaseGladiator b) {
        this.a = a;
        this.b = b;

        healthA = a.getHp();
        healthB = b.getHp();

        turnA = true;
    }
// FIXME this method should simulate the combat and return the winner.
//  It should not comment on the winner (use view) or do any other things.
    /**
     * Simulates the combat and returns
     *
     * @return winner of combat
     */
    public BaseGladiator Simulate() {
        //FIXME Accessing the view should not be done from the model.
        // This could maybe be a toString() method, which would be called by the controller and then
        // sent to the view?
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
        // NIT loser spelled with one 'o'
        var looser = healthA <= 0 ? a : b;
        // TODO don't call view methods from the model. maybe make this into a method
        //  that returns a String?
        if (Main.view != null) {
            Main.view.display(String.format(
                    "\n%s %s has died, %s %s wins!", looser.className(), looser.getName(), victor.className(), victor.getName()));
        }
        // Return the victor of this combat
        return victor;
    }
// TODO This method doesn't only simulate the turn of a combat,
//  but also dictates the rules of this turn (like who is attacking and who is defending).
//  Separate into methods: make one that receives two gladiators and simulates the fight and another
//  that changes turns and calls it again.
    /**
     * Simulates a single turn of combat
     *
     * @return true if the fight is over
     */
    private boolean SimulateTurn() {
        var current = turnA ? a : b;
        var enemy = turnA ? b : a;

        var chancePercentage = CalculateHitChance(current, enemy);
        var randomChance = Utils.random.nextInt(101); // TODO magic number -> comment

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
        int unclapmedChance = currentDex - enemyDex; // NIT what is unclapmed?

        return Math.max(10, Math.min(unclapmedChance, 100)); // NIT fix magic numbers -> comment
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
