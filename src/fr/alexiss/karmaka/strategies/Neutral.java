package fr.alexiss.karmaka.strategies;

import fr.alexiss.karmaka.BotPlayer;
import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.cards.Card;

public class Neutral implements Strategy {
    @Override
    public void play(BotPlayer player) {
        Card card = player.getHand().removeRandom();
        switch (Menu.getInstance().getRandom().nextInt(3)) {
            case 0 -> {
                System.out.println("Je mets " + card + " dans mes Oeuvres");
                player.addToDeeds(card);
            }
            case 1 -> {
                System.out.println("Je joue la compétence de " + card);
                Menu.getInstance().getGame().getOppositePlayer().takeCard(card);
                card.ability();
            }
            case 2 -> {
                System.out.println("Je mets " + card + " dans ma Vie Future");
                player.addToFutureLife(card);
            }
        }
    }
}
