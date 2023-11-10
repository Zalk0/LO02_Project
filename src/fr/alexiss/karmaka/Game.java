package fr.alexiss.karmaka;

public class Game {

    private Player player1;
    private Player player2;

    public Game() {
        init();
    }

    public void init() {
        player1 = new Player();
        player2 = new Player();

    }
}
