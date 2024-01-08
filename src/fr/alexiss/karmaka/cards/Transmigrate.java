package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Pile;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

public class Transmigrate extends Card {
    public Transmigrate() {
        super("Transmigration", 1, CardColor.BLUE,
                "Placez dans votre Main n’importe quelle carte de votre Vie Future.");
    }

    @Override
    public void ability() {
        Pile<Card> playerFutureLife = Menu.getInstance().getGame().getCurrentPlayer().getFutureLife();
        Player player = Menu.getInstance().getGame().getCurrentPlayer();

        if (playerFutureLife.isEmpty()) {
            System.out.println("Votre Vie Future est vide, il ne se passe rien !");
            return;
        }

        System.out.println("Carte(s) présente(s) dans votre Vie Future :");
        for (int i = 0; i < playerFutureLife.size(); i++) {
            System.out.println((i + 1) + ". " + playerFutureLife.get(i));
        }

        while (true) {
            System.out.println("\nSélectionner une carte par son numéro :");

            int choice = player.getChoice(1, playerFutureLife.size()) - 1;

            Card cardSelected = playerFutureLife.get(choice);
            System.out.println("\n" + cardSelected.getDetails());

            System.out.println("\nChoisir une action :");
            System.out.println("0. Retour");
            System.out.println("1. Prendre en main");

            if (player.getChoice(0, 1) == 1) {
                System.out.println("Je prends " + cardSelected + ".");
                playerFutureLife.remove(cardSelected);
                player.addToHand(cardSelected);
                return;
            }
        }
    }
}
