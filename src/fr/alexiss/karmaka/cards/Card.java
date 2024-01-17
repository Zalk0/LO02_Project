package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.enums.CardColor;

import java.io.Serializable;

/**
 * Abstract class representing a card.
 * A card has a name, a number of points, a color and a description.
 * It can be played by a player.
 */
public abstract class Card implements Serializable {
    private final String name;
    private final int points;
    private final CardColor color;
    private final String description;


    /**
     * Constructor.
     *
     * @param name        the name of the card
     * @param points      the number of points of the card
     * @param color       the color of the card
     * @param description the description of the card
     */
    public Card(String name, int points, CardColor color, String description) {
        this.name = name;
        this.points = points;
        this.color = color;
        this.description = description;
    }

    /**
     * Override of the toString method to return the name of the card
     *
     * @return Name of the card
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Abstract method representing the ability of the card.
     */
    public abstract void ability();

    /**
     * Gets the number of points of the card.
     *
     * @return the number of points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Gets the description of the card.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the color of the card.
     *
     * @return the color
     */
    public CardColor getColor() {
        return color;
    }

    /**
     * Gets the details of the card.
     * Contains the name, the number of points, the color and the description.
     *
     * @return the details
     */
    public String getDetails() {
        return this.name +
                "\nPoints : " + this.points +
                "\nCouleur : " + this.color +
                "\nDescription : " + this.description;
    }
}
