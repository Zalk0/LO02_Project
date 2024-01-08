package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.*;
import fr.alexiss.karmaka.enums.CardColor;

public class Sowing extends Card {
    public Sowing() {
        super("Semis", 2, CardColor.GREEN,
                "Puisez 2 cartes à la Source, puis placez sur votre Vie Future 2 cartes de votre Main.");
    }

    @Override
    public void ability() {
    	Player player = Menu.getInstance().getGame().getCurrentPlayer();
    	Pile<Card> playerVieFuture = player.getFutureLife();
    	Pile<Card> playerHand = player.getHand();
    	Pile<Card> source = Menu.getInstance().getGame().getWell();
    	int placedcards;
    	
    	//Puiser 2 cartes
    	for (int i = 0; i<2; i++) {
    		try {
    			playerHand.add(source.getFirst());
    		} catch (Exception e) {
    			System.out.println("Il n'y a plus de carte dans la source!");
    			break;
    		}
    	}
    	
    	//Placer 2 cartes de la main en vie future
    	for (placedcards = 0; placedcards < 2;) {
    		System.out.println("Carte(s) présente(s) dans votre main :\n");
            for (int i = 0; i < playerHand.size(); i++) {
                System.out.println((i + 1) + ". " + playerHand.get(i));
            }
            
            System.out.println("\nChoisir une carte à mettre dans la Vie Future:");
	        System.out.println("Sélectionner une carte par son numéro");
	        System.out.println("Finir (0)");
	        System.out.println("Aide WIP (aide)");
	        
	        int choice = player.getChoice(0, playerHand.size());
	        
	        if (choice == 0) {
	            break;
	        }
	        
	        Card cardSelected = playerHand.get(choice - 1);
	        System.out.println("\n" + cardSelected.getDetails());

	        System.out.println("\nChoisir une action:");
	        System.out.println("0. Retour");
	        System.out.println("1. Placer");
	        
	        choice = player.getChoice(0, 1);
	        
	        if (choice == 1) {
	        	placedcards++;
	        	System.out.println("Je défausse " + cardSelected + ".");
	        	playerHand.remove(cardSelected);
	        	playerVieFuture.add(cardSelected);
	        }
	        
	        System.out.println("Il y a " + placedcards + " cartes placées.");
    	}
    }
}
