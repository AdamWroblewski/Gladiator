package com.company.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NamesGenerator {
    private static Random random = new Random();

    public static String getName() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of("Names.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines.get(random.nextInt(lines.size()));
    }

    public static String[] getNames(int amount) {
        List<String> lines = null;
        String[] names = new String[amount];
        try {
            lines = Files.readAllLines(Path.of("Names.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        var list = new ArrayList<String>();

        for (var i = 0; i < amount; i++)
            names[i] = lines.get(random.nextInt(lines.size()));

        return names;
    }
}
