package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

import java.util.List;

public class Panic extends Card {
    public Panic() {
        super("Panique", 1, CardColor.RED,
                "Défaussez la première carte de la Pile d'un joueur. Vous pouvez ensuite jouer une autre carte.");
    }

    @Override
    public void ability() {
        List<Player> players = Menu.getInstance().getGame().getPlayers();

        // Choose the player who will discard a card
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i + 1) + ". " + players.get(i));
        }
        Player player = players.get(Menu.getInstance().getGame().getCurrentPlayer().getChoice(1, players.size()) - 1);
        if (player.getDeck().isEmpty()) {
            System.out.println("La pile du joueur est vide.");
        } else {
            Card card = player.getDeck().removeFirst();
            Menu.getInstance().getGame().addToRuins(card);
        }

        // Play another card
        System.out.println("Vous pouvez jouer une autre carte.");
        if (Menu.getInstance().getGame().getCurrentPlayer().getHand().isEmpty()) {
            System.out.println("Votre Main est vide, il ne se passe rien !");
            return;
        }
        Menu.getInstance().getGame().getCurrentPlayer().play();
    }
}
