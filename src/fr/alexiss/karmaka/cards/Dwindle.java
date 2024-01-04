package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

import java.util.List;

public class Dwindle extends Card {
    public Dwindle() {
        super("Dernier Souffle", 1, CardColor.RED,
                "Le joueur de votre choix défausse une carte de sa Main.");
    }

    @Override
    public void ability() {
        List<Player> players = Menu.getInstance().getGame().getPlayers();
        System.out.println("Choisissez qui doit défausser une carte :");
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i + 1) + ". " + players.get(i));
        }
        int choice = Menu.getInstance().getGame().getCurrentPlayer().getChoice(1, players.size());
        Player player = players.get(choice - 1);

        if (!player.getHand().isEmpty()) {
            System.out.println(player + " a le(s) carte(s) suivante(s) dans sa Main :");
            for (int i = 0; i < player.getHand().size(); i++) {
                System.out.println((i + 1) + ". " + player.getHand().get(i));
            }
            choice = player.getChoice(1, player.getHand().size()) - 1;
            Menu.getInstance().getGame().addToRuins(player.getHand().remove(choice));
        } else {
            System.out.println(player + " n'a pas de carte dans sa Main");
        }
    }
}
