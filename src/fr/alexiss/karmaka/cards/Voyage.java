package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Pile;
import fr.alexiss.karmaka.enums.CardColor;

public class Voyage extends Card {
    public Voyage() {
        super("Voyage", 3, CardColor.GREEN,
                "Puisez 3 cartes à la Source. Vous pouvez ensuite jouer une autre carte.");
    }

    @Override
    public void ability() {
    	Pile<Card> hand = Menu.getInstance().getGame().getCurrentPlayer().getHand();
    	Pile<Card> source = Menu.getInstance().getGame().getWell();
    	
    	for (int i = 0; i<3; i++) {
    		try {
        		hand.add(source.getFirst());
    		} catch (Exception e) {
    			System.out.println("Il n'y a plus de carte dans la source!");
    			break;
    		}
    	}
    	
    	
    	System.out.println("Vous pouvez rejouer une carte:");
        Menu.getInstance().getGame().getCurrentPlayer().play();
    }
}
