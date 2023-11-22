package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.enums.CardColor;

public class Recycle extends Card {
    public Recycle() {
        super("Recyclage", 1, CardColor.GREEN,
                "Ajoutez à votre Vie Future une des 3 dernières cartes de la Fosse.");
    }

    @Override
    public void ability() {

    }
}
