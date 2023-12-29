package fr.alexiss.karmaka.cards;
import fr.alexiss.karmaka.*;
import fr.alexiss.karmaka.enums.CardColor;

public class AnotherDay extends Card {
	
    public AnotherDay(Game game) {
        super("Lendemain", 1, CardColor.GREEN,
                "Puisez une carte à la Source. Vous pouvez ensuite jouer une autre carte.",
                game);
    }

    @Override
    public void ability() {
    	this.getGame().getCurrentPlayer();

    }
}
