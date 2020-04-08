package com.company.model.gladiators;

import com.company.Utils;

public class Archer extends BaseGladiator {
    public Archer(String name) {
        super(name);
    }

    public Archer(int lvl, int hp, int sp, int dex, String name) {
        super(lvl, hp, sp, dex, name);
    }

    @Override
    protected Utils.Multiplier hpMultiplier() {
        return Utils.Multiplier.Medium;
    }

    @Override
    protected Utils.Multiplier spMultiplier() {
        return Utils.Multiplier.Medium;

    }

    @Override
    protected Utils.Multiplier dexMultiplier() {
        return Utils.Multiplier.High;
    }

    @Override
    public String className() {
        return "Archer";
    }
}
