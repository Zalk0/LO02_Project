package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Pile;
import fr.alexiss.karmaka.enums.CardColor;

public class StolenDreams extends Card {
    public StolenDreams() {
        super("Rêves Brisés", 2, CardColor.BLUE,
                "Placez la première carte de la Vie Future d'un rival sur la vôtre.");
    }

    @Override
    public void ability() {
    	Pile<Card> playerFutureLife = Menu.getInstance().getGame().getCurrentPlayer().getFutureLife();
    	Pile<Card> rivalFutureLife = Menu.getInstance().getGame().getOppositePlayer().getFutureLife();
    	
    	try {
			Card carte = rivalFutureLife.getFirst();
			playerFutureLife.add(carte);
			System.out.println("La carte " + carte + "a été volée!");
		} catch (Exception e){
	        System.out.println("La vie future du rival est vide!");
		}
    }
}
