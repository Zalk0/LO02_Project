package fr.alexiss.karmaka;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Menu {

    private static Menu menu;

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    private Game game;

    private Menu() {

    }

    public static Menu getInstance() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    public static void main(String[] args) {
        Menu.getInstance();
        menu.mainMenu();
    }

    private void gameMenu() {
        System.out.println("\n---------- Menu ----------");
        System.out.println("Veuillez choisir une option dans le menu en tapant le nombre correspondant :");
        System.out.println("1. Reprendre la partie");
        System.out.println("2. Sauvegarder la partie en cours");
        System.out.println("3. Quitter la partie");
        System.out.println("4. Quitter le jeu");
        switch (getInput("[1-4]", "Vous devez rentrer un nombre entre 1 et 4")) {
            case "2" -> saveGame();
            case "3" -> quitGame();
            case "4" -> quit();
        }
    }

    private void mainMenu() {
        System.out.println("\n---------- Karmaka ----------");
        System.out.println("Veuillez choisir une option dans le menu en tapant le nombre correspondant :");
        System.out.println("1. Nouvelle partie");
        System.out.println("2. Charger une partie");
        System.out.println("3. Quitter le jeu");
        switch (getInput("[1-3]", "Vous devez rentrer un nombre entre 1 et 3")) {
            case "1" -> createGame();
            case "2" -> loadGame();
            case "3" -> quit();
        }
    }

    public String getInput(String regex, String msg) { // TODO Ajouter de l'aide et un raccourci de menu
        String choice = "";
        while (choice.isEmpty()) {
            try {
                choice = scanner.next(regex);
            } catch (InputMismatchException e) {
                System.out.println(msg);
                scanner.nextLine();
            }
        }
        if (choice.equalsIgnoreCase("menu")) {
            gameMenu();
            System.out.println(msg);
            choice = getInput(regex, msg);
        }
        return choice;
    }

    private void createGame() {
        game = new Game();
        int nbPlayers = 2;
        for (int i = 0; i < nbPlayers; i++) {
            System.out.println("Veuillez entrer le nom du joueur " + (i + 1) + " :");
            String name = getInput(".+", "Vous devez entrer au moins 1 caractère");

            System.out.println(name + " est-il un bot ? (0 pour non, 1 pour oui)");
            String isBot = getInput("[01]", "Veuillez entrer 0 ou 1");

            game.addPlayer(name, isBot.equals("1"));
        }
        game.begin();
        mainMenu();
    }

    private void loadGame() {
        // TODO
    }

    private void saveGame() {
        // TODO
    }

    private void quitGame() {
        game = null;
        mainMenu();
    }

    private void quit() {
        System.out.println("Voulez-vous vraiment quitter le jeu ?");
        if (getInput("(?i)oui|non", "Veuillez entrer \"oui\" ou \"non\"").equalsIgnoreCase("oui")) {
            return;
        }
        mainMenu();
    }

    public Game getGame() {
        return game;
    }

    public Random getRandom() {
        return random;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
