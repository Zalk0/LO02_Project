package fr.alexiss.karmaka;

import java.util.LinkedList;

public class Pile<Card> extends LinkedList<Card> {
    public Card getRandom() {
        return get(Menu.getInstance().getRandom().nextInt(size()));
    }

    public Card removeRandom() {
        return remove(Menu.getInstance().getRandom().nextInt(size()));
    }
}
