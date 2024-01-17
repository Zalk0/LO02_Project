package fr.alexiss.karmaka.strategies;

import fr.alexiss.karmaka.BotPlayer;
import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.cards.Card;

import java.io.Serializable;

/**
 * A strategy that plays a random card from the hand for its ability.
 */
public class Offensive implements Strategy, Serializable {

    /**
     * {@inheritDoc}
     */
    @Override
    public void play(BotPlayer player) {
        Card card = player.getHand().removeRandom();
        System.out.println("Je joue la compétence de " + card);
        Menu.getInstance().getGame().getOppositePlayer().takeCard(card);
        card.ability();
    }
}
