package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.enums.CardColor;

public abstract class Card {
    private final String name;
    private final int points;
    private final CardColor color;
    private final String description;


    public Card(String name, int points, CardColor color, String description) {
        this.name = name;
        this.points = points;
        this.color = color;
        this.description = description;
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

    public String getDetails() {
        return this.name +
                "\nPoints : " + this.points +
                "\nCouleur : " + this.color +
                "\nDescription : " + this.description;
    }
}
