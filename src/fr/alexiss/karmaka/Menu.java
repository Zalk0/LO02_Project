package fr.alexiss.karmaka;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private static Menu menu;

    private static final Scanner scanner = new Scanner(System.in);

    private Game game;

    private Menu() {
        mainMenu();
    }

    private void mainMenu() {
        String choice = "";

        System.out.println("Bienvenue sur le jeu Karmaka !");
        System.out.println("Veuillez choisir une option dans le menu en tapant le chiffre correspondant :");
        System.out.println("1. Nouvelle partie");
        System.out.println("2. Charge une partie");
        System.out.println("3. Sauvegarder la partie en cours");
        System.out.println("4. Quitter le jeu");

        while (choice.isEmpty()) {
            try {
                choice = scanner.next("[1-4]");
            } catch (InputMismatchException e) {
                System.out.println("Vous devez rentrer un chiffre");
                scanner.nextLine();
            }
        }

        switch (choice) {
            case "1":
                game = new Game();
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

    private void loadGame() {
        //TODO
    }

    private void saveGame() {
        //TODO
    }

    private void quitGame() {
        //TODO
    }

    public static Menu getInstance() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    public static void main(String[] args) {
        getInstance();
    }
}
