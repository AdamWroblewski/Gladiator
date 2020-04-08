package com.company.controller;

import com.company.Main;
import com.company.Utils;
import com.company.model.BinaryTree;
import com.company.model.Combat;
import com.company.model.Constants;
import com.company.model.NamesGenerator;
import com.company.model.gladiators.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Tournament {
    /**
     * Initializes the Tournament simulation
     */
    public void initialize() {
        // Just a safety protocol
        if (Main.view == null)
            throw new NullPointerException(
                    "Missing View module. Be sure to initialize it before starting the Tournament");

        Main.view.display(
                "Ave and welcome to the Colosseum! How many stages of the Tournament do you wish to watch?");

        // Get the stages amount
        int stages = Main.view.getNumber(Constants.MinTournamentStages, Constants.MaxTournamentStages);
        int gladiatorsAmount = (int) Math.pow(2, stages);

        // Generate names
        String[] names = NamesGenerator.getNames(gladiatorsAmount);

        // Generate gladiators and put them into pairs
        var gladiators = new Stack<BaseGladiator>();
        Arrays.asList(names).
                stream().forEach(x -> gladiators.push(generateGladiator(x)));
        var pairs = new ArrayList<Combat>();

        Main.view.display("\nWe have selected Rome's finest warriors for today's Tournament!");

        while (gladiators.size() > 0) {
            // Since the amount of gladiator pairs is a power of two, we can safely pop 2 elements at a time
            var combatantA = gladiators.pop();
            var combatantB = gladiators.pop();

            Main.view.display(String.format("%s %s - %s HP, %s SP, %s DEX, %s LVL", combatantA.className(), combatantA.getName(),
                    combatantA.getHp(), combatantA.getSp(), combatantA.getDex(), combatantA.getLevel()));
            Main.view.display(String.format("%s %s - %s HP, %s SP, %s DEX, %s LVL", combatantB.className(), combatantB.getName(),
                    combatantB.getHp(), combatantB.getSp(), combatantB.getDex(), combatantB.getLevel()));

            pairs.add(new Combat(combatantA, combatantB));
        }

        // Construct the Binary Tree
        var root = new BinaryTree<Combat>();
        root.AddRange(pairs);

        // Simulate the combat
        Main.view.display("\nAve Imperator, morituri te salutant!");
        BaseGladiator champion = SimulateCombat(root);

        // Announce the champion
        Main.view.display(String.format("\nThe Tournament's champion is %s %s", champion.className(), champion.getName()));
    }

    private static BaseGladiator generateGladiator(String name) {
        var chance = Utils.random.nextInt(5) + 1;

        switch (chance) {
            case 1:
                return new Archer(name);
            case 2:
                return new Assassin(name);
            case 3:
                return new Brutal(name);
            default:
                return new Swordsman(name);
        }
    }

    private static BaseGladiator SimulateCombat(BinaryTree<Combat> tree) {
        BaseGladiator victor;

        // Check if branches already exist
        if (tree.LeftBranch != null && tree.RightBranch != null) {
            // Simulate combat on branches first
            var leftVictor = SimulateCombat(tree.LeftBranch);
            var rightVictor = SimulateCombat(tree.RightBranch);

            var combat = new Combat(leftVictor, rightVictor);

            // Simulate this combat
            victor = combat.Simulate();
        } else {
            // Simulate this combat
            victor = tree.Value.Simulate();
        }

        // Level-up the victor
        victor.LevelUp();

        return victor;
    }
}