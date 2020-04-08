package com.company.model.gladiators;

import com.company.Utils;

public class Brutal extends BaseGladiator {
    public Brutal(String name) {
        super(name);
    }

    public Brutal(int lvl, int hp, int sp, int dex, String name) {
        super(lvl, hp, sp, dex, name);
    }

    @Override
    protected Utils.Multiplier hpMultiplier() {
        return Utils.Multiplier.High;
    }

    @Override
    protected Utils.Multiplier spMultiplier() {
        return Utils.Multiplier.High;

    }

    @Override
    protected Utils.Multiplier dexMultiplier() {
        return Utils.Multiplier.Low;
    }

    @Override
    public String className() {
        return "Brutal";
    }
}
