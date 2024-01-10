package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

public class Voyage extends Card {
    public Voyage() {
        super("Voyage", 3, CardColor.GREEN,
                "Puisez 3 cartes à la Source. Vous pouvez ensuite jouer une autre carte.");
    }

    @Override
    public void ability() {
        Player player = Menu.getInstance().getGame().getCurrentPlayer();

        for (int i = 0; i < 3; i++) {
            player.addToHand(Menu.getInstance().getGame().getWell().getFirst());
        }

        System.out.println("Vous pouvez rejouer une carte :");
        Menu.getInstance().getGame().getCurrentPlayer().play();
    }
}
