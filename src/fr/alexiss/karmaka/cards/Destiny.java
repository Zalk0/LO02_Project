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
        Pile<Card> well = Menu.getInstance().getGame().getWell();
        Pile<Card> cards = new Pile<>();

        System.out.println("Trois premières cartes de la Source :\n");
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ". " + well.getFirst());
            cards.add(well.removeFirst());
        }

        while (cards.size() > 1) {
            System.out.println("\nChoisir une carte à piocher :");
            System.out.println("Sélectionner une carte par son numéro");
            System.out.println("Finir (0)");
            System.out.println("Aide WIP (aide)");

            int choice = player.getChoice(0, cards.size());
            if (choice == 0) {
                break;
            }

            System.out.println("\n" + cards.get(choice).getDetails());
            System.out.println("Êtes-vous sûr de vouloir prendre cette carte ?");
            if (player.getChoice()) {
                player.addToFutureLife(cards.remove(choice));
            }
        }

        if (cards.size() == 1) {
            System.out.println("Il n'y qu'une carte à replacer, vous n'avez pas besoin de choisir d'ordre.");
            well.addFirst(cards.remove());
        }

        while (true) {
            System.out.println("\nVoici l'ordre des cartes à replacer :");
            for (int i = 0; i < cards.size(); i++) {
                System.out.println((i + 1) + ". " + cards.get(i));
            }

            System.out.println("L'ordre vous convient-il ?");
            if (player.getChoice()) {
                while (!cards.isEmpty()) {
                    well.addFirst(cards.removeLast());
                }
                break;
            }

            System.out.println("Quelle carte voulez-vous bouger ?");
            int choice = player.getChoice(0, cards.size()) - 1;
            System.out.println("À quelle position voulez-vous la mettre ?");
            cards.add(player.getChoice(0, cards.size()) - 1, cards.remove(choice));
        }
    }
}
