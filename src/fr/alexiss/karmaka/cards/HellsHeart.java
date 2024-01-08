package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Pile;
import fr.alexiss.karmaka.enums.CardColor;

public class HellsHeart extends Card {
    public HellsHeart() {
        super("Fournaise", 2, CardColor.RED,
                "Défaussez les 2 premières cartes de la Vie Future d'un rival.");
    }

    @Override
    public void ability() {
        Pile<Card> fosse = Menu.getInstance().getGame().getRuins();
        Pile<Card> rivalFutureLife = Menu.getInstance().getGame().getOppositePlayer().getFutureLife();

        for (int i = 0; i < 2; i++) {
            try {
                Card carte = rivalFutureLife.removeRandom();
                fosse.add(carte);
                System.out.println("La carte " + carte + "a été défaussée.");
            } catch (Exception e) {
                System.out.println("Le rival n'a plus de carte en main!");
                break;
            }
        }

    }
}
