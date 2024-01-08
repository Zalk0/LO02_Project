package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Pile;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

public class Roulette extends Card {
    public Roulette() {
        super("Roulette", 2, CardColor.RED,
                "Défaussez jusqu’à 2 cartes de votre Main. Vous pouvez ensuite puiser à la Source autant de carte(s) + 1.");
    }

    @Override
    public void ability() {
        int nbrCartesDefaussees;
        Pile<Card> fosse = Menu.getInstance().getGame().getRuins();
        Player player = Menu.getInstance().getGame().getCurrentPlayer();
        Pile<Card> hand = Menu.getInstance().getGame().getCurrentPlayer().getHand();
        Pile<Card> deck = Menu.getInstance().getGame().getCurrentPlayer().getDeck();
        Pile<Card> source = Menu.getInstance().getGame().getWell();

        System.out.println("Carte(s) présente(s) dans votre main:\n");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i));
        }

        //Choix de la carte à défausser ou passer
        for (nbrCartesDefaussees = 0; nbrCartesDefaussees < 2; ) {
            System.out.println("\nChoisir une carte à défausser:");
            System.out.println("Sélectionner une carte par son numéro");
            System.out.println("Passer (0)");
            System.out.println("Aide WIP (aide)");

            int choice = player.getChoice(0, hand.size());

            if (choice == 0) {
                break;
            }

            Card cardSelected = hand.get(choice - 1);
            System.out.println("\n" + cardSelected.getDetails());

            System.out.println("\nChoisir une action:");
            System.out.println("0. Retour");
            System.out.println("1. Defausser");

            choice = player.getChoice(0, 1);

            if (choice == 1) {
                nbrCartesDefaussees++;
                System.out.println("Je défausse " + cardSelected + ".");
                hand.remove(cardSelected);
                fosse.add(cardSelected);
            }

            System.out.println("Il y a " + nbrCartesDefaussees + " cartes défaussées.");
        }

        //Puiser autant de carte
        for (int i = 0; i <= nbrCartesDefaussees + 1; i++) {
            deck.add(source.removeLast());
        }
    }
}
