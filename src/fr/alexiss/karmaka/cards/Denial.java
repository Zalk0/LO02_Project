package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

public class Denial extends Card {
    public Denial() {
        super("Déni", 2, CardColor.BLUE,
                "Défaussez une carte de votre Main. Copiez le pouvoir de cette carte.");
    }

    @Override
    public void ability() {
        Player player = Menu.getInstance().getGame().getCurrentPlayer();
        if (!player.getHand().isEmpty()) {
            System.out.println("Vous défaussez une de vos cartes");
            System.out.println("Carte(s) présente(s) dans votre Main :");
            for (int i = 0; i < player.getHand().size(); i++) {
                System.out.println((i + 1) + ". " + player.getHand().get(i));
            }
            int choice = player.getChoice(1, player.getHand().size());
            Menu.getInstance().getGame().addToRuins(player.getHand().remove(choice));
            Menu.getInstance().getGame().getRuins().getFirst().ability();
        } else {
            System.out.println("Vous n'avez plus de cartes dans votre main");
        }
    }
}
