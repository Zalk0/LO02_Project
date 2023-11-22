package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.enums.CardColor;

public class Peek extends Card {
    public Peek() {
        super("Coup d'Oeil", 1, CardColor.BLUE,
                "Regardez la Main d’un rival. Vous pouvez ensuite jouer une autre carte.");
    }

    @Override
    public void ability() {

    }
}
