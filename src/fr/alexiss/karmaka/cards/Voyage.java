package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.enums.CardColor;

public class Voyage extends Card {
    public Voyage() {
        super("Voyage", 3, CardColor.GREEN,
                "Puisez 3 cartes à la Source. Vous pouvez ensuite jouer une autre carte.");
    }

    @Override
    public void ability() {

    }
}
