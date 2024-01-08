package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.enums.CardColor;

public class Sowing extends Card {
    public Sowing() {
        super("Semis", 2, CardColor.GREEN,
                "Puisez 2 cartes à la Source, puis placez sur votre Vie Future 2 cartes de votre Main.");
    }

    @Override
    public void ability() {
    	//TODO Definition puiser
    }
}
