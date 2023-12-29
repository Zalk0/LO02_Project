package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Game;
import fr.alexiss.karmaka.enums.CardColor;

public abstract class Card {
    private final String name;
    private final int points;
    private final CardColor color;
    private final String description;
    private final Game game;


    public Card(String name, int points, CardColor color, String description, Game game) {
        this.name = name;
        this.points = points;
        this.color = color;
        this.description = description;
		this.game = game;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract void ability();

    public int getPoints() {
        return points;
    }

    public String getDescription() {
        return description;
    }

    public CardColor getColor() {
        return color;
    }

    public Game getGame() {
		return game;
	}

	public String getDetails() {
        return this.name +
                "\nPoints :" + this.points +
                "\nCouleur :" + this.color +
                "\nDescription :" + this.description;
    }
}
