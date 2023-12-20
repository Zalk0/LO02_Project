package fr.alexiss.karmaka;

import fr.alexiss.karmaka.strategies.Strategy;
import fr.alexiss.karmaka.enums.StrategyTypes;

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
        int input = Integer.parseInt(Menu.getInput("[0-" + maxOrdinal + "]", "Veuillez entre un chiffre en 0 et " + maxOrdinal));
        strategy = StrategyTypes.values()[input].getStrategy();
    }

    @Override
    public void playTurn() {
        strategy.play();
    }
}
