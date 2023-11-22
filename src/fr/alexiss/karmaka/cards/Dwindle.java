package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.enums.CardColor;

public class Dwindle extends Card {
    public Dwindle() {
        super("Dernier Souffle", 1, CardColor.RED,
                "Le joueur de votre choix défausse une carte de sa Main.");
    }

    @Override
    public void ability() {

    }
}
