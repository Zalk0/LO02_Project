package fr.alexiss.karmaka.strategies;

import fr.alexiss.karmaka.BotPlayer;
import fr.alexiss.karmaka.Menu;

public class Defensive implements Strategy {
    public void play(BotPlayer player) {
        player.addToDeeds(player.getHand().removeRandom());
    }
}
