package fr.alexiss.karmaka;

import fr.alexiss.karmaka.cards.Card;
import fr.alexiss.karmaka.enums.StrategyTypes;
import fr.alexiss.karmaka.strategies.Strategy;

public class BotPlayer extends Player {

    private Strategy strategy;


    public BotPlayer(String name) {
        super(name);
        setStrategy();
    }

    private void setStrategy() {
        System.out.println("Veuillez choisir la stratégie que le bot suivra pendant la partie :");
        for (StrategyTypes strategyType : StrategyTypes.values()) {
            System.out.println(strategyType.ordinal() + ". " + strategyType);
        }
        int maxOrdinal = StrategyTypes.values().length - 1;
        int input = Integer.parseInt(Menu.getInstance().getInput("[0-" + maxOrdinal + "]", "Veuillez entre un nombre en 0 et " + maxOrdinal));
        strategy = StrategyTypes.values()[input].getStrategy();
    }

    @Override
    public int getChoice(int min, int max) {
        return Menu.getInstance().getRandom().nextInt(min, max + 1);
    }

    @Override
    public boolean getChoice() {
        return Menu.getInstance().getRandom().nextBoolean();
    }

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

    @Override
    public void play() {
        strategy.play(this);
    }

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

    @Override
    protected boolean useKarmicRing(int points) {
        return points + getKarmicRing() >= getKarmicLadder().getValue();
    }
}
