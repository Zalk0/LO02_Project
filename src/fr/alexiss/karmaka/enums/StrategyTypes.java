package fr.alexiss.karmaka.enums;

import fr.alexiss.karmaka.strategies.Defensive;
import fr.alexiss.karmaka.strategies.Neutral;
import fr.alexiss.karmaka.strategies.Offensive;
import fr.alexiss.karmaka.strategies.Strategy;

/**
 * Represents the different strategies.
 */
public enum StrategyTypes {
    OFFENSIVE(new Offensive()), DEFENSIVE(new Defensive()), NEUTRAL(new Neutral());

    private final Strategy strategy;

    /**
     * Constructor.
     *
     * @param strategy the strategy.
     */
    StrategyTypes(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Returns the strategy in French.
     *
     * @return the strategy in French.
     */
    @Override
    public String toString() {
        return switch (super.toString()) {
            case "OFFENSIVE" -> "Offensive";
            case "DEFENSIVE" -> "Défensive";
            case "NEUTRAL" -> "Neutre";
            default -> super.toString();
        };
    }

    /**
     * Gets the strategy.
     *
     * @return the strategy.
     */
    public Strategy getStrategy() {
        return strategy;
    }
}
