package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.enums.CardColor;

public class Denial extends Card {
    public Denial() {
        super("Déni", 2, CardColor.BLUE,
                "Défaussez une carte de votre Main. Copiez le pouvoir de cette carte.");
    }

    @Override
    public void ability() {

    }
}
