package com.company;

import com.company.controller.Tournament;
import com.company.view.ConsoleView;
import com.company.view.Viewable;

public class Main {

    public static Viewable view;

    public static void main(String[] args) {

        view = new ConsoleView();

        Tournament tournament = new Tournament();
        tournament.initialize();

    }
}
