package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.*;
import fr.alexiss.karmaka.enums.CardColor;

public class Spite extends Card {
    public Spite() {
        super("Bassesse", 3, CardColor.RED,
                "Défaussez au hasard 2 cartes de la Main d’un rival.");
    }

    @Override
    public void ability() {
    	Pile<Card> rivalHand = Menu.getInstance().getGame().getOppositePlayer().getHand();
    	Pile<Card> fosse = Menu.getInstance().getGame().getRuins();
    	
    	for (int i = 0; i < 2; i++ )
    		try {
    			Card carte = rivalHand.removeRandom();
    			fosse.add(carte);
    			System.out.println("La carte " + carte + "a été défaussée.");
    		} catch (Exception e){
    	        System.out.println("Le rival n'a plus de carte en main!");
    			break;
    		}
    }
}
