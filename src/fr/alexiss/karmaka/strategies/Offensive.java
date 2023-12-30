package fr.alexiss.karmaka.strategies;

import fr.alexiss.karmaka.BotPlayer;
import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.cards.Card;

public class Offensive implements Strategy {
    @Override
    public void play(BotPlayer player) {
        Card card = player.getHand().removeRandom();
        card.ability();
        Menu.getInstance().getGame().getOppositePlayer().takeCard(card);
    }
}
