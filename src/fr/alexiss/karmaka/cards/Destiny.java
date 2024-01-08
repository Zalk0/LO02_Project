package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Pile;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

public class Destiny extends Card {
    public Destiny() {
        super("Destinée", 2, CardColor.BLUE,
                "Regardez les 3 premières cartes de la Source, ajoutez-en jusqu’à 2 à votre Vie Future. Replacez le reste dans l'ordre souhaité.");
    }

    @Override
    public void ability() {
    	Player player = Menu.getInstance().getGame().getCurrentPlayer();
    	Pile<Card> source = Menu.getInstance().getGame().getWell();
    	int takenCards = 0;
    	int remainingCards = 3;
    	
    	System.out.println("Trois premières cartes de la Source :\n");
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ". " + source.get(i));
        }
        
        for (takenCards = 0; takenCards < 2; ) {
	        System.out.println("\nChoisir une carte à piocher:");
	        System.out.println("Sélectionner une carte par son numéro");
	        System.out.println("Finir (0)");
	        System.out.println("Aide WIP (aide)");
	        
	        int choice = player.getChoice(0, 3);
	        
	        if (choice == 0) {
	            break;
	        }
	        
	        Card cardSelected = source.get(choice - 1);
	        System.out.println("\n" + cardSelected.getDetails());

	        System.out.println("\nChoisir une action:");
	        System.out.println("0. Retour");
	        System.out.println("1. Piocher");
	        
	        choice = player.getChoice(0, 1);
	        
	        if (choice == 1) {
	        	takenCards++;
	        	remainingCards--;
	        	System.out.println("Je défausse " + cardSelected + ".");
                source.remove(cardSelected);
                player.getHand().add(cardSelected);
	        }
	        
	        System.out.println("Il y a " + takenCards + " cartes piochées.");
        }
    	
    	//TODO JE SAIS PAS LA FIN
    }
}
