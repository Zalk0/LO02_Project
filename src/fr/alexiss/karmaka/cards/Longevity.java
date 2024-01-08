package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

import java.util.List;

public class Longevity extends Card {
    public Longevity() {
        super("Longévité", 2, CardColor.GREEN,
                "Placez 2 cartes puisées à la Source sur la Pile d'un joueur.");
    }

    @Override
    public void ability() {
        List<Player> players = Menu.getInstance().getGame().getPlayers();
        System.out.println("Choisissez qui ajoute deux cartes de la source à sa pile :");

        for (int i = 0; i < players.size(); i++) {
            System.out.println((i + 1) + ". " + players.get(i));
        }
        int choice = Menu.getInstance().getGame().getCurrentPlayer().getChoice(1, players.size());
        Player player = players.get(choice - 1);

        for (int i = 0; i < 2; i++) {
            player.getDeck().addFirst(Menu.getInstance().getGame().getWell().removeFirst());
        }
        System.out.println("Deux cartes ont été ajoutée à la Pile de :" + player);
    }
}
