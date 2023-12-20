package fr.alexiss.karmaka.enums;

import fr.alexiss.karmaka.strategies.*;

public enum StrategyTypes {
    OFFENSIVE(new Offensive()), DEFENSIVE(new Defensive()), NEUTRAL(new Neutral());

    private final Strategy strategy;
    StrategyTypes(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        String word = super.toString();
        String result = word.substring(0, 1).toUpperCase() +
                word.substring(1).toLowerCase();
        return result.trim();
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
