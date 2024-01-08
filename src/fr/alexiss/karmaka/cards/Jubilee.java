package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.*;
import fr.alexiss.karmaka.enums.CardColor;

public class Jubilee extends Card {
    public Jubilee() {
        super("Jubilé", 3, CardColor.GREEN,
                "Placez jusqu’à 2 cartes de votre Main sur vos Oeuvres.");
    }

    @Override
    public void ability() {
    	Pile<Card> deeds = Menu.getInstance().getGame().getCurrentPlayer().getDeeds();
    	Pile<Card> hand = Menu.getInstance().getGame().getCurrentPlayer().getDeeds();
    	Player player = Menu.getInstance().getGame().getCurrentPlayer();
    	int placedcards;
    	
    	for (placedcards = 0; placedcards < 2;) {
    		System.out.println("Carte(s) présente(s) dans votre main :\n");
            for (int i = 0; i < hand.size(); i++) {
                System.out.println((i + 1) + ". " + hand.get(i));
            }
            
            System.out.println("\nChoisir une carte à mettre dans les Oeuvres:");
	        System.out.println("Sélectionner une carte par son numéro");
	        System.out.println("Finir (0)");
	        System.out.println("Aide WIP (aide)");
	        
	        int choice = player.getChoice(0, hand.size());
	        
	        if (choice == 0) {
	            break;
	        }
	        
	        Card cardSelected = hand.get(choice - 1);
	        System.out.println("\n" + cardSelected.getDetails());

	        System.out.println("\nChoisir une action:");
	        System.out.println("0. Retour");
	        System.out.println("1. Placer");
	        
	        choice = player.getChoice(0, 1);
	        
	        if (choice == 1) {
	        	placedcards++;
	        	System.out.println("Je défausse " + cardSelected + ".");
                hand.remove(cardSelected);
                deeds.add(cardSelected);
	        }
	        
	        System.out.println("Il y a " + placedcards + " cartes placées.");
    	}
    	
    	

    }
}
