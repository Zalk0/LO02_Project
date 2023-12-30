package fr.alexiss.karmaka.strategies;

import fr.alexiss.karmaka.BotPlayer;
import fr.alexiss.karmaka.cards.Card;

public class Defensive implements Strategy {
    @Override
    public void play(BotPlayer player) {
        Card card = player.getHand().removeRandom();
        System.out.println("Je mets " + card + " dans mes Oeuvres");
        player.addToDeeds(card);
    }
}
