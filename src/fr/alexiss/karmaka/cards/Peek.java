package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.*;
import fr.alexiss.karmaka.enums.CardColor;

public class Peek extends Card {
    public Peek() {
        super("Coup d'Oeil", 1, CardColor.BLUE,
                "Regardez la Main d’un rival. Vous pouvez ensuite jouer une autre carte.");
    }

    @Override
    public void ability() {
    	Pile<Card> hand = Menu.getInstance().getGame().getOppositePlayer().getHand();
    	System.out.println("Carte(s) présente(s) dans la main du joueur adverse:\n");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i));
        }
        
        // Rejouer
    	System.out.println("Vous pouvez rejouer une carte:");
        Player player = Menu.getInstance().getGame().getCurrentPlayer();
        player.play();
    }
}
