package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.enums.CardColor;

public class Destiny extends Card {
    public Destiny() {
        super("Destinée", 2, CardColor.BLUE,
                "Regardez les 3 premières cartes de la Source, ajoutez-en jusqu’à 2 à votre Vie Future. Replacez le reste dans l'ordre souhaité.");
    }

    @Override
    public void ability() {

    }
}
