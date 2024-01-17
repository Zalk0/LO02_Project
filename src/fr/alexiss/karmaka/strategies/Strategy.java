package fr.alexiss.karmaka.strategies;

import fr.alexiss.karmaka.BotPlayer;

/**
 * Interface for the strategies.
 */
public interface Strategy {

    /**
     * Plays a turn for the bot.
     *
     * @param player the bot that plays the card
     */
    void play(BotPlayer player);
}
