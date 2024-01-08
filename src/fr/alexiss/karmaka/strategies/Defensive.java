package fr.alexiss.karmaka.strategies;

import fr.alexiss.karmaka.BotPlayer;
import fr.alexiss.karmaka.cards.Card;

import java.io.Serializable;

public class Defensive implements Strategy, Serializable {
    @Override
    public void play(BotPlayer player) {
        Card card = player.getHand().removeRandom();
        System.out.println("Je mets " + card + " dans mes Oeuvres");
        player.addToDeeds(card);
    }
}
