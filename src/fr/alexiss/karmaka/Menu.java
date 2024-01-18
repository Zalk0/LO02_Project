package fr.alexiss.karmaka;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Singleton class that manages the game menu and contains the game entry point.
 */
public class Menu {

    private static Menu menu;

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    private Game game;


    /**
     * Private constructor to prevent instantiation.
     */
    private Menu() {

    }

    /**
     * Game entry point.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu.getInstance();
        menu.mainMenu();
    }

    /**
     * Displays the game menu.
     */
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

    /**
     * Displays the main menu (when in a game).
     */
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

    /**
     * Gets the user input and checks if it matches the regex.
     *
     * @param regex the regex to match
     * @param msg   the message to display if the input doesn't match the regex
     * @return the user input
     */
    public String getInput(String regex, String msg) {
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

    /**
     * Creates a new game.
     */
    private void createGame() {
        game = new Game();
        int nbPlayers = 2;
        for (int i = 0; i < nbPlayers; i++) {
            System.out.println("Veuillez entrer le nom du joueur " + (i + 1) + " :");
            String name = getInput(".+", "Vous devez entrer au moins 1 caractère");

            System.out.println(name + " est-il un bot ?");
            String isBot = getInput("(?i)oui|non", "Veuillez entrer \"oui\" ou \"non\"");

            game.addPlayer(name, isBot.equalsIgnoreCase("oui"));
        }
        game.begin();
        mainMenu();
    }

    /**
     * Loads a game from a file.
     */
    private void loadGame() {
        System.out.println("Entrez le numéro d'une sauvegarde :");
        String number = getInput("\\d+", "Veuillez entrer uniquement des chiffres");
        try {
            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream("Game_" + number + ".bin"));
            game = (Game) objectInput.readObject();
            objectInput.close();
            System.out.println("Partie chargée");
            game.resume();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        mainMenu();
    }

    /**
     * Saves the current game to a file.
     */
    private void saveGame() {
        System.out.println("Entrez un numéro pour la sauvegarde :");
        String number = getInput("\\d+", "Veuillez entrer uniquement des chiffres");
        try {
            ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream("Game_" + number + ".bin"));
            objectOutput.writeObject(game);
            objectOutput.close();
            System.out.println("Partie sauvegardée");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        gameMenu();
    }

    /**
     * Quits the current game.
     */
    private void quitGame() {
        game = null;
        mainMenu();
    }

    /**
     * Quits the game.
     */
    private void quit() {
        System.out.println("Voulez-vous vraiment quitter le jeu ?");
        if (getInput("(?i)oui|non", "Veuillez entrer \"oui\" ou \"non\"").equalsIgnoreCase("oui")) {
            System.exit(0);
        }
        mainMenu();
    }

    /**
     * Gets the game instance.
     *
     * @return the game instance
     */
    public Game getGame() {
        return game;
    }

    /**
     * Singleton pattern method to get the unique instance of the class.
     *
     * @return the unique instance of the class
     */
    public static Menu getInstance() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    /**
     * Gets the random instance.
     *
     * @return the random instance
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Gets the scanner instance.
     *
     * @return the scanner instance
     */
    public Scanner getScanner() {
        return scanner;
    }
}
