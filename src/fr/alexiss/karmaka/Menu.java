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

    private void gameMenu() {
        System.out.println("\n---------- Menu ----------");
        System.out.println("Veuillez choisir une option dans le menu en tapant le nombre correspondant :");
        System.out.println("1. Sauvegarder la partie en cours");
        System.out.println("2. Quitter la partie");
        System.out.println("3. Quitter le jeu");
        switch (getInput("[1-3]", "Vous devez rentrer un nombre entre 1 et 3")) {
            case "1" -> saveGame();
            case "2" -> quitGame();
            case "3" -> quit();
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

    public String getInput(String regex, String msg) {
        String choice = "";
        regex += "|(?i)menu|(?i)aide";
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
        }
        return choice;
    }

    public int getInput(int min, int max) {
        return random.nextInt(min, max + 1);
    }

    private void createGame() {
        game = new Game();
        //System.out.println("Combien de joueurs va-t-il y avoir ?");
        //int nbPlayers = Integer.parseInt(getInput("[2-4]", "Veuillez entrer un nombre entre 2 et 4"));
        int nbPlayers = 2;
        for (int i = 0; i < nbPlayers; i++) {
            System.out.println("Veuillez entrer le nom du joueur " + (i + 1) + " :");
            String name = getInput(".+", "Vous devez entrer au moins 1 caractère");

            System.out.println(name + " est-il un bot ? (0 pour non, 1 pour oui)");
            String isBot = getInput("[01]", "Veuillez entrer 0 ou 1");

            game.addPlayer(name, isBot.equals("1"));
        }
        game.begin();
    }

    private void loadGame() {
        //TODO
    }

    private void saveGame() {
        //TODO
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
}
