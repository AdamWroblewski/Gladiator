package com.company.model.gladiators;

import com.company.Utils;
import com.company.model.Constants;

public abstract class BaseGladiator {
    /**
     * Gladiator's name
     */
    private String name;
    /**
     * Basic amount of this Gladiator's Dexterity Points
     */
    private int baseDex;
    /**
     * Basic amount of this Gladiator's Health Points
     */
    private int baseHp;
    /**
     * Basic amount of this Gladiator's Strength Points
     */
    private int baseSp;
    /**
     * Gladiator's level
     */
    private int level;

    /**
     * Default Gladiator constructor
     *
     * @param name
     */
    public BaseGladiator(String name) {
        // Assign starter statistics
        level = Utils.random.nextInt((Constants.MaxStartLevel - Constants.MinStartLevel) + 1) + Constants.MinStartLevel;
        baseHp = Utils.random.nextInt((Constants.MaxStartHp - Constants.MinStartHp) + 1) + Constants.MinStartHp;
        baseSp = Utils.random.nextInt((Constants.MaxStartSp - Constants.MinStartSp) + 1) + Constants.MinStartSp;
        baseDex = Utils.random.nextInt((Constants.MaxStartDex - Constants.MinStartDex) + 1) + Constants.MinStartDex;

        // Assign name
        this.name = name;
    }

    /**
     * Gladiator constructor with parameters, for testing purposes
     *
     * @param lvl
     * @param hp
     * @param sp
     * @param dex
     * @param name
     */
    public BaseGladiator(int lvl, int hp, int sp, int dex, String name) {
        level = lvl;
        baseHp = hp;
        baseSp = sp;
        baseDex = dex;
        this.name = name;
    }

    /**
     * @return HP multiplier of this gladiator class
     */
    protected abstract Utils.Multiplier hpMultiplier();

    /**
     * @return SP multiplier of this gladiator class
     */
    protected abstract Utils.Multiplier spMultiplier();

    /**
     * @return DEX multiplier of this gladiator class
     */
    protected abstract Utils.Multiplier dexMultiplier();

    /**
     * @return This gladiator class name
     */
    public abstract String className();

    /**
     * Invoked after winning a fight, increments the gladiator's level
     */
    public void LevelUp() {
        level++;
    }

    /**
     * @return Amount of Health Points of this Gladiator
     */
    public int getHp() {
        return (int) (baseHp * level * Utils.getValue(hpMultiplier()));
    }

    /**
     * @return Amount of Strength Points of this Gladiator
     */
    public int getSp() {
        return (int) (baseSp * level * Utils.getValue(spMultiplier()));
    }

    /**
     * @return Amount of Dexterity Points of this Gladiator
     */
    public int getDex() {
        return (int) (baseDex * level * Utils.getValue(dexMultiplier()));
    }

    /**
     * @return Gladiator's level
     */
    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
