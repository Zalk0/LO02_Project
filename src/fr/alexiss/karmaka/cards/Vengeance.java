package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.*;
import fr.alexiss.karmaka.enums.CardColor;

public class Vengeance extends Card {
    public Vengeance() {
        super("Vengeance", 3, CardColor.RED,
                "Défaussez l’Oeuvre Exposée d’un rival.");
    }

    @Override
    public void ability() {
    	Pile<Card> fosse = Menu.getInstance().getGame().getRuins();
    	Pile<Card> rivalDeeds = Menu.getInstance().getGame().getOppositePlayer().getDeeds();

    	try {
			Card carte = rivalDeeds.getFirst();
			fosse.add(carte);
			System.out.println("L'Oeuvre " + carte + "a été défaussée!");
		} catch (Exception e){
	        System.out.println("Le rival n'a pas d'oeuvre!");
		}
    }
}
