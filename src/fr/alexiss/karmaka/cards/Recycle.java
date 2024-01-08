package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Pile;
import fr.alexiss.karmaka.Player;
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
        System.out.println("\nChoisir une carte à ajouter à la Vie Future:");
        System.out.println("Sélectionner une carte par son numéro");
        System.out.println("Aide WIP (aide)");

        int choice = player.getChoice(0, fosse.size());

        Card cardSelected = fosse.get(choice - 1);
        System.out.println("\n" + cardSelected.getDetails());

        System.out.println("\nChoisir une action:");
        System.out.println("0. Retour");
        System.out.println("1. Placer");

        choice = player.getChoice(0, 1);

        if (choice == 1) {
            System.out.println("Je défausse " + cardSelected + ".");
            fosse.remove(cardSelected);
            player.getFutureLife().add(cardSelected);
        }
    }
}
