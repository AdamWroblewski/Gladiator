package com.company.model.gladiators;

import com.company.Utils;

public class Swordsman extends BaseGladiator {

    public Swordsman(String name) {
        super(name);
    }

    public Swordsman(int lvl, int hp, int sp, int dex, String name) {
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
        return Utils.Multiplier.Medium;
    }

    @Override
    public String className() {
        return "Swordsman";
    }
}
