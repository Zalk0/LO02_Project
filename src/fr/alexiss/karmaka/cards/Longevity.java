package fr.alexiss.karmaka.cards;

import java.util.List;

import fr.alexiss.karmaka.*;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

public class Longevity extends Card {
    public Longevity() {
        super("Longévité", 2, CardColor.GREEN,
                "Placez 2 cartes puisées à la Source sur la Pile d'un joueur.");
    }

    @Override
    public void ability() {
    	 Pile<Card> source = Menu.getInstance().getGame().getWell();
    	
    	 List<Player> players = Menu.getInstance().getGame().getPlayers();
         System.out.println("Choisissez qui ajoute deux cartes de la source à sa pile :");
         for (int i = 0; i < players.size(); i++) {
             System.out.println((i + 1) + ". " + players.get(i));
         }
         int choice = Menu.getInstance().getGame().getCurrentPlayer().getChoice(1, players.size());
         Player player = players.get(choice - 1);
         
         for (int i = 0; i < 2; i++) {
         player.addToDeck(source.removeFirst());
         }
         System.out.println("Deux cartes ont été ajoutée à la Pile de :" + player);
    }
}
