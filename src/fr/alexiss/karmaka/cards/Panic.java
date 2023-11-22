package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.enums.CardColor;

public class Panic extends Card {
    public Panic() {
        super("Panique", 1, CardColor.RED,
                "Défaussez la première carte de la Pile d'un joueur. Vous pouvez ensuite jouer une autre carte.");
    }

    @Override
    public void ability() {

    }
}
