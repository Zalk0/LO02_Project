package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Pile;
import fr.alexiss.karmaka.enums.CardColor;

public class Thievery extends Card {
    public Thievery() {
        super("Vol", 3, CardColor.BLUE,
                "Placez dans votre Main l’Oeuvre Exposée d'un rival.");
    }

    @Override
    public void ability() {
        Pile<Card> playerHand = Menu.getInstance().getGame().getCurrentPlayer().getHand();
        Pile<Card> rivalDeeds = Menu.getInstance().getGame().getOppositePlayer().getDeeds();

        try {
            Card carte = rivalDeeds.getFirst();
            playerHand.add(carte);
            System.out.println("L'Oeuvre " + carte + "a été volée!");
        } catch (Exception e) {
            System.out.println("Le rival n'a pas d'oeuvre!");
        }
    }
}
