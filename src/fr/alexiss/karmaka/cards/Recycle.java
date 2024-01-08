package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.*;
import fr.alexiss.karmaka.enums.CardColor;

public class Recycle extends Card {
    public Recycle() {
        super("Recyclage", 1, CardColor.GREEN,
                "Ajoutez à votre Vie Future une des 3 dernières cartes de la Fosse.");
    }

    @Override
    public void ability() {
    	Player player = Menu.getInstance().getGame().getCurrentPlayer();
    	//Get 3 dernière de la Fosse
    	Pile<Card> fosse = Menu.getInstance().getGame().getRuins();
    	System.out.println("Trois dernières cartes de la défausse:\n");
        for (int i = 0; i < 3; i++) {
        	try {
        		System.out.println((i + 1) + ". " + fosse.get(i));
        	} catch (Exception e) {
        		break;
        	}
        }
    	
    	//Choix de la carte
        //TODO JE sais plus comment on fait
    	
    	//Ajout d'une carte
    	
    }
}
