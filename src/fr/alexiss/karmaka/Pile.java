package fr.alexiss.karmaka;

import java.util.LinkedList;

/**
 * A pile of cards.
 *
 * @param <Card> the type of cards in the pile
 */
public class Pile<Card> extends LinkedList<Card> {

    /**
     * Gets a random card from the pile.
     *
     * @return a random card from the pile
     */
    public Card getRandom() {
        return get(Menu.getInstance().getRandom().nextInt(size()));
    }

    /**
     * Removes a random card from the pile.
     *
     * @return the removed card
     */
    public Card removeRandom() {
        return remove(Menu.getInstance().getRandom().nextInt(size()));
    }
}
