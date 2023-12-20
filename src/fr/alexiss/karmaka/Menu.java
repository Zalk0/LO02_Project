package fr.alexiss.karmaka;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private static Menu menu;

    private static final Scanner scanner = new Scanner(System.in);

    private Game game;

    private Menu() {
    }

    private void mainMenu() {
        System.out.println("Bienvenue sur le jeu Karmaka !");
        System.out.println("Veuillez choisir une option dans le menu en tapant le chiffre correspondant :");
        System.out.println("1. Nouvelle partie");
        System.out.println("2. Charger une partie");
        System.out.println("3. Sauvegarder la partie en cours");
        System.out.println("4. Quitter le jeu");

        switch (getInput("[1-4]", "Vous devez rentrer un chiffre entre 1 et 4")) {
            case "1":
                createGame();
                break;
            case "2":
                loadGame();
                break;
            case "3":
                saveGame();
                break;
            case "4":
                quitGame();
                break;
        }
    }

    public static String getInput(String regex, String msg) {
        String choice = "";
        while (choice.isEmpty()) {
            try {
                choice = scanner.next(regex);
            } catch (InputMismatchException e) {
                System.out.println(msg);
                scanner.nextLine();
            }
        }
        return choice;
    }

    private void createGame() {
        game = new Game();
        System.out.println("Combien de joueurs va-t-il y avoir ?");
        int nbPlayers = Integer.parseInt(getInput("[2-4]", "Veuillez entre un chiffre entre 2 et 4"));
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
        //TODO
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static Menu getInstance() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    public static void main(String[] args) {
        Menu menu = Menu.getInstance();
        menu.mainMenu();
    }
}
