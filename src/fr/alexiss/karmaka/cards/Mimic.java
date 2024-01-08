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
        Player player = Menu.getInstance().getGame().getOppositePlayer();
        if (player.getDeeds().isEmpty()) {
            System.out.println("La pile d'Oeuvres de votre Rival est vide");
            return;
        }
        Card exposedCard = player.getDeeds().getFirst();

        System.out.println("Vous copiez la capacité de l'Oeuvre exposée de l'adversaire : " + exposedCard);

        if (exposedCard instanceof Mimic) {
            System.out.println("C'est une carte Mimétisme, il ne se passe rien !");
            return;
        }
        exposedCard.ability();
    }
}
