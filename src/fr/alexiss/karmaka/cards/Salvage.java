package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.enums.CardColor;

public class Salvage extends Card {
    public Salvage() {
        super("Sauvetage", 2, CardColor.GREEN,
                "Ajoutez à votre Main une des 3 dernières cartes de la Fosse.");
    }

    @Override
    public void ability() {

    }
}
