package fr.alexiss.karmaka;

import fr.alexiss.karmaka.cards.Card;
import fr.alexiss.karmaka.enums.StrategyTypes;
import fr.alexiss.karmaka.strategies.Strategy;

/**
 * A bot player
 */
public class BotPlayer extends Player {

    private Strategy strategy;


    /**
     * Create a bot player
     *
     * @param name The name of the player
     */
    public BotPlayer(String name) {
        super(name);
        setStrategy();
    }

    /**
     * Set the strategy of the bot
     * The strategy is chosen by the user
     */
    private void setStrategy() {
        System.out.println("Veuillez choisir la stratégie que le bot suivra pendant la partie :");
        for (StrategyTypes strategyType : StrategyTypes.values()) {
            System.out.println(strategyType.ordinal() + ". " + strategyType);
        }
        int maxOrdinal = StrategyTypes.values().length - 1;
        int input = Integer.parseInt(Menu.getInstance().getInput("[0-" + maxOrdinal + "]", "Veuillez entre un nombre en 0 et " + maxOrdinal));
        strategy = StrategyTypes.values()[input].getStrategy();
    }

    /**
     * Get a choice from the bot (int)
     *
     * @param min Minimum value
     * @param max Maximum value
     * @return Choice of the bot
     */
    @Override
    public int getChoice(int min, int max) {
        return Menu.getInstance().getRandom().nextInt(min, max + 1);
    }

    /**
     * Play a turn
     */
    @Override
    public void playTurn() {
        // Actions done at the beginning of every turn
        // Return true when rebirth is happening, so the player doesn't play afterward
        if (beginTurn()) {
            return;
        }

        // Play a card
        play();

        System.out.println("\n---------- Fin du Tour du joueur : " + this.getName() + " ----------");
    }

    /**
     * Play a card
     * Method necessary because it can be used in cards
     * Calls the method of the strategy
     */
    @Override
    public void play() {
        strategy.play(this);
    }

    /**
     * Take a card from the opposite player
     *
     * @param card Card to take
     */
    @Override
    public void takeCard(Card card) {
        if (Menu.getInstance().getRandom().nextBoolean()) {
            addToFutureLife(card);
            System.out.println(this.getName() + " a pris la carte");
            return;
        }
        System.out.println(this.getName() + " n'a pas pris la carte");
        Menu.getInstance().getGame().addToRuins(card);
    }

    /**
     * Ask if the bot wants to use Karmic Ring(s)
     * The bot will use the Karmic Ring(s) if it can reach the next level
     *
     * @param points Number of points the bot has
     * @return True if the bot wants to use the Karmic Ring(s)
     */
    @Override
    protected boolean useKarmicRing(int points) {
        return points + getKarmicRing() >= getKarmicLadder().getValue();
    }

    /**
     * Get a choice from the bot (boolean)
     *
     * @return Choice of the bot
     */
    @Override
    public boolean getChoice() {
        return Menu.getInstance().getRandom().nextBoolean();
    }
}
