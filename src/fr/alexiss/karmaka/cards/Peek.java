package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Pile;
import fr.alexiss.karmaka.enums.CardColor;

public class Peek extends Card {
    public Peek() {
        super("Coup d'Oeil", 1, CardColor.BLUE,
                "Regardez la Main d’un rival. Vous pouvez ensuite jouer une autre carte.");
    }

    @Override
    public void ability() {
        Pile<Card> rivalHand = Menu.getInstance().getGame().getOppositePlayer().getHand();
        System.out.println("Carte(s) présente(s) dans la main du joueur adverse :\n");
        for (int i = 0; i < rivalHand.size(); i++) {
            System.out.println((i + 1) + ". " + rivalHand.get(i));
        }

        // Play another card
        System.out.println("Vous pouvez rejouer une autre carte.");
        if (Menu.getInstance().getGame().getCurrentPlayer().getHand().isEmpty()) {
            System.out.println("Votre Main est vide, il ne se passe rien !");
            return;
        }
        Menu.getInstance().getGame().getCurrentPlayer().play();
    }
}
