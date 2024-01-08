package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

public class Mimic extends Card {
    public Mimic() {
        super("Mimétisme", 1, CardColor.MOSAIC,
                "Choisissez un Rival. Copiez le pouvoir de son Oeuvre Exposée.");
    }

    @Override
    public void ability() {
        Player rival = Menu.getInstance().getGame().getOppositePlayer();
        Card exposedCard = rival.getDeeds().getFirst();

        System.out.println("Vous copiez la capacité de l'Oeuvre exposée de l'advesaire : " + exposedCard);

        if (exposedCard instanceof Mimic) {
            return;
        }
        exposedCard.ability();
    }
}
