package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Pile;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

public class Salvage extends Card {
    public Salvage() {
        super("Sauvetage", 2, CardColor.GREEN,
                "Ajoutez à votre Main une des 3 dernières cartes de la Fosse.");
    }

    @Override
    public void ability() {
        Player player = Menu.getInstance().getGame().getCurrentPlayer();
        // Get last 3 cards of the Ruins
        Pile<Card> ruins = Menu.getInstance().getGame().getRuins();

        if (ruins.isEmpty()) {
            System.out.println("Il n'y a pas de carte dans la Fosse, il ne se passe rien !");
            return;
        }

        int sizeRuins = ruins.size();
        if (ruins.size() > 2) {
            sizeRuins = 3;
        }

        while (true) {
            System.out.println(sizeRuins + " dernières cartes de la Fosse :");
            for (int i = 0; i < sizeRuins; i++) {
                System.out.println((i + 1) + ". " + ruins.get(i));
            }

            //Choix de la carte
            System.out.println("\nChoisir une carte à ajouter à votre Main :");
            System.out.println("Sélectionner une carte par son numéro");
            System.out.println("Aide WIP (aide)");

            int choice = player.getChoice(1, sizeRuins) - 1;

            Card cardSelected = ruins.get(choice);
            System.out.println("\n" + cardSelected.getDetails());

            System.out.println("\nChoisir une action :");
            System.out.println("0. Retour");
            System.out.println("1. Prendre");

            choice = player.getChoice(0, 1);

            if (choice == 1) {
                System.out.println(cardSelected + " ajoutée à votre Main.");
                ruins.remove(cardSelected);
                player.addToHand(cardSelected);
                return;
            }
        }
    }
}
