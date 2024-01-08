package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.*;
import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.enums.CardColor;

public class Embody extends Card {
    public Embody() {
        super("Incarnation", 1, CardColor.MOSAIC,
                "Choisissez une de vos Oeuvres. Copiez son pouvoir.");
    }

    @Override
    public void ability() {
    	Player player = Menu.getInstance().getGame().getCurrentPlayer();
    	Pile<Card> deeds = player.getDeeds();
    	
    	System.out.println("Carte(s) présente(s) dans les Oeuvres:\n");
        for (int i = 0; i < deeds.size(); i++) {
            System.out.println((i + 1) + ". " + deeds.get(i));
        }
        
        System.out.println("\nChoisir une carte à ajouter à la Vie Future:");
        System.out.println("Sélectionner une carte par son numéro");
        System.out.println("Aide WIP (aide)");
        
        int choice = player.getChoice(0, deeds.size());
        
        Card cardSelected = deeds.get(choice - 1);
        System.out.println("\n" + cardSelected.getDetails());

        System.out.println("\nChoisir une action:");
        System.out.println("0. Retour");
        System.out.println("1. Placer");
        
        choice = player.getChoice(0, 1);
        
        if (choice == 1) {
        	System.out.println("Je défausse " + cardSelected + ".");
        	cardSelected.ability();
        }
    	//TODO
    	
    }
}
