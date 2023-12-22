package fr.alexiss.karmaka.strategies;

import fr.alexiss.karmaka.BotPlayer;
import fr.alexiss.karmaka.Game;
import fr.alexiss.karmaka.cards.Card;

public class Offensive implements Strategy {
    @Override
    public void play(BotPlayer player) {
        Card card = player.getHand().removeRandom();
        card.ability();
        Game.getInstance().getPlayers().get((Game.getInstance().getPlayers().indexOf(player) + 1) % 2).takeCard();
    }
}
