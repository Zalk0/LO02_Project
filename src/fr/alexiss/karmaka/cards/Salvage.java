package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Pile;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

public class Salvage extends Card {
    public Salvage() {
        super("Sauvetage", 2, CardColor.GREEN,
                "Ajoutez à votre Main une des 3 dernières cartes de la Fosse.");
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
        System.out.println("Sélectionner une carte par son numéro que vous voulez ajouter à votre main.");
        int choice = player.getChoice(1, 3);
        Card cardSelected = fosse.get(choice - 1);
        fosse.remove(cardSelected);
        player.addToHand(cardSelected);
    }
}
