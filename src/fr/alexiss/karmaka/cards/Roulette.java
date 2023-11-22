package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.enums.CardColor;

public class Roulette extends Card {
    public Roulette() {
        super("Roulette", 2, CardColor.RED,
                "Défaussez jusqu’à 2 cartes de votre Main. Vous pouvez ensuite puiser à la Source autant de carte(s) + 1.");
    }

    @Override
    public void ability() {

    }
}
