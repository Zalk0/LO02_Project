package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.*;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

public class Panic extends Card {
    public Panic() {
        super("Panique", 1, CardColor.RED,
                "Défaussez la première carte de la Pile d'un joueur. Vous pouvez ensuite jouer une autre carte.");
    }

    @Override
    public void ability() {
    	Pile<Card> defausse = Menu.getInstance().getGame().getRuins();
    	Player oppositePlayer = Menu.getInstance().getGame().getOppositePlayer();
    	defausse.add(oppositePlayer.getDeck().removeFirst());
    	
    	
    	//TODO Rejouer une carte
    }
}
