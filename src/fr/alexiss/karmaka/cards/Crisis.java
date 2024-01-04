package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

public class Crisis extends Card {
    public Crisis() {
        super("Crise", 2, CardColor.RED,
                "Le rival de votre choix défausse une de ses Oeuvres.");
    }

    @Override
    public void ability() {
        Player player = Menu.getInstance().getGame().getOppositePlayer();
        if (!player.getDeeds().isEmpty()) {
            System.out.println("Vous forcez votre rival à défaussez une de ses oeuvres");
            System.out.println("Carte(s) présente(s) dans ses Oeuvres :");
            for (int i = 0; i < player.getDeeds().size(); i++) {
                System.out.println((i + 1) + ". " + player.getDeeds().get(i));
            }
            int choice = player.getChoice(1, player.getDeeds().size());
            Menu.getInstance().getGame().addToRuins(player.getDeeds().remove(choice));
        } else {
            System.out.println("Le joueur adverse n'a pas de carte dans ses Oeuvres");
        }
    }
}
