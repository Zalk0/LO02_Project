package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Pile;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

public class Transmigrate extends Card {
    public Transmigrate() {
        super("Transmigration", 1, CardColor.BLUE,
                "Placez dans votre Main n’importe quelle carte de votre Vie Future.");
    }

    @Override
    public void ability() {
    	Pile<Card> playerFutureLife = Menu.getInstance().getGame().getCurrentPlayer().getFutureLife();
    	Player player = Menu.getInstance().getGame().getCurrentPlayer();
    	boolean chosen = false;
    	
    	System.out.println("Carte(s) présente(s) dans votre Vie Future:\n");
        for (int i = 0; i < playerFutureLife.size(); i++) {
            System.out.println((i + 1) + ". " + playerFutureLife.get(i));
        }
        
        while (chosen == false) {
	        System.out.println("\nSélectionner une carte par son numéro:");
	        
	        int choice = player.getChoice(0, playerFutureLife.size());
	        
	        Card cardSelected = playerFutureLife.get(choice - 1);
	        System.out.println("\n" + cardSelected.getDetails());
	
	        System.out.println("\nChoisir une action:");
	        System.out.println("0. Retour");
	        System.out.println("1. Prendre en main");
	        
	        if (choice == 1) {
	        	chosen = true;
	        	System.out.println("Je prend " + cardSelected + ".");
	        	playerFutureLife.remove(cardSelected);
                player.getHand().add(cardSelected);
	        }
        }
    }
}
