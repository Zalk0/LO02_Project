package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.enums.CardColor;

public class Swindle extends Card {
    public Swindle() {
        super("Duperie", 3, CardColor.BLUE,
                "Regardez 3 cartes de la Main d’un rival, ajoutez-en une à votre Main.");
    }

    @Override
    public void ability() {

    }
}
