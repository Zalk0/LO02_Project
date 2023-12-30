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
        return switch (super.toString()) {
            case "OFFENSIVE" -> "Offensive";
            case "DEFENSIVE" -> "Défensive";
            case "NEUTRAL" -> "Neutre";
            default -> super.toString();
        };
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
