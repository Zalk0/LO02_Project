package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Pile;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

import java.util.Collections;

public class Swindle extends Card {
    public Swindle() {
        super("Duperie", 3, CardColor.BLUE,
                "Regardez 3 cartes de la Main d’un rival, ajoutez-en une à votre Main.");
    }

    @Override
    public void ability() {
        Player oPlayer = Menu.getInstance().getGame().getOppositePlayer();
        Player cPlayer = Menu.getInstance().getGame().getCurrentPlayer();
        Pile<Card> hand = (Pile<Card>) oPlayer.getHand().clone();
        if (hand.isEmpty()) {
            System.out.println("La main de votre rival est vide");
            return;
        }
        Collections.shuffle(hand);

        Pile<Card> cards = new Pile<>();
        System.out.println("Choisissez 3 cartes parmi les " + hand.size() + " de votre rival :\n");
        System.out.println("Le nombre de cartes diminue à chaque carte prise.");
        for (int i = 0; i < 3; i++) {
            cards.add(hand.remove(cPlayer.getChoice(1, hand.size()) - 1));
            if (hand.isEmpty()) {
                break;
            }
        }

        while (true) {
            System.out.println("Voici les " + cards.size() + " de votre rival :");
            for (int i = 0; i < cards.size(); i++) {
                System.out.println((i + 1) + ". " + cards.get(i));
            }

            System.out.println("\nChoisissez une carte à prendre :");
            System.out.println("Sélectionner une carte par son numéro");
            System.out.println("Aide WIP (aide)");
            int choice = cPlayer.getChoice(1, cards.size()) - 1;

            System.out.println("\n" + cards.get(choice).getDetails());
            System.out.println("Êtes-vous sûr de vouloir prendre cette carte ?");
            if (cPlayer.getChoice()) {
                cPlayer.addToHand(cards.get(choice));
                oPlayer.getHand().remove(cards.get(choice));
                break;
            }
        }
    }
}
