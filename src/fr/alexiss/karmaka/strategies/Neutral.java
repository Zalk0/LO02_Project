package fr.alexiss.karmaka.strategies;

import fr.alexiss.karmaka.BotPlayer;
import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.cards.Card;

public class Neutral implements Strategy {
    @Override
    public void play(BotPlayer player) {
        switch (Menu.getInstance().getRandom().nextInt(3)) {
            case 0 -> player.addToDeeds(player.getHand().removeRandom());
            case 1 -> {
                Card card = player.getHand().removeRandom();
                card.ability();
                Menu.getInstance().getGame().getOppositePlayer().takeCard(card);
            }
            case 2 -> player.addToFutureLife(player.getHand().removeRandom());
        }
    }
}
